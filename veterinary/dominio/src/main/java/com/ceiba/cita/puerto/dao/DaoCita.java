package com.ceiba.cita.puerto.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;

import java.time.LocalDate;
import java.util.List;

public interface DaoCita {

    /**
     * Permite listar citas
     * @return las citas
     */
    List<DtoCita> listar();

    Boolean buscarCitaPorFechaYHora(Cita cita);
    boolean buscarCitaPorFechaYHoraDescartandoId(Cita cita);

    boolean buscarCitaPorFechaYUsuario(Cita cita);
    Boolean buscarCitaPorFechaYUsuarioDescartandoId(Cita cita);

    List<DtoCita> listarPorId(long id);

}
