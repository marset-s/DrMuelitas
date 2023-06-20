package com.delgadomarset.clinicaOdontologica.entity;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DOMICILIOS")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "El nombre de la calle debe tener hasta 50 caracteres")
    @NotNull
    private String calle;
    private int numero;

    @Size(max = 50, message = "El nombre de la localidad debe tener hasta 50 caracteres")
    @NotNull
    private String localidad;

    @Size(max = 50, message = "El nombre de la provincia debe tener hasta 50 caracteres")
    @NotNull
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

    /*
    @Override
    public String toString() {
        return "Id: " + id + " - Calle: " + calle + " - Numero: " + numero + " - Localidad: " + localidad + " - Provincia: " + provincia;
    }

     */
}
