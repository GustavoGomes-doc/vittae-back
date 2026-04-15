package com.vittae.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vittae.dto.CadastrarMedicoDTO;
import com.vittae.model.DiaSemana;
import com.vittae.model.Disponibilidade;
import com.vittae.model.Especialidade;
import com.vittae.model.Medico;
import com.vittae.repository.CadastrarMedicoRepository;
import com.vittae.repository.EspecialidadeRepository;

@Service
public class CadastrarMedicoService {

	@Autowired
	private CadastrarMedicoRepository cadastrarMedicoRepository;

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// ── SALVAR (a partir do DTO) ─────────────────────────────
	public Medico salvarDTO(CadastrarMedicoDTO dto) {

		Medico medico = new Medico();

		// 1. Dados herdados de Usuario
		medico.setNome(dto.getNome());
		medico.setCpf(dto.getCpf());
		medico.setEmail(dto.getEmail());
		medico.setSenha(passwordEncoder.encode(dto.getSenha())); // ✅ BCrypt

		// 2. Dados do Médico
		medico.setDataNascimento(dto.getDataNascimento());
		medico.setCrm(dto.getCrm());
		medico.setUfCrm(dto.getUfCrm());
		medico.setRqe(dto.getRqe());
		medico.setCep(dto.getCep());
		medico.setValorConsulta((int) dto.getValorConsulta()); // ou mude para double no model
		medico.setTempoConsultaMinutos(dto.getTempoConsultaMinutos());
		medico.setBiografia(dto.getBiografia());

		// 3. Especialidades — busca no banco pelo nome, cria se não existir
		if (dto.getEspecialidades() != null) {
			List<Especialidade> especialidades = new ArrayList<>();
			for (String nomeEsp : dto.getEspecialidades()) {
				Especialidade esp = especialidadeRepository.findByNome(nomeEsp).orElseGet(() -> {
					Especialidade nova = new Especialidade();
					nova.setNome(nomeEsp);
					return especialidadeRepository.save(nova);
				});
				especialidades.add(esp);
			}
			medico.setEspecialidades(especialidades);
		}

		// 4. Salva o médico primeiro para ter ID gerado
		Medico medicoSalvo = cadastrarMedicoRepository.save(medico);

		// 5. Disponibilidades — seta o médico em cada uma antes de salvar
		if (dto.getDisponibilidades() != null) {
		    List<Disponibilidade> disponibilidades = new ArrayList<>();
		    
		    for (CadastrarMedicoDTO.DisponibilidadeDTO dtoDisp : dto.getDisponibilidades()) {
		        Disponibilidade disp = new Disponibilidade();
		        
		        disp.setDiaSemana(DiaSemana.valueOf(dtoDisp.getDiaSemana())); 
		        
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

	// ── Converte "08:00" → 8 e "18:30" → 18 ───────────────
	private int converterHora(String horaStr) {
		if (horaStr == null || !horaStr.contains(":"))
			return 0;
		return Integer.parseInt(horaStr.split(":")[0]);
	}

	// ── CRUD padrão ──────────────────────────────────────────
	public List<Medico> listarTodos() {
		return cadastrarMedicoRepository.findAll();
	}

	public Optional<Medico> buscarPorId(Long id) {
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