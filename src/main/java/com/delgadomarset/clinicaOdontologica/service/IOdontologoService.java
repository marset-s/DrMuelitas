package com.delgadomarset.clinicaOdontologica.service;


import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.delgadomarset.clinicaOdontologica.exception.BadRequestException;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;
    List<OdontologoDto> listarOdontologos();
    OdontologoDto registrarOdontologo(Odontologo odontologo) throws BadRequestException;
    OdontologoDto actualizarOdontologo(Odontologo odontologo) throws BadRequestException;
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
