package com.delgadomarset.clinicaOdontologica.service.impl;


import com.delgadomarset.clinicaOdontologica.dto.DomicilioDto;
import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;
import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import com.delgadomarset.clinicaOdontologica.repository.PacienteReposirtory;
import com.delgadomarset.clinicaOdontologica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteReposirtory pacienteRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public PacienteService(PacienteReposirtory pacienteRepository, ObjectMapper objectMapper) {
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public List<PacienteDto> listarPacientes() {
        List<PacienteDto> pacienteDtos = pacienteRepository
                .findAll()
                .stream()
                .map(paciente -> {
                    PacienteDto pacienteDto = objectMapper.convertValue(paciente, PacienteDto.class);
                    pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(paciente.getDomicilio()));
                    return pacienteDto;
                })
                .toList();
        LOGGER.info("Lista de todos los pacientes: {}", pacienteDtos);
        return pacienteDtos;
    }


    @Override
    public PacienteDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;
        if (pacienteBuscado != null) {
            pacienteDto = objectMapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(pacienteBuscado.getDomicilio()));
            LOGGER.info("Paciente encontrado: {}", pacienteDto);

        } else LOGGER.info("El id no se encuentra registrado en la base de datos");

        return pacienteDto;
    }

    @Override
    public PacienteDto registrarPaciente(Paciente paciente) {
        PacienteDto pacienteDto = objectMapper.convertValue(pacienteRepository.save(paciente), PacienteDto.class);
        pacienteDto.setDomicilioDto(DomicilioDto.fromDomicilio(paciente.getDomicilio()));
        LOGGER.info("Nuevo paciente registrado con exito: {}", pacienteDto);
        return pacienteDto;
    }


    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) {
        Paciente pacienteAActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;
        if (pacienteAActualizar != null) {
            pacienteAActualizar = paciente;
            pacienteActualizadoDto = registrarPaciente(pacienteAActualizar);
            LOGGER.warn("El paciente con ID {} ha sido actualizado: {}", pacienteAActualizar.getId(), pacienteActualizadoDto);
        } else
            LOGGER.warn("No es posible actualizar el paciente porque no está registrado en la base de datos");
        return pacienteActualizadoDto;

    }

    @Override
    public void eliminarPaciente(Long id) {
        if (pacienteRepository.existsById(id))
            pacienteRepository.deleteById(id);
        LOGGER.warn("Se ha eliminado el paciente con id {}", id);
    }

}
