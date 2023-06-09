package com.delgadomarset.clinicaOdontologica.dto;

import com.delgadomarset.clinicaOdontologica.entity.Domicilio;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioDto {

    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    public DomicilioDto() {
    }

    public DomicilioDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public static DomicilioDto fromDomicilio(Domicilio domicilio){
        String calle = "Calle: " + domicilio.getCalle();
        int numero = Integer.parseInt("Numero: " + domicilio.getNumero());
        String localidad = "Localidad " + domicilio.getLocalidad();
        String provincia = "Provincia " + domicilio.getProvincia();
        return new DomicilioDto(calle, numero, localidad, provincia);
    }

    @Override
    public String toString() {
        return "DomicilioDto{" +
                "calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
