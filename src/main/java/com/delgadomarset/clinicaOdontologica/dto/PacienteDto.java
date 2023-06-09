package com.delgadomarset.clinicaOdontologica.dto;

import com.delgadomarset.clinicaOdontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {

    private String nombre;
    private String apellido;

    public PacienteDto() {
    }

    public PacienteDto(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public static PacienteDto fromPaciente(Paciente paciente){
        String nombre = "Paciente: " + paciente.getNombre();
        String apellido = "Apellido " + paciente.getApellido();
        return new PacienteDto(nombre, apellido);
    }

    @Override
    public String toString() {
        return "PacienteDto{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
