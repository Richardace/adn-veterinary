package com.ceiba.cita.comando.fabrica;

import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import org.springframework.stereotype.Component;

import com.ceiba.usuario.comando.ComandoUsuario;

@Component
public class FabricaCita {

    public Cita crear(ComandoCita comandoCita) {
        return new Cita(
                comandoCita.getId(),
                comandoCita.getIdUsuario(),
                comandoCita.getFecha(),
                comandoCita.getNotas()
        );
    }

}