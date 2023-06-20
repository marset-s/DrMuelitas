package com.delgadomarset.clinicaOdontologica.service.impl;

import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;
import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Turno;
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
    private final ObjectMapper objectMapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public TurnoDto registrarTurno(Turno turno) {
        TurnoDto turnoDto = objectMapper.convertValue(turnoRepository.save(turno), TurnoDto.class);
        turnoDto.setPacienteDto(PacienteDto.fromPaciente(turno.getPaciente()));
        turnoDto.setOdontologoDto(OdontologoDto.fromOdontologo(turno.getOdontologo()));
        LOGGER.info("️Se guardó al turno: {}", turnoDto);
        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<TurnoDto> turnoDtos = turnoRepository
                .findAll()
                .stream()
                .map(turno -> {
                    TurnoDto turnoDto = objectMapper.convertValue(turno, TurnoDto.class);
                    turnoDto.setPacienteDto(PacienteDto.fromPaciente(turno.getPaciente()));
                    turnoDto.setOdontologoDto(OdontologoDto.fromOdontologo(turno.getOdontologo()));
                    return turnoDto;
                })
                .toList();
        LOGGER.info("🔢 Listando todos los turnos: {}", turnoDtos);
        return turnoDtos;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;
        if (turnoBuscado != null) {
            turnoDto = objectMapper.convertValue(turnoBuscado, TurnoDto.class);
            turnoDto.setPacienteDto(PacienteDto.fromPaciente(turnoBuscado.getPaciente()));
            turnoDto.setOdontologoDto(OdontologoDto.fromOdontologo(turnoBuscado.getOdontologo()));
            LOGGER.info("Se ha encontrado al turno con ID {}: {}", id, turnoDto);
        } else
            LOGGER.info("El turno con ID {} no está registrado en la base de datos", id);
        return turnoDto;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        Turno turnoAActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoActualizadoDto = null;
        if (turnoAActualizar != null) {
            turnoAActualizar = turno;
            turnoActualizadoDto = registrarTurno(turnoAActualizar);
            LOGGER.warn("El turno con ID {} ha sido actualizado: {}", turnoAActualizar.getId(), turnoActualizadoDto);
        } else
            LOGGER.warn("No es posible actualizar el turno porque no está registrado en la base de datos");
        return turnoActualizadoDto;
    }

    @Override
    public void eliminarTurno(Long id) {
        if (turnoRepository.existsById(id))
            turnoRepository.deleteById(id);
        LOGGER.info("Se ha eliminado el turno con ID: {}", id);
    }
}
