package com.delgadomarset.clinicaOdontologica.controller;

import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Turno;
import com.delgadomarset.clinicaOdontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;


    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //GET
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) {
        ResponseEntity<TurnoDto> response = ResponseEntity.badRequest().build();
        TurnoDto turnoDto = turnoService.buscarTurnoPorId(id);
        if (turnoDto != null) response = ResponseEntity.ok(turnoDto);
        return response;
    }

    @GetMapping
    public List<TurnoDto> listarTurnos() {
        return turnoService.listarTodos();
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<TurnoDto> guardarTurno(@RequestBody Turno turno) {
        ResponseEntity<TurnoDto> response = ResponseEntity.badRequest().build();
        TurnoDto turnoDto = turnoService.registrarTurno(turno);
        if (turnoDto != null) response = ResponseEntity.status(HttpStatus.CREATED).body(turnoDto);
        return response;
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) {
        ResponseEntity<?> response = ResponseEntity.notFound().build();
        if (buscarTurnoPorId(id).getStatusCode().is2xxSuccessful()) {
            turnoService.eliminarTurno(id);
            response = ResponseEntity.ok().build();
        }
        return response;
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno(@RequestBody Turno turno) {
        ResponseEntity<TurnoDto> response = ResponseEntity.badRequest().build();
        TurnoDto turnoDto = turnoService.actualizarTurno(turno);
        if (turnoDto != null) response = ResponseEntity.ok(turnoDto);
        return response;
    }
}

