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
import com.vittae.model.enums.Status;
import com.vittae.repository.CadastrarMedicoRepository;
import com.vittae.repository.ConsultaRepository;
import com.vittae.repository.EspecialidadeRepository;
import com.vittae.repository.PacienteRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private CadastrarMedicoRepository cadastrarMedicoRepository;
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	public void salvarAgendamento(AgendamentoDTO dto) {
	    Consulta novaConsulta = new Consulta();

	    novaConsulta.setDataConsulta(dto.getDataConsulta());
	    novaConsulta.setHora(dto.getHora());;
	    novaConsulta.setObservacoes(dto.getObservacoes());
	    novaConsulta.setStatus(Status.PENDENTE);

	    // responsável legal
	    novaConsulta.setRespNome(dto.getRespNome());
	    novaConsulta.setRespCpf(dto.getRespCpf());
	    novaConsulta.setRespParentesco(dto.getRespParentesco());

	    // médico
	    Medico medico = cadastrarMedicoRepository.findById(dto.getMedicoId())
	        .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
	    novaConsulta.setMedico(medico);
	    novaConsulta.setValorConsulta(medico.getValorConsulta());
	    
	    boolean horarioOcupado = consultaRepository.existsConsultaOcupada(
	    		medico.getId(), dto.getDataConsulta(), dto.getHora()
	    		);
	    
	    if (horarioOcupado) {
	    	throw new RuntimeException("Falha no agendamento: Este horario ja foi marcado.");
	    }
	    
	    novaConsulta.setMedico(medico);
	    novaConsulta.setValorConsulta(medico.getValorConsulta());

	    // especialidade
	    if (dto.getEspecialidade() != null) {
	        especialidadeRepository.findByNome(dto.getEspecialidade())
	            .ifPresent(novaConsulta::setEspecialidade);
	    }
	    
	    String cpfPaciente = dto.getPaciente().getCpf();
	    String nomePaciente = dto.getPaciente().getNome();
	    
	    // paciente
	    String cpf = dto.getPaciente().getCpf();
	    Paciente paciente = pacienteRepository.findByCpfAndNome(cpfPaciente, nomePaciente)
		        .orElseGet(() -> {
		            Paciente novo = new Paciente();
		            novo.setNome(nomePaciente);
		            novo.setCpf(cpfPaciente);
		            novo.setGenero(dto.getPaciente().getGenero());
		            
		            if (dto.getPaciente().getNascimento() != null && !dto.getPaciente().getNascimento().isEmpty()) {
		                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		                novo.setDataNascimento(LocalDate.parse(dto.getPaciente().getNascimento(), fmt));
		            }
		            return pacienteRepository.save(novo);
		        });
		        
		    novaConsulta.setPaciente(paciente);

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