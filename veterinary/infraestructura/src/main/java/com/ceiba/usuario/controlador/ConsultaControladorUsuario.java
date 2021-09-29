package com.ceiba.usuario.controlador;

import java.util.List;

import com.ceiba.usuario.comando.ComandoUsuario;
import com.ceiba.usuario.consulta.ManejadorAutenticarUsuario;
import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import org.springframework.web.bind.annotation.*;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/usuarios")
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorUsuario {

    private final ManejadorListarUsuarios manejadorListarUsuarios;
    private final ManejadorAutenticarUsuario manejadorAutenticarUsuario;

    public ConsultaControladorUsuario(ManejadorListarUsuarios manejadorListarUsuarios,
                                      ManejadorAutenticarUsuario manejadorAutenticarUsuario) {
        this.manejadorListarUsuarios = manejadorListarUsuarios;
        this.manejadorAutenticarUsuario = manejadorAutenticarUsuario;
    }

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<DtoUsuario> listar() {
        return this.manejadorListarUsuarios.ejecutar();
    }

    @PostMapping(value="autenticar")
    @ApiOperation("Autenticar Usuario")
    public DtoUsuario autenticarUsuario(@RequestBody ComandoUsuario comandoUsuario) {
        return this.manejadorAutenticarUsuario.ejecutar(comandoUsuario);
    }

}
