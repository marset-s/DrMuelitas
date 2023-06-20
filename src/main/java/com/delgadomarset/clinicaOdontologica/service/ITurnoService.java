package com.delgadomarset.clinicaOdontologica.service;

import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Turno;

import java.util.List;

public interface ITurnoService {

    TurnoDto registrarTurno(Turno turno);
    List<TurnoDto> listarTodos();

    TurnoDto buscarPorId(Long id);
    TurnoDto actualizarTurno(Turno turno);
    void elimnarTurno(Long id);
}
