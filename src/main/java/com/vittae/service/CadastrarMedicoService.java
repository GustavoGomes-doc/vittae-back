package com.vittae.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vittae.dto.CadastrarMedicoDTO;
import com.vittae.model.Disponibilidade;
import com.vittae.model.Especialidade;
import com.vittae.model.Medico;
import com.vittae.model.enums.DiaSemana;
import com.vittae.repository.CadastrarMedicoRepository;
import com.vittae.repository.EspecialidadeRepository;

@Service // regra d negocio; toda a logica
public class CadastrarMedicoService {

	@Autowired
	private CadastrarMedicoRepository cadastrarMedicoRepository;

	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	private PasswordEncoder passwordEncoder;

	public CadastrarMedicoService(PasswordEncoder passwordEncoder, CadastrarMedicoRepository medicoRepository) {
		this.passwordEncoder = passwordEncoder;
		this.cadastrarMedicoRepository = medicoRepository;
	}

	public Medico salvarDTO(CadastrarMedicoDTO dto) {
		
		 String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

		Medico medico = new Medico();
		// dados usuario
		medico.setNome(dto.getNome());
		medico.setCpf(dto.getCpf());
		medico.setEmail(dto.getEmail());
		medico.setSenha(passwordEncoder.encode(dto.getSenha()));
		// dados médico
		medico.setDataNascimento(dto.getDataNascimento());
		medico.setCrm(dto.getCrm());
		medico.setUfCrm(dto.getUfCrm());
		medico.setRqe(dto.getRqe());
		medico.setCep(dto.getCep());
		medico.setValorConsulta(dto.getValorConsulta());
		medico.setTempoConsulta(dto.getTempoConsulta());
		medico.setBiografia(dto.getBiografia());

		// especialidade
		if (dto.getEspecialidades() != null) { // busca ou cria p cada especialide no dto: tenta achar no banco, usa,
			List<Especialidade> especialidades = new ArrayList<>();
			for (String nomeEsp : dto.getEspecialidades()) {
				Especialidade esp = especialidadeRepository.findByNome(nomeEsp).orElseGet(() -> { // se n encontrou cria
																									// e salva
					Especialidade nova = new Especialidade();
					nova.setNome(nomeEsp);
					return especialidadeRepository.save(nova);
				});
				especialidades.add(esp);
			}
			medico.setEspecialidades(especialidades);
		}

		// salva médico primeiro para ter id gerado
		Medico medicoSalvo = cadastrarMedicoRepository.save(medico);

		// dispnb, seta o médico em cada uma antes de salvar
		if (dto.getDisponibilidades() != null) {
			List<Disponibilidade> disponibilidades = new ArrayList<>();

			for (CadastrarMedicoDTO.DisponibilidadeDTO dtoDisp : dto.getDisponibilidades()) {
				Disponibilidade disp = new Disponibilidade();

				disp.setDiaSemana(DiaSemana.valueOf(dtoDisp.getDiaSemana())); // converte o string para enum
																				// SE NN : IllegalArgumentException
																				// barra
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

	// crud med
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
