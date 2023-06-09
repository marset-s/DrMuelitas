package com.delgadomarset.clinicaOdontologica.service.impl;



import com.delgadomarset.clinicaOdontologica.dto.PacienteDto;

import com.delgadomarset.clinicaOdontologica.repository.IDao;
import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import com.delgadomarset.clinicaOdontologica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PacienteService implements IPacienteService {
    private IDao<Paciente> pacienteIDao;
    private ObjectMapper objectMapper;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao, ObjectMapper objectMapper) {
        this.pacienteIDao = pacienteIDao;
        this.objectMapper = objectMapper;
    }

    public PacienteDto guardarPaciente(Paciente paciente) {
        return PacienteDto.fromPaciente(pacienteIDao.guardar(paciente));
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) {
        return PacienteDto.fromPaciente(pacienteIDao.actualizar(paciente));
    }

    public PacienteDto buscarPacientePorId(int id) {
        return PacienteDto.fromPaciente(pacienteIDao.buscarPorId(id));
    }

    @Override
    public List<PacienteDto> listarPacientes() {
        List<Paciente> pacienteList = pacienteIDao.listarTodos();
        return pacienteList.stream().map(PacienteDto::fromPaciente).toList();
    }

    @Override
    public PacienteDto buscarPacientePorDni(String dni) {
        return PacienteDto.fromPaciente(pacienteIDao.buscarPorCriterio(dni));
    }

    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }

}
