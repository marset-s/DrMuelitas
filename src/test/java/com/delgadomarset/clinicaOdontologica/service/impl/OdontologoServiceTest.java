package com.delgadomarset.clinicaOdontologica.service.impl;

import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OdontologoServiceTest {

    private final OdontologoService odontologoService;

    @Autowired
    OdontologoServiceTest(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @BeforeEach
    public void crearOdontologo() throws BadRequestException {
        Odontologo odontologo = new Odontologo(
                "99554877",
                "Luciana",
                "Murga"
        );
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
    }

    @Test
    void deberiaRegistrarUnOdontologo() throws ResourceNotFoundException {
        Assertions.assertEquals(odontologoService.buscarOdontologoPorId(1L).getNombre(), "Luciana");
        Assertions.assertEquals(1L, (long) odontologoService.buscarOdontologoPorId(1L).getId());
    }

    @Test
    void buscarOdontologoPorId() {
    }

    @Test
    void listarOdontologos() {
    }

    @Test
    void actualizarOdontologo() throws ResourceNotFoundException, BadRequestException {
        OdontologoDto nuevoOdontologo = new OdontologoDto(
                1L,
                "32554669",
                "Ramona",
                "Santana"
        );
        ObjectMapper mapper = new ObjectMapper();
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(mapper.convertValue(nuevoOdontologo, Odontologo.class));
        Assertions.assertEquals(odontologoService.buscarOdontologoPorId(1L).getNombre(), "Ramona");

    }

    @Test
    void eliminarOdontologo() {
    }
}