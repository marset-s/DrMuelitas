package com.delgadomarset.clinicaOdontologica.service.impl;

import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                "995522222222",
                "Luciana",
                "Murga"
        );
    }


    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologo() throws ResourceNotFoundException, BadRequestException {
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        Assertions.assertEquals("Luciana", odontologoDto.getNombre());
        Assertions.assertEquals(1L, (long) odontologoDto.getId());
    }


    @Test
    @Order(2)
    void deberiaActualizarOdontologo() throws ResourceNotFoundException, BadRequestException {
        OdontologoDto nuevoOdontologo = new OdontologoDto(
                1L,
                "12312333445553",
                "Ramona",
                "Santana"
        );
        ObjectMapper mapper = new ObjectMapper();
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(mapper.convertValue(nuevoOdontologo, Odontologo.class));
        Assertions.assertEquals(odontologoService.buscarOdontologoPorId(1L).getNombre(), "Ramona");

    }

    @Test
    @Order(3)
    void deberiaLanzarResourceNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarOdontologoPorId(2L));
    }


}