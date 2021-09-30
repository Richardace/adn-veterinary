package com.ceiba.cita.modelo.util;

import com.ceiba.dominio.excepcion.ExcepcionFechaNoValida;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class CitaUtil {

    private static final double VALOR_CITA_ENTRE_SEMANA = 150000;
    private static final double VALOR_CITA_FIN_DE_SEMANA = 230000;

    private static final String HORARIO_FIN_DE_SEMANA = "EL HORARIO LOS FINES DE SEMANA ES 8 AM - 6 PM";
    private static final String HORARIO_ENTRE_SEMANA = "EL HORARIO ENTRE SEMANA ES 6 PM - 10 PM";

    public static void validarHoraCitaValida(LocalDate fechaCita, int hora){
        if(esFinDeSemana(fechaCita) && (hora < 800 || hora > 1800) ){
            throw new ExcepcionFechaNoValida(HORARIO_FIN_DE_SEMANA);
        }else if ((!esFinDeSemana(fechaCita)) && (hora < 1800 || hora > 2200)){
            throw new ExcepcionFechaNoValida(HORARIO_ENTRE_SEMANA);
        }
    }

    public static double calcularCostoCita(LocalDate fecha){
        return esFinDeSemana(fecha) ? VALOR_CITA_FIN_DE_SEMANA : VALOR_CITA_ENTRE_SEMANA;
    }

    public static boolean esFinDeSemana(LocalDate fecha){
         return (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

}
