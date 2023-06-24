package com.delgadomarset.clinicaOdontologica.service;


import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;
import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    List<PacienteDto> listarPacientes();

    PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException;

    PacienteDto registrarPaciente(Paciente paciente) throws BadRequestException;

    PacienteDto actualizarPaciente(Paciente paciente) throws BadRequestException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

}
