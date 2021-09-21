package com.ceiba.cita.modelo.entidad;

public class Cita {

    private Long id;
    private Long idUsuario;
    private String fecha;
    private String notas;

    public Cita(Long id, Long idUsuario, String fecha, String notas) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.notas = notas;
    }
}
