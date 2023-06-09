package com.delgadomarset.clinicaOdontologica.service.impl;


import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;

import com.delgadomarset.clinicaOdontologica.repository.IDao;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.delgadomarset.clinicaOdontologica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoIDao;
    private ObjectMapper objectMapper;

    @Autowired
    public OdontologoService(IDao<Odontologo> odontologoIDao, ObjectMapper objectMapper) {
        this.odontologoIDao = odontologoIDao;
        this.objectMapper = objectMapper;
    }

    public OdontologoDto registrarOdontologo(Odontologo odontologo) {
        return OdontologoDto.fromOdontologo(odontologoIDao.guardar(odontologo)); //método static de la clase OdontologoDto
    }

    public OdontologoDto buscarOdontologoPorId(int id) {
        return OdontologoDto.fromOdontologo(odontologoIDao.buscarPorId(id));
    }

    public List<OdontologoDto> listarOdontologos() {
        List<Odontologo> odontologoList = odontologoIDao.listarTodos();
        return odontologoList.stream().map(OdontologoDto::fromOdontologo).toList(); //:: referencia al metodo (method refrence)
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {
        return OdontologoDto.fromOdontologo(odontologoIDao.actualizar(odontologo));
    }

    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);
    }
}
