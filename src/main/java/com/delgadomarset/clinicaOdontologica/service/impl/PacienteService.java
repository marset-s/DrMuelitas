package com.delgadomarset.clinicaOdontologica.service.impl;


import com.delgadomarset.clinicaOdontologica.dto.DomicilioDto;
import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;
import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.delgadomarset.clinicaOdontologica.repository.PacienteRepository;
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

    private final PacienteRepository pacienteRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ObjectMapper objectMapper) {
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public PacienteDto registrarPaciente(Paciente paciente) throws BadRequestException {
        if (paciente.getDomicilio() == null) {
            throw new BadRequestException("El paciente no tiene un domicilio registrado");
        }

        PacienteDto pacienteDto = objectMapper.convertValue(pacienteRepository.save(paciente), PacienteDto.class);
        pacienteDto.setDomicilio(DomicilioDto.fromDomicilio(paciente.getDomicilio()));

        LOGGER.info("Nuevo paciente registrado con éxito: {}", pacienteDto);

        return pacienteDto;
    }

    @Override
    public List<PacienteDto> listarPacientes() {
        List<PacienteDto> pacienteDtos = pacienteRepository
                .findAll()
                .stream()
                .map(paciente -> {
                    PacienteDto pacienteDto = objectMapper.convertValue(paciente, PacienteDto.class);
                    pacienteDto.setDomicilio(DomicilioDto.fromDomicilio(paciente.getDomicilio()));
                    return pacienteDto;
                })
                .toList();
        LOGGER.info("Lista de todos los pacientes: {}", pacienteDtos);
        return pacienteDtos;
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;
        if (pacienteBuscado != null) {
            pacienteDto = objectMapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilio(DomicilioDto.fromDomicilio(pacienteBuscado.getDomicilio()));
            LOGGER.info("Paciente encontrado: {}", pacienteDto);

        } else {
            LOGGER.info("El id no se encuentra registrado en la base de datos");
            throw new ResourceNotFoundException("El paciente no existe en la base datos");
        }

        return pacienteDto;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) throws BadRequestException {
        PacienteDto pacienteActualizado;
        if (!pacienteRepository.existsById(paciente.getId())){
            LOGGER.warn("No es posible actualizar el paciente.");
            throw new BadRequestException("El paciente no existe en la base de datos");
        } else {
            pacienteActualizado = registrarPaciente(paciente);
            LOGGER.warn("Se ha actualizado al paciente con Id {}: {}", pacienteActualizado.getId(), pacienteActualizado);
        }
        return pacienteActualizado;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (buscarPacientePorId(id) != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado al paciente con ID: {}", id);
        } else {
            LOGGER.warn("El paciente con ID: " + id + "no se encuentra en la base de datos");
            throw new ResourceNotFoundException("El paciente con ID: " + id + " no se encuentra en la base de datos");
        }
    }
}
