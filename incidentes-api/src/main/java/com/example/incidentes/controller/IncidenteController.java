package com.example.incidentes.controller;


import com.example.incidentes.domain.model.Incidente;
import com.example.incidentes.domain.repository.IncidenteRepository;
import com.example.incidentes.domain.service.IncidenteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/incidentes")
public class IncidenteController {

    private IncidenteRepository incidenteRepository;
    private IncidenteService incidenteService;

    @GetMapping
    public ResponseEntity<List<Incidente>> listar() {
        List<Incidente> list = incidenteRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{idIncident}")
    public ResponseEntity<Incidente> findById(@PathVariable Long idIncident) {

        Optional<Incidente> obj = incidenteRepository.findById(idIncident);

        if (obj.isPresent()) {
            return ResponseEntity.ok(obj.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Incidente adicionar (@RequestBody Incidente incidente) {
        return incidenteService.salvar(incidente);
    }

    @PutMapping("/{idIncident}")
    public ResponseEntity<Incidente> atualizar (@PathVariable Long idIncident, @RequestBody Incidente incidente) {

        if (!incidenteRepository.existsById(idIncident)) {
            return ResponseEntity.notFound().build();
        }


        incidente.setIdIncident(idIncident);
        incidente.setUpdateAt(LocalDateTime.now());
        incidente = incidenteService.salvar(incidente);

        return ResponseEntity.ok(incidente);

    }

    @DeleteMapping("/{idIncident}")
    public ResponseEntity<Void> remover(@PathVariable Long idIncident) {
        if (!incidenteRepository.existsById(idIncident)) {
            return ResponseEntity.notFound().build();
        }
        incidenteService.excluir(idIncident);
        return ResponseEntity.noContent().build();
    }
}
