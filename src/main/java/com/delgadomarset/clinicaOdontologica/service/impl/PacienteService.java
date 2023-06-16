package com.delgadomarset.clinicaOdontologica.service.impl;



import com.delgadomarset.clinicaOdontologica.dto.DomicilioDto;
import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;

import com.delgadomarset.clinicaOdontologica.entity.Domicilio;
import com.delgadomarset.clinicaOdontologica.repository.IDao;
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

    private final PacienteReposirtory pacienteReposirtory;
    private ObjectMapper objectMapper;

    @Autowired
    public PacienteService(PacienteReposirtory pacienteReposirtory, ObjectMapper objectMapper) {
        this.pacienteReposirtory = pacienteReposirtory;
        this.objectMapper = objectMapper;
    }


    @Override
    public List<PacienteDto> listarPacientes() {
        List<Paciente> pacienteList = pacienteReposirtory.findAll();
        List<PacienteDto> pacienteDtos = listarPacientes().stream()
                .map(paciente -> {
                    Domicilio domicilio = paciente.getDomicilo();
                    DomicilioDto domicilioDto = objectMapper.convertValue(domicilio, DomicilioDto.class);
                    return  new PacienteDto(paciente.getId(),paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaIngreso(), domicilioDto);
                })
                .toList();
        LOGGER.info("Lista de todos los pacientes: {}", pacienteDtos);
        return pacienteDtos;
    }


    @Override
    public PacienteDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteReposirtory.findById(id).orElse(null);
        PacienteDto pacienteDto = null;
        if (pacienteBuscado != null) {
            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteBuscado.getDomicilio(), DomicilioDto.class);
            pacienteDto = objectMapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente encontrado: {}", pacienteDto);

        } else LOGGER.info("El id no se encuentra registrado en la base de datos");

        return pacienteDto;
    }


    @Override
    public PacienteDto guardarPaciente(Paciente paciente) {
        Paciente pacienteNuevo = pacienteReposirtory.save(paciente);
        DomicilioDto domicilioDto = objectMapper.convertValue(pacienteNuevo.getDomicilio(), DomicilioDto.class);
        PacienteDto pacienteDtoNuevo = objectMapper.convertValue(pacienteNuevo, PacienteDto.class);
        pacienteDtoNuevo.setDomicilioDto(domicilioDto);

        LOGGER.info("Nuevo paciente registrado con exito: {}", pacienteDtoNuevo);

        return pacienteDtoNuevo;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) {
        Paciente pacienteAActualizar = pacienteReposirtory.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;

        if (pacienteAActualizar != null) {
            pacienteAActualizar = paciente;
            pacienteReposirtory.save(pacienteAActualizar);

            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteAActualizar.getDomicilio(), DomicilioDto.class);
            pacienteActualizadoDto = objectMapper.convertValue(pacienteAActualizar, PacienteDto.class);
            pacienteActualizadoDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente actualizado con exito: {}", pacienteActualizadoDto);

        } else LOGGER.error("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");

        return pacienteActualizadoDto;

    }
    @Override
    public void eliminarPaciente(Long id) {
        pacienteReposirtory.deleteById(id);
        LOGGER.warn("Se ha eliminado el paciente con id {}", id);
    }

}
