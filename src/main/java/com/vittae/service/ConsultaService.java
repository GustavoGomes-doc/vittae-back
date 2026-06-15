package com.vittae.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vittae.dto.AgendamentoDTO;
import com.vittae.dto.VisualizarConsultaDTO;
import com.vittae.model.Consulta;
import com.vittae.model.Medico;
import com.vittae.model.Paciente;
import com.vittae.model.enums.Perfil;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void salvarAgendamento(AgendamentoDTO dto) {
        Consulta novaConsulta = new Consulta();

        novaConsulta.setDataConsulta(dto.getDataConsulta());
        novaConsulta.setHora(dto.getHora());
        novaConsulta.setObservacoes(dto.getObservacoes());
        novaConsulta.setStatus(Status.PENDENTE);

        novaConsulta.setRespNome(dto.getRespNome());
        novaConsulta.setRespCpf(dto.getRespCpf());
        novaConsulta.setRespParentesco(dto.getRespParentesco());

        if (dto.getRespDataNascimento() != null && !dto.getRespDataNascimento().isEmpty()) {
            DateTimeFormatter fmtResp = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            novaConsulta.setRespDataNascimento(LocalDate.parse(dto.getRespDataNascimento(), fmtResp));
        }

        Medico medico = cadastrarMedicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        novaConsulta.setMedico(medico);
        novaConsulta.setValorConsulta(medico.getValorConsulta());

        boolean horarioOcupado = consultaRepository.existsConsultaOcupada(
            medico.getId(), dto.getDataConsulta(), dto.getHora());
        if (horarioOcupado) {
            throw new RuntimeException("Falha no agendamento: Este horario ja foi marcado.");
        }

        if (dto.getEspecialidade() != null) {
            especialidadeRepository.findByNome(dto.getEspecialidade())
                .ifPresent(novaConsulta::setEspecialidade);
        }

        String cpfPaciente  = dto.getPaciente().getCpf();
        String nomePaciente = dto.getPaciente().getNome();

        Paciente paciente = pacienteRepository.findByCpfAndNome(cpfPaciente, nomePaciente)
            .orElseGet(() -> {
                Paciente novo = new Paciente();
                novo.setNome(nomePaciente);
                novo.setCpf(cpfPaciente);
                novo.setGenero(dto.getPaciente().getGenero());
                novo.setTelefone(dto.getPaciente().getTelefone());
                novo.setPerfil(Perfil.PACIENTE);
                novo.setSenha(passwordEncoder.encode(cpfPaciente));

                if (dto.getPaciente().getNascimento() != null && !dto.getPaciente().getNascimento().isEmpty()) {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    novo.setDataNascimento(LocalDate.parse(dto.getPaciente().getNascimento(), fmt));
                }
                return pacienteRepository.save(novo);
            });

        // FIX: vincular responsável ao paciente menor
        if (dto.getRespCpf() != null && !dto.getRespCpf().isEmpty()) {
            Paciente responsavel = pacienteRepository.findByCpf(dto.getRespCpf())
                .orElseGet(() -> {
                    Paciente resp = new Paciente();
                    resp.setNome(dto.getRespNome());
                    resp.setCpf(dto.getRespCpf());
                    resp.setPerfil(Perfil.PACIENTE);
                    resp.setSenha(passwordEncoder.encode(dto.getRespCpf()));
                    if (dto.getRespDataNascimento() != null && !dto.getRespDataNascimento().isEmpty()) {
                        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        resp.setDataNascimento(LocalDate.parse(dto.getRespDataNascimento(), fmt));
                    }
                    return pacienteRepository.save(resp);
                });
            paciente.setResponsavel(responsavel);
            pacienteRepository.save(paciente);
        }

        novaConsulta.setPaciente(paciente);
        consultaRepository.save(novaConsulta);
    }

    public List<VisualizarConsultaDTO> listarParaVisualizacao() {
        return consultaRepository.findAll().stream().map(consulta -> {
            VisualizarConsultaDTO dto = new VisualizarConsultaDTO();
            dto.setId(consulta.getId());
            dto.setHora(consulta.getHora());
            dto.setStatus(consulta.getStatus());
            dto.setMedico(consulta.getMedico());
            dto.setDataConsulta(consulta.getDataConsulta());
            dto.setValorConsulta(consulta.getValorConsulta());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<Consulta> listarTodos() {
        return consultaRepository.findAllComRelacionamentos();
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAllComRelacionamentos();
    }

    public List<Consulta> listarPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    public List<Consulta> listarPorMedico(Long medicoId) {
        return consultaRepository.findByMedicoId(medicoId);
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta atualizar(Long id, Consulta consultaAtualizada) {
        Consulta existente = consultaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada: " + id));
        existente.setStatus(consultaAtualizada.getStatus());
        existente.setDataConsulta(consultaAtualizada.getDataConsulta());
        existente.setHora(consultaAtualizada.getHora());
        return consultaRepository.save(existente);
    }

    public void deletar(Long id) {
        consultaRepository.deleteById(id);
    }
}