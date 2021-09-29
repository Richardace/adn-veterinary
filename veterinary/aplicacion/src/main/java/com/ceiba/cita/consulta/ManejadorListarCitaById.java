package com.ceiba.cita.consulta;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;

import java.util.List;

public class ManejadorListarCitaById {
    private final DaoCita daoCita;

    public ManejadorListarCitaById(DaoCita daoCita){
        this.daoCita = daoCita;
    }

    public List<DtoCita> ejecutar(long id){
        return this.daoCita.listarById(id);
    }
}
