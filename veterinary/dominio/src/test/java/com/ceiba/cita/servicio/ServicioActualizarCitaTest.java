package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.util.CitaUtil;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionFechaNoValida;
import com.ceiba.dominio.excepcion.ExcepcionFechaOcupada;
import com.ceiba.dominio.excepcion.ExcepcionLimiteCitasDia;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

import java.time.LocalDateTime;
import java.time.Month;

public class ServicioActualizarCitaTest {

    private static final Double VALOR_CITA_ENTRE_SEMANA = 150000d;
    private static final Double VALOR_CITA_FIN_DE_SEMANA = 230000d;

    @Test
    public void validarPrecioCitaEntreSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 24, 00, 00, 00);
        // act - assert
        Double precioCita = CitaUtil.calcularCostoCita(fechaCitaPrueba.toLocalDate());
        Assert.assertEquals(VALOR_CITA_ENTRE_SEMANA, precioCita);
    }

    @Test
    public void validarPrecioCitaFinDeSemanaTest() {

        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);

        Double precioCita = CitaUtil.calcularCostoCita(fechaCitaPrueba.toLocalDate());

        Assert.assertEquals(VALOR_CITA_FIN_DE_SEMANA, precioCita);
    }

    @Test
    public void validarFechaFinDeSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        // act - assert
        boolean esFinDeSemana = CitaUtil.esFinDeSemana(fechaCitaPrueba.toLocalDate());
        Assert.assertEquals(true, esFinDeSemana);
    }

    @Test
    public void validarFechaEntreSemanaTest() {
        // arrange
        LocalDateTime fechaCitaPrueba = LocalDateTime.of(2021, Month.SEPTEMBER, 24, 00, 00, 00);
        // act - assert
        boolean esFinDeSemana = CitaUtil.esFinDeSemana(fechaCitaPrueba.toLocalDate());
        Assert.assertEquals(false, esFinDeSemana);
    }

    @Test
    public void validarErrorPorLimiteDeCitasUsuarioTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.buscarCitaPorFechaYHoraDescartandoId(cita)).thenReturn(false);
        Mockito.when(daoCita.buscarCitaPorFechaYUsuarioDescartandoId(cita)).thenReturn(true);
        ServicioActualizarCita ServicioActualizarCita = new ServicioActualizarCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> ServicioActualizarCita.ejecutar(cita), ExcepcionLimiteCitasDia.class, "SE EXCEDIO EL LIMITE DE CITAS AL DIA");
    }

    @Test
    public void validarErrorPorDisponibilidadCitasTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.buscarCitaPorFechaYHoraDescartandoId(cita)).thenReturn(true);
        Mockito.when(daoCita.buscarCitaPorFechaYUsuarioDescartandoId(cita)).thenReturn(false);
        ServicioActualizarCita ServicioActualizarCita = new ServicioActualizarCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> ServicioActualizarCita.ejecutar(cita), ExcepcionFechaOcupada.class, "A ESTA HORA EL DOCTOR TIENE OTRA CITA");
    }

    @Test
    public void validarErrorPorHoraCitaFinDeSemanaTest() {
        // arrange
        Integer hora = 1900;
        LocalDateTime fecha = LocalDateTime.of(2021, Month.SEPTEMBER, 25, 00, 00, 00);
        Cita cita = new CitaTestDataBuilder().conHora(hora).conFecha(fecha).build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.buscarCitaPorFechaYHoraDescartandoId(cita)).thenReturn(false);
        Mockito.when(daoCita.buscarCitaPorFechaYUsuarioDescartandoId(cita)).thenReturn(false);
        ServicioActualizarCita ServicioActualizarCita = new ServicioActualizarCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> ServicioActualizarCita.ejecutar(cita), ExcepcionFechaNoValida.class, "EL HORARIO LOS FINES DE SEMANA ES 8 AM - 6 PM");
    }

    @Test
    public void validarErrorPorHoraCitaEntreSemanaTest() {
        // arrange
        Integer hora = 1300;
        LocalDateTime fecha = LocalDateTime.of(2021, Month.SEPTEMBER, 23, 00, 00, 00);
        Cita cita = new CitaTestDataBuilder().conHora(hora).conFecha(fecha).build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);

        Mockito.when(daoCita.buscarCitaPorFechaYHoraDescartandoId(cita)).thenReturn(false);
        Mockito.when(daoCita.buscarCitaPorFechaYUsuarioDescartandoId(cita)).thenReturn(false);
        ServicioActualizarCita ServicioActualizarCita = new ServicioActualizarCita(repositorioCita, daoCita);
        // act - assert
        BasePrueba.assertThrows(() -> ServicioActualizarCita.ejecutar(cita), ExcepcionFechaNoValida.class, "EL HORARIO ENTRE SEMANA ES 6 PM - 10 PM");
    }
}
