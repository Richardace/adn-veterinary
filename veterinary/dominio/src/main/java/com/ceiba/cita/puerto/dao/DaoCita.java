package com.ceiba.cita.puerto.dao;

import com.ceiba.cita.modelo.dto.DtoCita;

import java.util.List;

public interface DaoCita {

    /**
     * Permite listar citas
     * @return las citas
     */
    List<DtoCita> listar();

    Boolean findCitaByFechaAndHora(String fecha, int hora);

    boolean findCitasByFechaAndUsuario(String fecha, Long idUsuario);
}
