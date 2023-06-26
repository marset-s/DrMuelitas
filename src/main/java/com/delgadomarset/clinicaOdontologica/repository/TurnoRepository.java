package com.delgadomarset.clinicaOdontologica.repository;

import com.delgadomarset.clinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
