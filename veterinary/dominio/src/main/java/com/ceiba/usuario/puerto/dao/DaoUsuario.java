package com.ceiba.usuario.puerto.dao;

import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Usuario;

import java.util.List;

public interface DaoUsuario {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoUsuario> listar();

    /**
     * Permite buscar el usuario que intenta loguearse en el sisetma
     * @return las rol del usuario
     */
    DtoUsuario autenticarUsuario(Usuario usuario);
}
