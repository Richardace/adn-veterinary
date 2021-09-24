package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.comando.ComandoUsuario;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoUsuarioTestDataBuilder {

    private Long id;
    private String nombre;
    private String correo;
    private String clave;
    private String rol;

    public ComandoUsuarioTestDataBuilder() {
        nombre = UUID.randomUUID().toString();
        correo = "richard@gmail.com";
        clave = "1234";
        rol = "admin";
    }

    public ComandoUsuarioTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoUsuario build() {
        return new ComandoUsuario(id,nombre, correo, clave, rol);
    }
}
