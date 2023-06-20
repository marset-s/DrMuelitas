package com.delgadomarset.clinicaOdontologica.service;


import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;
import com.delgadomarset.clinicaOdontologica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    List<PacienteDto> listarPacientes();
    PacienteDto buscarPacientePorId(Long id);

    PacienteDto guardarPaciente(Paciente paciente);
    PacienteDto actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);

}
