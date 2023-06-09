package com.delgadomarset.clinicaOdontologica.service;


import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(int id);
    List<OdontologoDto> listarOdontologos();
    OdontologoDto registrarOdontologo(Odontologo odontologo);
    OdontologoDto actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(int id);
}
