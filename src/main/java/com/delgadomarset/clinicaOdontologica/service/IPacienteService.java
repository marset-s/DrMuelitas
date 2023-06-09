package com.delgadomarset.clinicaOdontologica.service;


import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;
import com.delgadomarset.clinicaOdontologica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    List<PacienteDto> listarPacientes();
    PacienteDto buscarPacientePorDni(String dni);

    PacienteDto buscarPacientePorId(int id);

    PacienteDto guardarPaciente(Paciente paciente);
    PacienteDto actualizarPaciente(Paciente paciente);
    void eliminarPaciente(int id);

}
