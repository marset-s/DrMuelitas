package com.delgadomarset.clinicaOdontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private Long id;
    private LocalDateTime fechaHora;
    private PacienteDto paciente;
    private OdontologoDto odontologo;


    public TurnoDto() {
    }

    public TurnoDto(Long id, LocalDateTime fechaHora, PacienteDto pacienteDto, OdontologoDto odontologoDto) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.paciente = pacienteDto;
        this.odontologo = odontologoDto;
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

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public OdontologoDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDto odontologo) {
        this.odontologo = odontologo;
    }

    @Override
    public String toString() {
        return "TurnoDto{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", paciente='" + paciente + '\'' +
                ", odontologo='" + odontologo + '\'' +
                '}';
    }
}
