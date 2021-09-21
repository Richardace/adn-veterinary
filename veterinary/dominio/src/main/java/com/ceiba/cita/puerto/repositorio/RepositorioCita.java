package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;

public interface RepositorioCita {
    /**
     * Permite crear una cita
     * @param cita
     * @return el id generado
     */
    Long crear(Cita cita);

    /**
     * Permite actualizar una cita
     * @param cita
     */
    void actualizar(Cita cita);


}
