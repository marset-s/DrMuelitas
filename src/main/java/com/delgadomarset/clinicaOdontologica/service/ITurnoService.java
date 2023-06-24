package com.delgadomarset.clinicaOdontologica.service;

import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Turno;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    TurnoDto registrarTurno(Turno turno) throws BadRequestException;
    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;
    TurnoDto actualizarTurno(Turno turno) throws BadRequestException;
    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
