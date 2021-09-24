package com.ceiba.cita.entidad;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

import java.time.LocalDateTime;
import java.time.Month;

public class CitaTest {

    @Test
    public void validarSinIdentificadorUsuarioObligatorioTest() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().sinIdUsuario();
        BasePrueba.assertThrows(citaTestDataBuilder::build, ExcepcionValorObligatorio.class, "Se debe ingresar el usuario");
    }

    @Test
    public void validarSinFechaObligatorioTest() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().sinFecha();
        BasePrueba.assertThrows(citaTestDataBuilder::build, ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de la cita");
    }
    @Test
    public void validarSinHoraObligatorioTest() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().sinHora();
        BasePrueba.assertThrows(citaTestDataBuilder::build, ExcepcionValorObligatorio.class, "Se debe ingresar la hora de la cita");
    }

    @Test
    public void validarSinNotasObligatorioTest() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().sinNotas();
        BasePrueba.assertThrows(citaTestDataBuilder::build, ExcepcionValorObligatorio.class, "Ingresa el nombre de tu mascota");
    }


}
