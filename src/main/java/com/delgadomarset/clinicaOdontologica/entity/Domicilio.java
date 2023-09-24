package com.delgadomarset.clinicaOdontologica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "DOMICILIOS")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = "😉 El nombre de la calle debe contener entre 2 y 50 caracteres.")
    @NotNull(message = "😉 El campo de calle no puede estar vacío ni ser nulo.")
    @NotBlank(message = "😉 El campo de calle no puede estar vacío ni ser nulo.")
    private String calle;


    private int numero;

    @Size(min = 2, max = 50, message = "😉 El nombre de la localidad debe contener entre 2 y 50 caracteres.")
    @NotNull(message = "😉 El campo de localidad no puede estar vacío ni ser nulo.")
    @NotBlank(message = "😉 El campo de localidad no puede estar vacío ni ser nulo.")
    private String localidad;

    @Size(min = 2, max = 50, message = "😉 El nombre de la provincia debe contener entre 2 y 50 caracteres.")
    @NotNull(message = "😉 El campo de provincia no puede estar vacío ni ser nulo.")
    @NotBlank(message = "😉 El campo de provincia no puede estar vacío ni ser nulo.")
    private String provincia;

    public Domicilio() {
    }


    public Domicilio(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }


    public Long getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

}
