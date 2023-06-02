package com.delgadomarset.clinicaOdontologica.service;


import com.delgadomarset.clinicaOdontologica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    List<Paciente> listarPacientes();
    Paciente buscarPacientePorDni(String dni);

}
