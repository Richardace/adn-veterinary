package com.ceiba.dominio.excepcion;

public class ExcepcionFechaOcupada extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionFechaOcupada(String mensaje) {
        super(mensaje);
    }
}
