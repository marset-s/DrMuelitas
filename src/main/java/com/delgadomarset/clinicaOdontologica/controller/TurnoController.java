package com.delgadomarset.clinicaOdontologica.controller;

import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Turno;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
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
    public ResponseEntity<TurnoDto> guardarTurno(@RequestBody Turno turno) throws BadRequestException {
        ResponseEntity<TurnoDto> respuesta;
        TurnoDto turnoDto = turnoService.registrarTurno(turno);
        if (turnoDto != null) respuesta = new ResponseEntity<>(turnoDto, null, HttpStatus.CREATED);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Se ha eliminado al turno");
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

