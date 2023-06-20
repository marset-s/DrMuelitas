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
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) {
        ResponseEntity<PacienteDto> response = ResponseEntity.badRequest().build();
        PacienteDto pacienteDto = pacienteService.buscarPacientePorId(id);
        if (pacienteDto != null) response = ResponseEntity.ok(pacienteDto);
        return response;
    }

    @GetMapping
    public List<PacienteDto> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<PacienteDto> guardarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<PacienteDto> response = ResponseEntity.badRequest().build();
        PacienteDto pacienteDto = pacienteService.registrarPaciente(paciente);
        if (pacienteDto != null) response = ResponseEntity.status(HttpStatus.CREATED).body(pacienteDto);
        return response;
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<PacienteDto> response = ResponseEntity.badRequest().build();
        PacienteDto pacienteDto = pacienteService.actualizarPaciente(paciente);
        if (pacienteDto != null) response = ResponseEntity.ok(pacienteDto);
        return response;
    }


    //DELETE
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) {
        ResponseEntity<?> response = ResponseEntity.notFound().build();
        if (buscarPacientePorId(id).getStatusCode().is2xxSuccessful()) {
            pacienteService.eliminarPaciente(id);
            response = ResponseEntity.ok().build();
        }
        return response;
    }

}


