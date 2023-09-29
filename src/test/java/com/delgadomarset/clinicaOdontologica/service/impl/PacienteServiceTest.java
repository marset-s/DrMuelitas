package com.delgadomarset.clinicaOdontologica.service.impl;

import com.delgadomarset.clinicaOdontologica.dto.DomicilioDto;
import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;
import com.delgadomarset.clinicaOdontologica.entity.Domicilio;
import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

    private final PacienteService pacienteService;
    private static Paciente paciente;


    @Autowired
    public PacienteServiceTest(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @BeforeAll
    public static void crearPaciente() {
        paciente = new Paciente(
                "Serrana",
                "Marset",
                "2222222222",
                LocalDate.of(2023, 10, 30),
                new Domicilio("Florida", 2908, "Canelones", "Canelones")
        );
    }

    @Test
    @Order(1)
    void deberiaaRegistarUnPaciente() throws BadRequestException {
        PacienteDto pacienteDto = pacienteService.registrarPaciente(paciente);
        Assertions.assertEquals("Serrana", paciente.getNombre());
        Assertions.assertEquals(1, (Long) pacienteDto.getId());
    }

    @Order(2)
    void deberiaActualizarPaciente() throws ResourceNotFoundException, BadRequestException {
        PacienteDto nuevoPaciente = new PacienteDto(
                1L,
                "Serrana",
                "Marset",
                "2222222222",
                LocalDate.of(2023, 10, 30),
                new DomicilioDto(1L, "Florida", 2908, "Canelones", "Canelones")
        );
        ObjectMapper mapper = new ObjectMapper();
        PacienteDto PacienteDto = pacienteService.actualizarPaciente(mapper.convertValue(nuevoPaciente, Paciente.class));
        Assertions.assertEquals(pacienteService.buscarPacientePorId(1L).getNombre(), "Ramona");
    }

    @Test
    @Order(3)
    void deberiaLanzarResourceNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPacientePorId(2L));
    }

    @Test
    @Order(4)
    void deberiaListarUnSoloPaciente() {
        List<PacienteDto> pacienteDtos = pacienteService.listarPacientes();
        assertEquals(1, pacienteDtos.size());
    }

    @Test
    @Order(5)
    void deberiaEliminarElPaciente1() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }
}