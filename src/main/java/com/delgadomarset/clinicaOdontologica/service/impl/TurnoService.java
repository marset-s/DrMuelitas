package com.delgadomarset.clinicaOdontologica.service.impl;

import com.delgadomarset.clinicaOdontologica.dto.TurnoDto;
import com.delgadomarset.clinicaOdontologica.entity.Turno;
import com.delgadomarset.clinicaOdontologica.repository.IDao;
import com.delgadomarset.clinicaOdontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private IDao<Turno> turnoIDao;
    private ObjectMapper objectMapper;

    @Autowired
    public TurnoService(IDao<Turno> turnoIDao, ObjectMapper objectMapper) {
        this.turnoIDao = turnoIDao;
        this.objectMapper = objectMapper;
    }

    @Override
    public TurnoDto guardarTurno(Turno turno) {
        return TurnoDto.fromTurno(turnoIDao.guardar(turno)); //método static de la clase turnodto
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<Turno> turnoList = turnoIDao.listarTodos();
        //List<TurnoDto> turnoDtoList = new ArrayList<>();
        //for (Turno t : turnoList){
           // turnoDtoList.add(TurnoDto.fromTurno(t));
        //}
        //return null;

        return turnoList.stream().map(TurnoDto::fromTurno).toList(); //:: referencia al metodo (method refrence)
    }

    @Override
    public TurnoDto buscarPorId(int id) {
        return TurnoDto.fromTurno( turnoIDao.buscarPorId(id));
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        return TurnoDto.fromTurno( turnoIDao.actualizar(turno));
    }

    @Override
    public void elimnarTurno(int id) {
        turnoIDao.eliminar(id);
    }


}
