package com.vittae.controller;
 
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.dto.CadastrarMedicoDTO;
import com.vittae.dto.HorariosLivresDTO;
import com.vittae.dto.MedicoListagemDTO;
import com.vittae.model.Disponibilidade;
import com.vittae.model.Medico;
import com.vittae.repository.CadastrarMedicoRepository;
import com.vittae.repository.ConsultaRepository;
import com.vittae.service.CadastrarMedicoService;

import jakarta.validation.Valid;

@RestController //controler, resp body, pega json  da req e transforma dto 
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*")
public class CadastrarMedicoController {
 
    @Autowired
    private CadastrarMedicoService cadastrarMedicoService;
    
    @Autowired
    private CadastrarMedicoRepository cadastrarMedicoRepository;
    
    @Autowired
    private ConsultaRepository consultaRepository;
	
    
    @GetMapping("/{id}/horarios-livres")
    public ResponseEntity<?> getHorariosLivres (@PathVariable Long id,
    		@RequestParam(required = false) String data) {
    	
    	List <Disponibilidade> disponibilidades = cadastrarMedicoRepository
    			.findDisponibilidadesByMedicoId(id);
    	
    	List<String> diasDisponiveis = disponibilidades.stream()
    			.map(d -> d.getDiaSemana().name())
    			.collect(Collectors.toList());
    	
    	if (data == null || data.isEmpty()) {
    		return ResponseEntity.ok(new HorariosLivresDTO(diasDisponiveis, null));
    	}
    	
    	LocalDate dataConsulta = LocalDate.parse(data);
    	
    	String diaDaSemana = mapearDiaSemana(dataConsulta.getDayOfWeek());
    	
    	Optional<Disponibilidade> dispDoDia = disponibilidades.stream()
    			.filter(d -> d.getDiaSemana().name().equals(diaDaSemana))
    			.findFirst();
    	if (dispDoDia.isEmpty()) {
    		return ResponseEntity.ok(new HorariosLivresDTO(diasDisponiveis, List.of()));
    	}
    	
    	Disponibilidade disp = dispDoDia.get();
    	LocalTime inicio = LocalTime.parse(disp.getHoraInicio());
    	LocalTime fim = LocalTime.parse(disp.getHoraFim());
    	
    	//busca o tempo de consulta do medico
    	Medico medico = cadastrarMedicoRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("Medico nao encontrado"));
    	int tempoConsulta = medico.getTempoConsultaMinutos();
    	if (tempoConsulta <= 0) tempoConsulta = 30; //fallback
    	
    	List<LocalTime> todosHorarios = new ArrayList<>();
    	LocalTime atual = inicio;
    	while (atual.plusMinutes(tempoConsulta).compareTo(fim) <= 0) {
    		todosHorarios.add(atual);
    		atual = atual.plusMinutes(tempoConsulta);
    	}
    	
    	//remove todos os horarios ja ocupados
    	List<LocalTime> ocupados = consultaRepository.findHorariosOcupados(id, dataConsulta);
    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
    	
    	List<String> livres = todosHorarios.stream()
    			.filter(h -> !ocupados.contains(h))
    			.map(h -> h.format(fmt))
    			.collect(Collectors.toList());
    	
    	return ResponseEntity.ok(new HorariosLivresDTO(diasDisponiveis, livres));
    }
   
    
    //mapeia day of week do java para enum
    private String mapearDiaSemana(java.time.DayOfWeek dow) {
    	switch (dow) {
    	case MONDAY : return "SEGUNDA";
    	case TUESDAY : return "TERCA";
    	case WEDNESDAY : return "QUARTA";
    	case THURSDAY : return "QUINTA";
    	case FRIDAY : return "SEXTA";
    	case SATURDAY : return "SABADO";
    		default: 	return "";
    	}
    }
 
    //receb DTO em vez de medico diretamente
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarMedicoDTO dto) {
        try {
            Medico medicoSalvo = cadastrarMedicoService.salvarDTO(dto);
            return ResponseEntity.ok(medicoSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
  
    @GetMapping
    public List<MedicoListagemDTO> listar() {
        return cadastrarMedicoRepository.findAllComEspecialidades()
            .stream()
            .map(MedicoListagemDTO::new)
            .collect(Collectors.toList());
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscar(@PathVariable Long id) { //captura 1 da url e coloca no id
        return cadastrarMedicoService.buscarPorId(id)
                .map(ResponseEntity::ok) //200
                .orElse(ResponseEntity.notFound().build()); //404
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico) { //add atualizar  med dto
        return ResponseEntity.ok(cadastrarMedicoService.atualizar(id, medico));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cadastrarMedicoService.deletar(id);
        return ResponseEntity.noContent().build(); //204
    }
}