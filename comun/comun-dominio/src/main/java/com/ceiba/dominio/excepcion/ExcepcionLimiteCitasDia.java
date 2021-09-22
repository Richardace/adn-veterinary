package com.ceiba.dominio.excepcion;

public class ExcepcionLimiteCitasDia extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionLimiteCitasDia(String mensaje) {
        super(mensaje);
    }
}
