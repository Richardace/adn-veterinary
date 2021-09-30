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

        // act - assert
        Double precioCita = CitaUtil.calcularCostoCita(fechaCitaPrueba.toLocalDate());

        Assert.assertEquals(VALOR_CITA_FIN_DE_SEMANA, precioCita);
    }

    @Test
    public void validarFechaFinDeSemanaSabadoTest() {
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
    public void validarFechaFinDeSemanaDomingoTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 26, 00, 00, 00);
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
    public void validarHoraCitaValidaFinSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        Integer hora = 1200;
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        CitaUtil.validarHoraCitaValida(fechaCitaPrueba.toLocalDate(), hora);
    }

    @Test
    public void validarHoraCitaNoValidaFinSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        Integer hora = 2000;
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> CitaUtil.validarHoraCitaValida(fechaCitaPrueba.toLocalDate(), hora), ExcepcionFechaNoValida.class, "EL HORARIO LOS FINES DE SEMANA ES 8 AM - 6 PM");
    }

    @Test
    public void validarHoraCitaNoValidaFinSemana2Test() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        Integer hora = 0500;
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> CitaUtil.validarHoraCitaValida(fechaCitaPrueba.toLocalDate(), hora), ExcepcionFechaNoValida.class, "EL HORARIO LOS FINES DE SEMANA ES 8 AM - 6 PM");
    }

    @Test
    public void validarHoraCitaValidaEntreSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 24, 00, 00, 00);
        Integer hora = 2000;
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);

        // act - assert
        CitaUtil.validarHoraCitaValida(fechaCitaPrueba.toLocalDate(), hora);
    }

    @Test
    public void validarCitaUtil() {
        // arrange
        CitaUtil absCls = Mockito.mock(
                CitaUtil.class,
                Mockito.CALLS_REAL_METHODS);
    }

    @Test
    public void validarHoraCitaNoValidaEntreSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 24, 00, 00, 00);
        Integer hora = 1200;
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> CitaUtil.validarHoraCitaValida(fechaCitaPrueba.toLocalDate(), hora), ExcepcionFechaNoValida.class, "EL HORARIO ENTRE SEMANA ES 6 PM - 10 PM");
    }

    @Test
    public void validarHoraCitaNoValidaEntreSemana2Test() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 24, 00, 00, 00);
        Integer hora = 2300;
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> CitaUtil.validarHoraCitaValida(fechaCitaPrueba.toLocalDate(), hora), ExcepcionFechaNoValida.class, "EL HORARIO ENTRE SEMANA ES 6 PM - 10 PM");
    }

    @Test
    public void validarErrorPorLimiteDeCitasUsuarioTest() {
        // arrange
        Integer hora = 1300;
        LocalDateTime fecha = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        Cita cita = new CitaTestDataBuilder().conFecha(fecha).conHora(hora).build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.buscarCitaPorFechaYHora(cita)).thenReturn(false);
        Mockito.when(daoCita.buscarCitaPorFechaYUsuario(cita)).thenReturn(true);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionLimiteCitasDia.class, "SE EXCEDIO EL LIMITE DE CITAS AL DIA");
    }

    @Test
    public void validarErrorPorDisponibilidadCitasTest() {
        // arrange
        Integer hora = 1300;
        LocalDateTime fecha = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        Cita cita = new CitaTestDataBuilder().conFecha(fecha).conHora(hora).build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.buscarCitaPorFechaYHora(cita)).thenReturn(true);
        Mockito.when(daoCita.buscarCitaPorFechaYUsuario(cita)).thenReturn(false);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionFechaOcupada.class, "A ESTA HORA EL DOCTOR TIENE OTRA CITA");
    }

    @Test
    public void validarErrorPorHoraCitaFinDeSemanaTest() {
        // arrange
        Integer hora = 1900;
        LocalDateTime fecha = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        Cita cita = new CitaTestDataBuilder().conFecha(fecha).conHora(hora).build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.buscarCitaPorFechaYHora(cita)).thenReturn(false);
        Mockito.when(daoCita.buscarCitaPorFechaYUsuario(cita)).thenReturn(false);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionFechaNoValida.class, "EL HORARIO LOS FINES DE SEMANA ES 8 AM - 6 PM");
    }

    @Test
    public void validarErrorPorHoraCitaEntreSemanaTest() {
        // arrange
        Integer hora = 1300;
        LocalDateTime fecha = LocalDateTime.of(2021, Month.SEPTEMBER, 23, 00, 00, 00);
        Cita cita = new CitaTestDataBuilder().conFecha(fecha).conHora(hora).build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.buscarCitaPorFechaYHora(cita)).thenReturn(false);
        Mockito.when(daoCita.buscarCitaPorFechaYUsuario(cita)).thenReturn(false);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionFechaNoValida.class, "EL HORARIO ENTRE SEMANA ES 6 PM - 10 PM");
    }


}
