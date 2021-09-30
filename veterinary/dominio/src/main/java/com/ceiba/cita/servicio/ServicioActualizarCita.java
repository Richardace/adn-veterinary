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

public class ServicioActualizarCita {

    private static final String FECHA_OCUPADA = "A ESTA HORA EL DOCTOR TIENE OTRA CITA";
    private static final String LIMITE_CITAS_DIA = "SE EXCEDIO EL LIMITE DE CITAS AL DIA";

    private final RepositorioCita repositorioCita;
    private final DaoCita daoCita;

    public ServicioActualizarCita(RepositorioCita repositorioCita, DaoCita daoCita) {
        this.repositorioCita = repositorioCita;
        this.daoCita = daoCita;
    }

    public void ejecutar(Cita cita) {
        LocalDate fechaCita = cita.getFecha().toLocalDate();
        CitaUtil.validarHoraCitaValida(fechaCita, cita.getHora());
        validarDisponibilidadCita(cita);
        validarLimiteCitas(cita);
        cita.setPrecio(CitaUtil.calcularCostoCita(fechaCita));
        this.repositorioCita.actualizar(cita);
    }

    private void validarLimiteCitas(Cita cita){
        if(this.daoCita.buscarCitaPorFechaYUsuarioDescartandoId(cita)){
            throw new ExcepcionLimiteCitasDia(LIMITE_CITAS_DIA);
        }
    }

    private void validarDisponibilidadCita(Cita cita){
        if(this.daoCita.buscarCitaPorFechaYHoraDescartandoId(cita)){
            throw new ExcepcionFechaOcupada(FECHA_OCUPADA);
        }
    }

}
