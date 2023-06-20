package com.delgadomarset.clinicaOdontologica.controller;


import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;

import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import com.delgadomarset.clinicaOdontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //GET
    /*
    @GetMapping("/{dni}")
    public ResponseEntity<PacienteDto> buscarPacientePorDni(@PathVariable String dni){
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.buscarPacientePorDni(dni);
        if(pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.CREATED);
        else respuesta =  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

     */

    //GET
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id){
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.buscarPacientePorId(id);
        if(pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.CREATED);
        else respuesta =  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    @GetMapping
    public List<PacienteDto> listarPacientes(){
        return pacienteService.listarPacientes();
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<PacienteDto> guardarPaciente(@RequestBody Paciente paciente){
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        if(pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.CREATED);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody Paciente paciente){
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.actualizarPaciente(paciente);
        if(pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }


    //DELETE
    @DeleteMapping("eliminar/{id}")
    public void eliminarPaciente(@PathVariable Long id){
        pacienteService.eliminarPaciente(id);
    }

}
