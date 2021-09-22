package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCita implements RowMapper<DtoCita>, MapperResult {

    @Override
    public DtoCita mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        Long idUsuario = resultSet.getLong("idUsuario");
        String fecha = resultSet.getString("fecha");
        Integer hora = resultSet.getInt("hora");
        Double precio = resultSet.getDouble("precio");
        String notas = resultSet.getString("notas");

        return new DtoCita(id,idUsuario,fecha, hora, precio, notas);
    }

}
