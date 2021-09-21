package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;


public class ServicioCrearCita {

    private final RepositorioCita repositorioCita;

    public ServicioCrearCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public Long ejecutar(Cita cita) {
        return this.repositorioCita.crear(cita);
    }
}
