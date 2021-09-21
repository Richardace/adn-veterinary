package com.ceiba.cita.controlador;

import com.ceiba.cita.consulta.ManejadorListarCita;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/citas")
@Api(tags={"Controlador consulta cita"})
public class ConsultaControladorCita {

    private final ManejadorListarCita manejadorListarCitas;

    public ConsultaControladorCita(ManejadorListarCita manejadorListarCitas) {
        this.manejadorListarCitas = manejadorListarCitas;
    }

    @GetMapping
    @ApiOperation("Listar Citas")
    public List<DtoCita> listar() {
        return this.manejadorListarCitas.ejecutar();
    }

}
