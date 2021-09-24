package com.ceiba.cita.testdatabuilder;

import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.usuario.comando.ComandoUsuario;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

public class ComandoCitaTestDataBuilder {

    private Long id;
    private Long idUsuario;
    private LocalDateTime fecha;
    private Integer hora;
    private Double precio;
    private String notas;

    public ComandoCitaTestDataBuilder() {
        this.id = 1L;
        this.idUsuario = 1L;
        this.fecha = LocalDateTime.of(2021, Month.SEPTEMBER, 26, 00, 00, 00);
        this.hora = 1500;
        this.notas = "Paciente en Perfecto Estado";
    }

    public ComandoCitaTestDataBuilder sinIdUsuario() {
        this.idUsuario = null;
        return this;
    }

    public ComandoCitaTestDataBuilder sinFecha() {
        this.fecha = null;
        return this;
    }

    public ComandoCitaTestDataBuilder sinHora() {
        this.hora = null;
        return this;
    }

    public ComandoCitaTestDataBuilder sinNotas() {
        this.notas = null;
        return this;
    }

    public ComandoCita build() {
        return new ComandoCita(
                idUsuario,
                fecha,
                hora,
                notas
        );
    }
}
