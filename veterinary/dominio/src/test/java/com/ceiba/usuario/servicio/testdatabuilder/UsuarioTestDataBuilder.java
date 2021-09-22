package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.modelo.entidad.Usuario;

import java.time.LocalDateTime;

public class UsuarioTestDataBuilder {

    private Long id;
    private String nombre;
    private String correo;
    private String clave;
    private String rol;

    public UsuarioTestDataBuilder() {
        nombre = "1234";
        correo = "richardacevedo98@gmail.com";
        clave = "1234";
        rol = "admin";
    }

    public UsuarioTestDataBuilder conClave(String clave) {
        this.clave = clave;
        return this;
    }

    public UsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public Usuario build() {
        return new Usuario(id,nombre, correo, clave,rol);
    }
}
