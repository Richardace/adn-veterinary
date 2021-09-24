package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.util.CitaUtil;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

import java.time.LocalDateTime;
import java.time.Month;

public class ServicioCrearCitaTest {

    private static final Double VALOR_CITA_ENTRE_SEMANA = 150000d;
    private static final Double VALOR_CITA_FIN_DE_SEMANA = 230000d;


    @Test
    public void validarPrecioCitaEntreSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 24, 00, 00, 00);
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        Double precioCita = CitaUtil.calcularCostoCita(fechaCitaPrueba.toLocalDate());

        Assert.assertEquals(VALOR_CITA_ENTRE_SEMANA, precioCita);
    }

    @Test
    public void validarPrecioCitaFinDeSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        Double precioCita = CitaUtil.calcularCostoCita(fechaCitaPrueba.toLocalDate());

        Assert.assertEquals(VALOR_CITA_FIN_DE_SEMANA, precioCita);
    }

    @Test
    public void validarFechaFinDeSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        boolean esFinDeSemana = CitaUtil.esFinDeSemana(fechaCitaPrueba.toLocalDate());

        Assert.assertEquals(true, esFinDeSemana);
    }

    @Test
    public void validarFechaEntreSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 24, 00, 00, 00);
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        boolean esFinDeSemana = CitaUtil.esFinDeSemana(fechaCitaPrueba.toLocalDate());

        Assert.assertEquals(false, esFinDeSemana);
    }

    @Test
    public void validarErrorPorLimiteDeCitasUsuarioTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();
        cita.setHora(1300);
        cita.setFecha(LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00));
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.findCitaByFechaAndHora(cita.getFecha().toLocalDate(), cita.getHora())).thenReturn(false);
        Mockito.when(daoCita.findCitasByFechaAndUsuario(cita.getFecha().toLocalDate(), 1L)).thenReturn(true);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionLimiteCitasDia.class, "SE EXCEDIO EL LIMITE DE CITAS AL DIA");
    }

    @Test
    public void validarErrorPorDisponibilidadCitasTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();
        cita.setHora(1300);
        cita.setFecha(LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00));
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.findCitaByFechaAndHora(cita.getFecha().toLocalDate(), cita.getHora())).thenReturn(true);
        Mockito.when(daoCita.findCitasByFechaAndUsuario(cita.getFecha().toLocalDate(), 1L)).thenReturn(false);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionFechaOcupada.class, "A ESTA HORA EL DOCTOR TIENE OTRA CITA");
    }

    @Test
    public void validarErrorPorHoraCitaFinDeSemanaTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();
        cita.setHora(1900);
        cita.setFecha(LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00));
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.findCitaByFechaAndHora(cita.getFecha().toLocalDate(), cita.getHora())).thenReturn(false);
        Mockito.when(daoCita.findCitasByFechaAndUsuario(cita.getFecha().toLocalDate(), 1L)).thenReturn(false);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionFechaNoValida.class, "EL HORARIO LOS FINES DE SEMANA ES 8 AM - 6 PM");
    }

    @Test
    public void validarErrorPorHoraCitaEntreSemanaTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();
        cita.setHora(1300);
        cita.setFecha(LocalDateTime.of(2021, Month.SEPTEMBER, 23, 00, 00, 00));
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.findCitaByFechaAndHora(cita.getFecha().toLocalDate(), cita.getHora())).thenReturn(false);
        Mockito.when(daoCita.findCitasByFechaAndUsuario(cita.getFecha().toLocalDate(), 1L)).thenReturn(false);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionFechaNoValida.class, "EL HORARIO ENTRE SEMANA ES 6 PM - 10 PM");
    }


}
