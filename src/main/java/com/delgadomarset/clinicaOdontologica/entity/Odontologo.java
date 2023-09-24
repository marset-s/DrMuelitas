package com.delgadomarset.clinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;


@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = " 😉 El campo de la matrícula no puede estar vacío ni ser nulo.")
    @Size(min = 8, max = 9, message = " La matrícula profesional debe contener entre 8 y 9 caracteres.")
    @NotNull(message = "😉 El campo de la matrícula no puede estar vacío ni ser nulo.")
    private String matricula;
    @Size(min = 2, max = 50, message = "😉 El nombre debe contener entre 2 y 50 caracteres.")
    @NotNull(message = "😉 El campo de nombre no puede estar vacío ni ser nulo.")
    @NotBlank(message = "😉El campo de nombre no puede estar vacío ni ser nulo.")
    private String nombre;

    @Size(min = 2, max = 50, message = "😉 El apellido debe contener entre 2 y 50 caracteres.")
    @NotNull(message = "😉 El campo de apellido no puede estar vacío ni ser nulo.")
    @NotBlank(message = "😉El campo de apellido no puede estar vacío ni ser nulo.")
    private String apellido;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;

    public Odontologo() {
    }

    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

}
