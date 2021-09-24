package com.ceiba.usuario.puerto.repositorio;

import com.ceiba.usuario.modelo.entidad.Usuario;

public interface RepositorioUsuario {
    /**
     * Permite crear un usuario
     * @param usuario
     * @return el id generado
     */
    Long crear(Usuario usuario);

    /**
     * Permite actualizar un usuario
     * @param usuario
     */
    void actualizar(Usuario usuario);

    /**
     * Permite validar si existe un usuario con un nombre
     * @param correo
     * @return si existe o no
     */
    boolean existe(String correo);

    /**
     * Permite validar si existe un usuario con un nombre excluyendo un id
     * @param correo
     * @return si existe o no
     */
    boolean existeExcluyendoId(Long id,String correo);

}
