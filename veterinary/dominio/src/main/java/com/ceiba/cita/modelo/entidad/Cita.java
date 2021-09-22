package com.ceiba.cita.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cita {

    private Long id;
    private Long idUsuario;
    private String fecha;
    private Integer hora;
    private Double precio;
    private String notas;

    public Cita(Long id, Long idUsuario, String fecha, Integer hora, String notas) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.notas = notas;
    }

    public Cita(Long id, Long idUsuario, String fecha, Integer hora, Double precio, String notas) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.notas = notas;
    }
}
