package com.vittae.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.dto.AgendamentoDTO;
import com.vittae.model.Consulta;
import com.vittae.model.Medico;
import com.vittae.model.Paciente;
import com.vittae.repository.CadastrarMedicoRepository;
import com.vittae.repository.ConsultaRepository;
import com.vittae.repository.PacienteRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private CadastrarMedicoRepository cadastrarMedicoRepository;

	public void salvarAgendamento(AgendamentoDTO dto) {
		Consulta novaConsulta = new Consulta();

		// convertendo de LocalDate do DTO para Date da Entidade
		//novaConsulta.setDataAgendado(java.sql.Date.valueOf(dto.getDataAgendado()));
		//novaConsulta.setDataConsulta(java.sql.Date.valueOf(dto.getDataConsulta()));
		novaConsulta.setHora(dto.getHora());

		// convertendo o Double do DTO para o int da sua classe
		//if (dto.getValorconsulta() != null) {
		//	novaConsulta.setValorconsulta(dto.getValorconsulta().intValue());
		//}

		// mapeamento do Médico (Criamos uma referência rápida só com o ID)
		Medico medicoSelecionado = cadastrarMedicoRepository.findById(dto.getMedicoId())
				.orElseThrow(() -> new RuntimeException("Médico não encontrado"));

		novaConsulta.setMedico(medicoSelecionado);

		//logica p salvar paciente
		String cpfDoPaciente = dto.getPaciente().getCpf();

		Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(cpfDoPaciente);
		Paciente pacienteDaConsulta;

		if (pacienteExistente.isPresent()) {
			pacienteDaConsulta = pacienteExistente.get();
		} else {
			Paciente novoPaciente = new Paciente();
			novoPaciente.setNome(dto.getPaciente().getNome());
			novoPaciente.setCpf(cpfDoPaciente);
			novoPaciente.setTelefone(dto.getPaciente().getTelefone());
			novoPaciente.setGenero(dto.getPaciente().getGenero()); 

			// converte "DD/MM/AAAA" → LocalDate
			if (dto.getPaciente().getNascimento() != null && !dto.getPaciente().getNascimento().isEmpty()) {
			    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			    novoPaciente.setDataNascimento(LocalDate.parse(dto.getPaciente().getNascimento(), fmt));
			}
			pacienteDaConsulta = pacienteRepository.save(novoPaciente);
		}

		// 4. Agora atrelamos o OBJETO paciente inteiro na Consulta, e não só o ID
		novaConsulta.setPaciente(pacienteDaConsulta);

		consultaRepository.save(novaConsulta);
	}

	public List<Consulta> listarTodos() {
		return consultaRepository.findAll();
	}

	public Optional<Consulta> buscarPorId(Long id) {
		return consultaRepository.findById(id);
	}

	public Object atualizar(Long id, Consulta consulta) {
		return null;
	}

	public void deletar(Long id) {
		consultaRepository.deleteById(id);
	}
}