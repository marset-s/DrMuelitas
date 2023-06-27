package com.delgadomarset.clinicaOdontologica.service.impl;

import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;
import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Turno;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.delgadomarset.clinicaOdontologica.repository.TurnoRepository;
import com.delgadomarset.clinicaOdontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.objectMapper = objectMapper;
    }


    @Override
    public TurnoDto registrarTurno(Turno turno) throws BadRequestException, ResourceNotFoundException {
        TurnoDto turnoDto = null;
        PacienteDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if(paciente == null || odontologo == null) {
            if(paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            }
            else if (paciente == null){
                LOGGER.error("El paciente no se encuentra en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            } else {
                LOGGER.error("El odontologo no se encuentra en nuestra base de datos");
                throw new BadRequestException("El odontologo no se encuentra en nuestra base de datos");
            }

        } else {
            turnoDto = TurnoDto.fromTurno(turnoRepository.save(turno));
            LOGGER.info("Nuevo turno registrado con exito: {}", turnoDto);
        }
        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnoDtos = turnos.stream()
                .map(turno -> {
                    TurnoDto turnoDto = objectMapper.convertValue(turno, TurnoDto.class);
                    turnoDto.setPaciente(PacienteDto.fromPaciente(turno.getPaciente()));
                    turnoDto.setOdontologo(OdontologoDto.fromOdontologo(turno.getOdontologo()));
                    return turnoDto;
                })
                .toList();
        LOGGER.info("Lista de todos los turnos: {}", turnoDtos);
        return turnoDtos;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;
        if (turnoBuscado != null) {
            turnoDto = objectMapper.convertValue(turnoBuscado, TurnoDto.class);
            turnoDto.setPaciente(PacienteDto.fromPaciente(turnoBuscado.getPaciente()));
            turnoDto.setOdontologo(OdontologoDto.fromOdontologo(turnoBuscado.getOdontologo()));
            LOGGER.info("Se ha encontrado al turno con ID {}: {}", id, turnoDto);
        } else {
            LOGGER.info("El turno con ID {} no está registrado en la base de datos", id);
            throw new ResourceNotFoundException("El turno igresado no existe en la base de datos");
        }
        return turnoDto;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) throws BadRequestException {
        Turno turnoAActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoActualizadoDto = null;
        if (turnoAActualizar != null) {
            turnoAActualizar = turno;
            turnoActualizadoDto = objectMapper.convertValue(turnoAActualizar, TurnoDto.class);
            LOGGER.warn("El turno con ID {} ha sido actualizado: {}", turnoAActualizar.getId(), turnoActualizadoDto);
        } else {
            LOGGER.warn("No es posible actualizar el turno porque no está registrado en la base de datos");
            throw new BadRequestException("El turno no existe en la base de datos");
        }
        return turnoActualizadoDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con ID: {}", id);
        } else {
            LOGGER.warn("El turno con ID: " + id + "no se encuentra en la base de datos");
            throw new ResourceNotFoundException("El turno con ID: " + id + "no se encuentra en la base de datos");
        }
    }
}
