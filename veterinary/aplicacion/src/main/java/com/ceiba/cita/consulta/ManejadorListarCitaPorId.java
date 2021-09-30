package com.ceiba.cita.consulta;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;

import java.util.List;

public class ManejadorListarCitaPorId {
    private final DaoCita daoCita;

    public ManejadorListarCitaPorId(DaoCita daoCita){
        this.daoCita = daoCita;
    }

    public List<DtoCita> ejecutar(long id){
        return this.daoCita.listarPorId(id);
    }
}
