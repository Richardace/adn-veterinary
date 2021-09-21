package com.ceiba.cita.consulta;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarCita {

    private final DaoCita daoCita;

    public ManejadorListarCita(DaoCita daoCita){
        this.daoCita = daoCita;
    }

    public List<DtoCita> ejecutar(){ return this.daoCita.listar(); }
}
