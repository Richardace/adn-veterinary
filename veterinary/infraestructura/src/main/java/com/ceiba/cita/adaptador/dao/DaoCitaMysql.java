package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DaoCitaMysql implements DaoCita {

    private final static String CONSULTA_FALLIDA = "No se encontró usuario con las credenciales proporcionadas";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="cita", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="cita", value="listarById")
    private static String sqlListarById;

    @SqlStatement(namespace="cita", value="existePorFechaCita")
    private static String sqlFechaCita;

    @SqlStatement(namespace="cita", value="existePorFechaCitaDiferente")
    private static String sqlFechaCitaUpdate;

    @SqlStatement(namespace="cita", value="cantidadCitasUsuario")
    private static String sqlCitasUsuario;

    @SqlStatement(namespace="cita", value="cantidadCitasUsuarioUpdate")
    private static String sqlCitasUsuarioUpdate;

    public DaoCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoCita> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoCita());
    }

    @Override
    public List<DtoCita> listarById(long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarById, paramSource ,new MapeoCita());
    }

    @Override
    public Boolean findCitaByFechaAndHora(LocalDate fecha, int hora) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha", fecha);
        paramSource.addValue("hora", hora);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFechaCita,paramSource, Boolean.class);
    }

    @Override
    public boolean findCitaByFechaAndHoraUpdate(LocalDate fecha, int hora, long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha", fecha);
        paramSource.addValue("hora", hora);
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFechaCitaUpdate,paramSource, Boolean.class);
    }

    @Override
    public boolean findCitasByFechaAndUsuario(LocalDate fecha, Long idUsuario) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha", fecha);
        paramSource.addValue("idUsuario", idUsuario);
        try{
            Integer cantidadCitas = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlCitasUsuario,paramSource, Integer.class);
            return cantidadCitas > 2;
        }catch (EmptyResultDataAccessException e){
            throw new ExcepcionTecnica(CONSULTA_FALLIDA);
        }
    }

    @Override
    public Boolean findCitasByFechaAndUsuarioUpdate(LocalDate fecha, Long idUsuario, Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha", fecha);
        paramSource.addValue("idUsuario", idUsuario);
        paramSource.addValue("id", id);
        try{
            Integer cantidadCitas = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlCitasUsuarioUpdate,paramSource, Integer.class);
            return cantidadCitas > 2;
        }catch (EmptyResultDataAccessException e){
            throw new ExcepcionTecnica(CONSULTA_FALLIDA);
        }
    }

}
