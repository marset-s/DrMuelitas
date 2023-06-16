package com.delgadomarset.clinicaOdontologica.repository;

import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteReposirtory extends JpaRepository <Paciente, Long>{
}
