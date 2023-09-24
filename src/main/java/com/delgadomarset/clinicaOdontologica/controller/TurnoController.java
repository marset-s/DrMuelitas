package com.delgadomarset.clinicaOdontologica.controller;

import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Turno;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.delgadomarset.clinicaOdontologica.service.impl.TurnoService;
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

@Tag(name = "Turnos")
@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;


    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //POST
    @Operation(summary = "Registrar un turno", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Turno creado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<TurnoDto> registrarTurno(@RequestBody @Valid Turno turno) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.registrarTurno(turno));
    }

    //GET
    @Operation(summary = "Buscar turno por id", responses = {
            @ApiResponse(responseCode = "200", description = "Turno obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, id no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.buscarTurnoPorId(id));
    }

    @Operation(summary = "Listado de todos los turnos", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Turnos obtenidos correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @GetMapping
    public List<TurnoDto> listarTurnos() {
        return turnoService.listarTodos();
    }

    //PUT
    @Operation(summary = "Actualizar un turno", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Turno actualizado con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, el turno no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno(@RequestBody @Valid Turno turno) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.actualizarTurno(turno));
    }

    //DELETE
    @Operation(summary = "Eliminar un turno", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Se eliminó al turno",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, el id no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Servidor no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("😉 Turno eliminado con éxito.");
    }


}

