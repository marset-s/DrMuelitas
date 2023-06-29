package com.delgadomarset.clinicaOdontologica.service.impl;

import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Domicilio;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import com.delgadomarset.clinicaOdontologica.entity.Turno;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {

    private final TurnoService turnoService;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;
    private final ObjectMapper mapper;


    private static Paciente paciente;
    private static Odontologo odontologo;
    private static Turno turno;


    @Autowired
    TurnoServiceTest(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService, ObjectMapper mapper) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
        this.mapper = mapper;
    }

    @BeforeAll
    public static void crearTodasLasEntidades() {
        odontologo = new Odontologo(
                "995522222222",
                "Luciana",
                "Murga"
        );
        paciente = new Paciente(
                "Serrana",
                "Marset",
                "2222222222",
                LocalDate.of(2023, 07, 01),
                new Domicilio("Florida", 2908, "Canelones", "Canelones")
        );
        turno = new Turno(LocalDateTime.of(2023, 7, 1, 1, 0), odontologo, paciente);
    }


    @Test
    @Order(1)
    void deberiaRegistarUnTurno() throws BadRequestException, ResourceNotFoundException {
        pacienteService.registrarPaciente(paciente);
        odontologoService.registrarOdontologo(odontologo);
        TurnoDto turnoDto = turnoService.registrarTurno(turno);

        Assertions.assertEquals(LocalDateTime.of(2023, 7, 1, 1, 0), turno.getFechaHora());
        Assertions.assertEquals(1, (Long) turno.getId());
    }

    @Test
    @Order(2)
    void deberiaLanzarResourceNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarTurnoPorId(2L));
    }

    @Test
    @Order(3)
    void deberiaLanzarLaExcepcionConstraintViolationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> turnoService.registrarTurno(
                new Turno(LocalDateTime.of(2023, 6, 1, 1, 0), odontologo, paciente)));
    }

}