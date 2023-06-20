package com.delgadomarset.clinicaOdontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private Long id;
    private LocalDateTime fechaHora;
    private String pacienteDto;
    private String odontologoDto;


    public TurnoDto() {
    }

    public TurnoDto(Long id, LocalDateTime fechaHora, String pacienteDto, String odontologoDto) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.pacienteDto = pacienteDto;
        this.odontologoDto = odontologoDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getPacienteDto() {
        return pacienteDto;
    }

    public void setPacienteDto(String pacienteDto) {
        this.pacienteDto = pacienteDto;
    }

    public String getOdontologoDto() {
        return odontologoDto;
    }

    public void setOdontologoDto(String odontologoDto) {
        this.odontologoDto = odontologoDto;
    }

    @Override
    public String toString() {
        return "TurnoDto{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", pacienteDto='" + pacienteDto + '\'' +
                ", odontologoDto='" + odontologoDto + '\'' +
                '}';
    }
}
