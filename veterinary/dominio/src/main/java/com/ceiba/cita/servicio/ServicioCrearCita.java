package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionFechaNoValida;
import com.ceiba.dominio.excepcion.ExcepcionFechaOcupada;
import com.ceiba.dominio.excepcion.ExcepcionLimiteCitasDia;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ServicioCrearCita {

    private static final double VALOR_CITA_ENTRE_SEMANA = 150000;
    private static final double VALOR_CITA_FIN_DE_SEMANA = 230000;

    private static final String HORARIO_FIN_DE_SEMANA = "EL HORARIO LOS FINES DE SEMANA ES 8 AM - 6 PM";
    private static final String HORARIO_ENTRE_SEMANA = "EL HORARIO ENTRE SEMANA ES 6 PM - 10 PM";
    private static final String FECHA_OCUPADA = "A ESTA HORA EL DOCTOR TIENE OTRA CITA";
    private static final String LIMITE_CITAS_DIA = "SE EXCEDIO EL LIMITE DE CITAS AL DIA";

    private final RepositorioCita repositorioCita;
    private final DaoCita daoCita;

    public ServicioCrearCita(RepositorioCita repositorioCita,
                             DaoCita daoCita) {
        this.repositorioCita = repositorioCita;
        this.daoCita = daoCita;
    }

    public Long ejecutar(Cita cita) {
        LocalDate fechaCita = convertirFecha(cita.getFecha());
        validarHoraCitaValida(fechaCita, cita.getHora());
        validarDisponibilidadCita(cita.getFecha(), cita.getHora());
        validarLimiteCitas(cita.getFecha(), cita.getIdUsuario());
        cita.setPrecio(calcularCostoCita(fechaCita));
        return this.repositorioCita.crear(cita);
    }
    public void validarLimiteCitas(String fechaCita, Long idUsuario){
        if(this.daoCita.findCitasByFechaAndUsuario(fechaCita, idUsuario)){
            throw new ExcepcionLimiteCitasDia(LIMITE_CITAS_DIA);
        }
    }

    public void validarDisponibilidadCita(String fechaCita, int horaCita){
        if(this.daoCita.findCitaByFechaAndHora(fechaCita, horaCita)){
            throw new ExcepcionFechaOcupada(FECHA_OCUPADA);
        }
    }

    public void validarHoraCitaValida(LocalDate fechaCita, int hora){
        if(esFinDeSemana(fechaCita) && (hora < 800 || hora > 1800) ){
            throw new ExcepcionFechaNoValida(HORARIO_FIN_DE_SEMANA);
        }else if ((!esFinDeSemana(fechaCita)) && (hora < 1800 || hora > 2200)){
            throw new ExcepcionFechaNoValida(HORARIO_ENTRE_SEMANA);
        }
    }

    public double calcularCostoCita(LocalDate fecha){
        return esFinDeSemana(fecha) ? VALOR_CITA_FIN_DE_SEMANA : VALOR_CITA_ENTRE_SEMANA;
    }

    public boolean esFinDeSemana(LocalDate fecha){
        if ((fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            return true;
        }
        return false;
    }

    public LocalDate convertirFecha(String fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaUsuario = LocalDate.parse(fecha, formato);
        return fechaUsuario;
    }
}
