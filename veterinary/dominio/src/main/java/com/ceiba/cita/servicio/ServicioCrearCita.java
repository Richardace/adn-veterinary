package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.util.CitaUtil;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionFechaNoValida;
import com.ceiba.dominio.excepcion.ExcepcionFechaOcupada;
import com.ceiba.dominio.excepcion.ExcepcionLimiteCitasDia;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ServicioCrearCita {

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
        LocalDate fechaCita = cita.getFecha().toLocalDate();
        CitaUtil.validarHoraCitaValida(fechaCita, cita.getHora());
        validarDisponibilidadCita(fechaCita, cita.getHora());
        validarLimiteCitas(fechaCita, cita.getIdUsuario());
        cita.setPrecio(CitaUtil.calcularCostoCita(fechaCita));
        return this.repositorioCita.crear(cita);
    }

    public void validarLimiteCitas(LocalDate fechaCita, Long idUsuario){
        if(this.daoCita.findCitasByFechaAndUsuario(fechaCita, idUsuario)){
            throw new ExcepcionLimiteCitasDia(LIMITE_CITAS_DIA);
        }
    }

    public void validarDisponibilidadCita(LocalDate fechaCita, int horaCita){
        if(this.daoCita.findCitaByFechaAndHora(fechaCita, horaCita)){
            throw new ExcepcionFechaOcupada(FECHA_OCUPADA);
        }
    }


}
