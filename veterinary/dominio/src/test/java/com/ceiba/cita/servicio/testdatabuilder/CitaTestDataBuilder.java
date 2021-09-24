package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.usuario.modelo.entidad.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class CitaTestDataBuilder {

    private Long id;
    private Long idUsuario;
    private LocalDateTime fecha;
    private Integer hora;
    private Double precio;
    private String notas;

    public CitaTestDataBuilder() {
        this.id = 1L;
        this.idUsuario = 1L;
        this.fecha = LocalDateTime.of(2021, Month.SEPTEMBER, 26, 00, 00, 00);
        this.hora = 1500;
        this.notas = "Paciente en Perfecto Estado";
    }

    public CitaTestDataBuilder sinIdUsuario() {
        this.idUsuario = null;
        return this;
    }

    public CitaTestDataBuilder sinFecha() {
        this.fecha = null;
        return this;
    }

    public CitaTestDataBuilder sinHora() {
        this.hora = null;
        return this;
    }

    public CitaTestDataBuilder sinNotas() {
        this.notas = null;
        return this;
    }

    public Cita build() {
        return new Cita(
                idUsuario,
                fecha,
                hora,
                notas
        );
    }
}
