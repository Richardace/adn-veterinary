package com.ceiba.usuario.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarLongitud;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Usuario {

    private static final String SE_DEBE_INGRESAR_ROL = "Se debe ingresar rol para el usuario";
    private static final String LA_CLAVE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "La clave debe tener una longitud mayor o igual a %s";
    private static final String SE_DEBE_INGRESAR_LA_CLAVE = "Se debe ingresar la clave";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";
    private static final String SE_DEBE_INGRESAR_EL_CORREO_DE_USUARIO = "Se debe ingresar el correo de usuario";

    private static final int LONGITUD_MINIMA_CLAVE = 4;

    private Long id;
    private String nombre;
    private String correo;
    private String clave;
    private String rol;

    public Usuario(Long id,String nombre, String correo, String clave,String rol) {
        validarObligatorio(clave, SE_DEBE_INGRESAR_LA_CLAVE);
        validarObligatorio(correo, SE_DEBE_INGRESAR_EL_CORREO_DE_USUARIO);
        validarLongitud(clave, LONGITUD_MINIMA_CLAVE, String.format(LA_CLAVE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A,LONGITUD_MINIMA_CLAVE));
        validarObligatorio(rol, SE_DEBE_INGRESAR_ROL);

        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.rol = rol;
    }
    public Usuario(String correo, String clave) {
        validarObligatorio(correo, SE_DEBE_INGRESAR_EL_CORREO_DE_USUARIO);
        validarObligatorio(clave, SE_DEBE_INGRESAR_LA_CLAVE);

        this.correo = correo;
        this.clave = clave;
    }


}
