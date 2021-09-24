package com.ceiba.cita.puerto.dao;

import com.ceiba.cita.modelo.dto.DtoCita;

import java.time.LocalDate;
import java.util.List;

public interface DaoCita {

    /**
     * Permite listar citas
     * @return las citas
     */
    List<DtoCita> listar();

    Boolean findCitaByFechaAndHora(LocalDate fecha, int hora);

    boolean findCitasByFechaAndUsuario(LocalDate fecha, Long idUsuario);
}
