package com.delgadomarset.clinicaOdontologica.service;

import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Turno;

import java.util.List;

public interface ITurnoService {

    TurnoDto guardarTurno(Turno turno);
    List<TurnoDto> listarTodos();

    TurnoDto buscarPorId(int id);
    TurnoDto actualizarTurno(Turno turno);
    void elimnarTurno(int id);
}
