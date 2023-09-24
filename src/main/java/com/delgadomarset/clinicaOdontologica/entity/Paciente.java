package com.delgadomarset.clinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "PACIENTES")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = " 😜 El nombre debe contener entre 2 y 50 caracteres.")
    @NotNull(message = "😜 campo de nombre no puede estar vacío ni ser nulo.")
    @NotBlank(message = "😜 campo de nombre no puede estar vacío ni ser nulo.")
    private String nombre;


    @Size(min = 2, max = 50, message = "😜 El apellido debe contener entre 2 y 50 caracteres.")
    @NotNull(message = "😜 campo de apellido no puede estar vacío ni ser nulo.")
    @NotBlank(message = "😜")
    private String apellido;


    @Pattern(regexp = "\\d+", message = "😜 El DNI debe contener solo números")
    @Size(max = 12)
    @NotBlank(message = "😜 El campo del dni no puede estar vacío ni ser nulo")
    private String dni;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "😜 La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "😜 El campo de la fecha de ingreso del paciente no puede estar vacío")
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;


    public Paciente() {
    }


    public Paciente(String nombre, String apellido, String dni, LocalDate fechaIngreso, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }


    public Long getId() {
        return id;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }


}
