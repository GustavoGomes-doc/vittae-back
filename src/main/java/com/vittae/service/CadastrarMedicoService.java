package com.vittae.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vittae.dto.CadastrarMedicoDTO;
import com.vittae.dto.MedicoListagemDTO;
import com.vittae.model.Disponibilidade;
import com.vittae.model.Especialidade;
import com.vittae.model.Medico;
import com.vittae.model.enums.DiaSemana;
import com.vittae.model.enums.Perfil;
import com.vittae.repository.CadastrarMedicoRepository;
import com.vittae.repository.EspecialidadeRepository;
import com.vittae.repository.UsuarioRepository;

@Service //regra d negocio; toda a logica 
public class CadastrarMedicoService {

	@Autowired
	private CadastrarMedicoRepository cadastrarMedicoRepository;

	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	public Medico salvarDTO(CadastrarMedicoDTO dto) {
		
		String cpfLimpo = dto.getCpf() != null ? dto.getCpf().replaceAll("\\D", "") : "";
		if (usuarioRepository.findByCpf(cpfLimpo).isPresent()) {
			throw new RuntimeException("CPF já cadastrado no sistema.");
		}
		
		String telefoneLimpo = dto.getTelefone() != null ? dto.getTelefone().replaceAll("\\D", "") : "";
		String crmLimpo = dto.getCrm() != null ? dto.getCrm().replaceAll("\\D", "") : "";
		String ufCrm = dto.getUfCrm() != null ? dto.getUfCrm().toUpperCase() : "";

		if (cadastrarMedicoRepository.existsByCrmAndUfCrm(crmLimpo, ufCrm)) {
		    throw new RuntimeException("CRM já cadastrado para esta UF.");
		}

		if (cadastrarMedicoRepository.existsByTelefone(telefoneLimpo)) {
		    throw new RuntimeException("Telefone já cadastrado no sistema.");
		}
		
		Medico medico = new Medico();

		//dados usuario
		medico.setNome(dto.getNome());
		medico.setCpf(cpfLimpo);
		medico.setEmail(dto.getEmail());
		medico.setSenha(passwordEncoder.encode(dto.getSenha())); // ✅ BCrypt

		//dados médico
		medico.setDataNascimento(dto.getDataNascimento());
		medico.setCrm(crmLimpo);
		medico.setUfCrm(ufCrm);
		medico.setValorConsulta(dto.getValorConsulta());
		medico.setTempoConsultaMinutos(dto.getTempoConsultaMinutos());
		medico.setTelefone(telefoneLimpo);
		medico.setFoto(dto.getFoto());
		medico.setPerfil(Perfil.MEDICO);	

		//especialidade
		if (dto.getEspecialidades() != null) { //busca ou cria p cada especialide no dto: tenta achar no banco, usa,
			List<Especialidade> especialidades = new ArrayList<>();
			for (String nomeEsp : dto.getEspecialidades()) {
				Especialidade esp = especialidadeRepository.findByNome(nomeEsp).orElseGet(() -> { // se n encontrou cria e salva
					Especialidade nova = new Especialidade();
					nova.setNome(nomeEsp);
					return especialidadeRepository.save(nova);
				});
				especialidades.add(esp);
			}
			medico.setEspecialidades(especialidades);
		}

		//salva médico primeiro para ter id gerado
		Medico medicoSalvo = cadastrarMedicoRepository.save(medico);

		//dispnb, seta o médico em cada uma antes de salvar
		if (dto.getDisponibilidades() != null) {
		    List<Disponibilidade> disponibilidades = new ArrayList<>();
		    
		    for (CadastrarMedicoDTO.DisponibilidadeDTO dtoDisp : dto.getDisponibilidades()) {
		        Disponibilidade disp = new Disponibilidade();
		        
		        disp.setDiaSemana(DiaSemana.valueOf(dtoDisp.getDiaSemana())); //converte o string para enum
		        															// SE NN : IllegalArgumentException  barra
		        disp.setHoraInicio(dtoDisp.getHoraInicio()); 	
		        disp.setHoraFim(dtoDisp.getHoraFim());       
		        
		        disp.setMedico(medicoSalvo);
		        disponibilidades.add(disp);
		    }
		    
		    medicoSalvo.setDisponibilidades(disponibilidades);
		    cadastrarMedicoRepository.save(medicoSalvo);
		}

		return medicoSalvo;
	}
	
	public List<MedicoListagemDTO> listarPorEspecialidade(String especialidade) {
	    return cadastrarMedicoRepository.findByEspecialidadesNome(especialidade)
	        .stream()
	        .map(MedicoListagemDTO::new)
	        .collect(Collectors.toList());
	}

	//crudzin med
	public List<Medico> listarTodos() {
		return cadastrarMedicoRepository.findAll();
	}

	public Optional<Medico> buscarPorId(Long id) { // controler decide c n achar 
		return cadastrarMedicoRepository.findById(id);
	}

	public Medico atualizar(Long id, Medico dadosNovos) {
		Medico medico = cadastrarMedicoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Médico não encontrado"));
		if (dadosNovos.getNome() != null)
			medico.setNome(dadosNovos.getNome());
		if (dadosNovos.getCrm() != null)
			medico.setCrm(dadosNovos.getCrm());
		if (dadosNovos.getEmail() != null)
			medico.setEmail(dadosNovos.getEmail());
		return cadastrarMedicoRepository.save(medico);
	}

	public void deletar(Long id) {
		cadastrarMedicoRepository.deleteById(id);
	}
}