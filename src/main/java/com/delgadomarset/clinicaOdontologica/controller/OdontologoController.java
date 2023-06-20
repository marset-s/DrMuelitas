package com.delgadomarset.clinicaOdontologica.controller;

import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.delgadomarset.clinicaOdontologica.service.impl.OdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;


    @Autowired
    public OdontologoController(OdontologoService odontologoService, ObjectMapper objectMapper) {
        this.odontologoService = odontologoService;

    }


    //GET
    @GetMapping
    public List<OdontologoDto> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) {
        ResponseEntity<OdontologoDto> response = ResponseEntity.badRequest().build();
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(id);
        if (odontologoDto != null) response = ResponseEntity.ok(odontologoDto);
        return response;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDto> registrarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<OdontologoDto> response = ResponseEntity.badRequest().build();
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        if (odontologoDto != null) response = ResponseEntity.status(HttpStatus.CREATED).body(odontologoDto);
        return response;
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<OdontologoDto> response = ResponseEntity.badRequest().build();
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(odontologo);
        if (odontologoDto != null) response = ResponseEntity.ok(odontologoDto);
        return response;
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {
        ResponseEntity<?> response = ResponseEntity.notFound().build();
        if (buscarOdontologoPorId(id).getStatusCode().is2xxSuccessful()) {
            odontologoService.eliminarOdontologo(id);
            response = ResponseEntity.ok().build();
        }
        return response;
    }


}
