package com.delgadomarset.clinicaOdontologica.controller;


import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;
import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.delgadomarset.clinicaOdontologica.service.impl.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pacientes")
@CrossOrigin
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @Operation(summary = "Registrar un paciente", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Paciente creado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<PacienteDto> registrarPaciente(@RequestBody @Valid Paciente paciente) throws BadRequestException {
        PacienteDto pacienteGuardado = pacienteService.registrarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
    }


    //GET
    @Operation(summary = "Buscar paciente por id", responses = {
            @ApiResponse(responseCode = "200", description = "Paciente obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, id no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        PacienteDto pacienteEncontrado = pacienteService.buscarPacientePorId(id);
        return ResponseEntity.ok(pacienteEncontrado);
    }


    @Operation(summary = "Listado de todos los pacientes", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Pacientes obtenidos correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @GetMapping
    public List<PacienteDto> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    //PUT
    @Operation(summary = "Actualizar un paciente", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Paciente actualizado con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, el paciente no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody @Valid Paciente paciente) throws ResourceNotFoundException, BadRequestException {
        PacienteDto pacienteEncontrado = pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok(pacienteEncontrado);
    }

    //DELETE
    @Operation(summary = "Eliminar un paciente", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Se eliminó al paciente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, el id no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Servidor no encontrado",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("😉 Paciente eliminado con éxito.");
    }

}


