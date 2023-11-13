package com.example.incidentes.domain.service;

import com.example.incidentes.domain.model.Incidente;
import com.example.incidentes.domain.repository.IncidenteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class IncidenteService {
    private IncidenteRepository incidenteRepository;

    @Transactional
    public Incidente salvar(Incidente incidente) {
        incidente.setCreatedAt(LocalDateTime.now());
        return incidenteRepository.save(incidente);
    }

    public void excluir(Long idIncident) {
        incidenteRepository.deleteById(idIncident);
    }
}
