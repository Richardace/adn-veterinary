package com.ceiba.cita.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Setter
@Getter
public class Cita {

    private static final String SE_DEBE_INGRESAR_EL_USUARIO = "Se debe ingresar el usuario";
    private static final String SE_DEBE_INGRESAR_LA_FECHA = "Se debe ingresar la fecha de la cita";
    private static final String NOTAS_VACIO = "Ingresa el nombre de tu mascota";
    private static final String SE_DEBE_INGRESAR_HORA = "Se debe ingresar la hora de la cita";

    private Long id;
    private Long idUsuario;
    private LocalDateTime fecha;
    private Integer hora;
    private Double precio;
    private String notas;

    public Cita(Long id, Long idUsuario, LocalDateTime fecha, Integer hora, String notas) {

        validarObligatorio(idUsuario, SE_DEBE_INGRESAR_EL_USUARIO);
        validarObligatorio(fecha, SE_DEBE_INGRESAR_LA_FECHA);
        validarObligatorio(hora, SE_DEBE_INGRESAR_HORA);
        validarObligatorio(notas, NOTAS_VACIO);

        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.notas = notas;
    }

    public Cita(Long id, Long idUsuario, LocalDateTime fecha, Integer hora, Double precio, String notas) {

        validarObligatorio(idUsuario, SE_DEBE_INGRESAR_EL_USUARIO);
        validarObligatorio(fecha, SE_DEBE_INGRESAR_LA_FECHA);
        validarObligatorio(hora, SE_DEBE_INGRESAR_HORA);
        validarObligatorio(notas, NOTAS_VACIO);

        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.notas = notas;
    }

    public Cita(Long idUsuario, LocalDateTime fecha, Integer hora, String notas) {

        validarObligatorio(idUsuario, SE_DEBE_INGRESAR_EL_USUARIO);
        validarObligatorio(fecha, SE_DEBE_INGRESAR_LA_FECHA);
        validarObligatorio(hora, SE_DEBE_INGRESAR_HORA);
        validarObligatorio(notas, NOTAS_VACIO);

        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.notas = notas;
    }

}
