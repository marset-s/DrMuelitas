package com.delgadomarset.clinicaOdontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private Long id;
    private LocalDateTime fechaHora;
    private PacienteDto pacienteDto;
    private OdontologoDto odontologoDto;


    public TurnoDto() {
    }

    public TurnoDto(Long id, LocalDateTime fechaHora, PacienteDto pacienteDto, OdontologoDto odontologoDto) {
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

    public PacienteDto getPacienteDto() {
        return pacienteDto;
    }

    public void setPacienteDto(PacienteDto pacienteDto) {
        this.pacienteDto = pacienteDto;
    }

    public OdontologoDto getOdontologoDto() {
        return odontologoDto;
    }

    public void setOdontologoDto(OdontologoDto odontologoDto) {
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
