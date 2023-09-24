package com.delgadomarset.clinicaOdontologica.controller;

import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;
import com.delgadomarset.clinicaOdontologica.service.impl.OdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Tag(name = "Odontologos")
@CrossOrigin
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;


    @Autowired
    public OdontologoController(OdontologoService odontologoService, ObjectMapper objectMapper) {
        this.odontologoService = odontologoService;

    }

    //POST
    @Operation(summary = "Registrar un odontólogo", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Odontólogo creado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDto> resgistrarOdontologo(@Valid @RequestBody Odontologo odontologo) throws BadRequestException {
        OdontologoDto odontologoGuardado = odontologoService.registrarOdontologo(odontologo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(odontologoGuardado);
    }

    //GET
    @Operation(summary = "Listado de todos los odontólogos", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Odontólogos obtenidos correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })

    @GetMapping
    public List<OdontologoDto> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }


    @Operation(summary = "Buscar odontólogo por id", responses = {
            @ApiResponse(responseCode = "200", description = "Odontólogo obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, id no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        OdontologoDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);
        return ResponseEntity.ok(odontologoEncontrado);
    }

    //PUT
    @Operation(summary = "Actualizar un odontólogo", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Odontólogo actualizado con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, el odontólogo no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody @Valid Odontologo odontologo) throws ResourceNotFoundException, BadRequestException {
        OdontologoDto odontologoActualizado = odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.ok(odontologoActualizado);
    }

    //DELETE
    @Operation(summary = "Eliminar un odontólogo", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Se eliminó al odontólogo",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, el id no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Servidor no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Se ha eliminado al odontólogo con éxito ☺️");
    }

}
