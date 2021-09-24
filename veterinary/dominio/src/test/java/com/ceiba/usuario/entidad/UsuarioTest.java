package com.ceiba.usuario.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.Test;

public class UsuarioTest {

    @Test
    public void validarClaveLongitudMenor4Test() {
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conClave("124");
        BasePrueba.assertThrows(usuarioTestDataBuilder::build, ExcepcionLongitudValor.class, "La clave debe tener una longitud mayor o igual a 4");
    }

    @Test
    public void validarSinNombreObligatorioTest() {
        UsuarioTestDataBuilder UsuarioTestDataBuilder = new UsuarioTestDataBuilder().sinNombre();
        BasePrueba.assertThrows(UsuarioTestDataBuilder::build, ExcepcionValorObligatorio.class, "Se debe ingresar el nombre de usuario");
    }

    @Test
    public void validarSinCorreoObligatorioTest() {
        UsuarioTestDataBuilder UsuarioTestDataBuilder = new UsuarioTestDataBuilder().sinCorreo();
        BasePrueba.assertThrows(UsuarioTestDataBuilder::build, ExcepcionValorObligatorio.class, "Se debe ingresar el correo de usuario");
    }
    @Test
    public void validarSinClaveObligatorioTest() {
        UsuarioTestDataBuilder UsuarioTestDataBuilder = new UsuarioTestDataBuilder().sinClave();
        BasePrueba.assertThrows(UsuarioTestDataBuilder::build, ExcepcionValorObligatorio.class, "Se debe ingresar la clave");
    }

    @Test
    public void validarSinRolObligatorioTest() {
        UsuarioTestDataBuilder UsuarioTestDataBuilder = new UsuarioTestDataBuilder().sinRol();
        BasePrueba.assertThrows(UsuarioTestDataBuilder::build, ExcepcionValorObligatorio.class, "Se debe ingresar rol para el usuario");
    }

}
