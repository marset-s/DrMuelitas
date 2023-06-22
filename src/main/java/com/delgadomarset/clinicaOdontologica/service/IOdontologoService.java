package com.delgadomarset.clinicaOdontologica.service;


import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.delgadomarset.clinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(Long id);
    List<OdontologoDto> listarOdontologos();
    OdontologoDto registrarOdontologo(Odontologo odontologo);
    OdontologoDto actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
