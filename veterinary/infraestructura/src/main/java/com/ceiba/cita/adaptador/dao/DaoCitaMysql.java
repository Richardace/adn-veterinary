package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoCitaMysql implements DaoCita {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="cita", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="cita", value="existePorFechaCita")
    private static String sqlFechaCita;

    @SqlStatement(namespace="cita", value="cantidadCitasUsuario")
    private static String sqlCitasUsuario;

    public DaoCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoCita> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoCita());
    }

    @Override
    public Boolean findCitaByFechaAndHora(String fecha, int hora) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha", fecha);
        paramSource.addValue("hora", hora);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFechaCita,paramSource, Boolean.class);
    }

    @Override
    public boolean findCitasByFechaAndUsuario(String fecha, Long idUsuario) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha", fecha);
        paramSource.addValue("idUsuario", idUsuario);
        try{
            Integer cantidadCitas = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlCitasUsuario,paramSource, Integer.class);
            return cantidadCitas > 2 ? true : false;
        }catch (NullPointerException e){
            throw new ExcepcionTecnica("ALGO FALLO");
        }


    }

}
