package com.ceiba.dominio.excepcion;

public class ExcepcionFechaNoValida extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionFechaNoValida(String mensaje) {
        super(mensaje);
    }
}
