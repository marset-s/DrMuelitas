package com.delgadomarset.clinicaOdontologica.service.impl;

import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    private final OdontologoService odontologoService;
    private static Odontologo odontologo;

    @Autowired
    OdontologoServiceTest(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @BeforeAll
    public static void crearOdontologo() {
        odontologo = new Odontologo(
                "522222222",
                "Luciana",
                "Murga"
        );
    }

    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologo() throws ResourceNotFoundException, BadRequestException {
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        assertEquals("Luciana", odontologoDto.getNombre());
        assertEquals(1L, (long) odontologoDto.getId());
    }


    @Test
    @Order(2)
    void deberiaActualizarOdontologo() throws ResourceNotFoundException, BadRequestException {
        OdontologoDto nuevoOdontologo = new OdontologoDto(
                1L,
                "333445553",
                "Ramona",
                "Santana"
        );
        ObjectMapper mapper = new ObjectMapper();
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(mapper.convertValue(nuevoOdontologo, Odontologo.class));
        assertEquals(odontologoService.buscarOdontologoPorId(1L).getNombre(), "Ramona");
    }

    @Test
    @Order(3)
    void deberiaListarUnSoloOdontologo() {
        List<OdontologoDto> odontologoDto = odontologoService.listarOdontologos();
        assertEquals(1, odontologoDto.size());
    }

    @Test
    @Order(4)
    void deberiaLanzarResourceNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarOdontologoPorId(2L));
    }

    @Test
    @Order(5)
    void cuandoNoSeCumplaElFormatoDeMatricula_noDeberiaRegistrarElOdontologo() throws BadRequestException {
        Odontologo odontologo = new Odontologo(
                "99",
                "Luciana",
                "Murga"
        );
        Assertions.assertThrows(ConstraintViolationException.class, () -> odontologoService.registrarOdontologo(odontologo));
    }

    @Test
    @Order(6)
    void deberiaEliminarElOdontologoId1() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }
}