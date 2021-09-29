package com.ceiba.cita.controlador;

import com.ceiba.cita.consulta.ManejadorListarCita;
import com.ceiba.cita.consulta.ManejadorListarCitaById;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.usuario.comando.ComandoUsuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/citas")
@Api(tags={"Controlador consulta cita"})
public class ConsultaControladorCita {

    private final ManejadorListarCita manejadorListarCitas;
    private final ManejadorListarCitaById manejadorListarCitaById;

    public ConsultaControladorCita(ManejadorListarCita manejadorListarCitas,
                                   ManejadorListarCitaById manejadorListarCitaById) {
        this.manejadorListarCitas = manejadorListarCitas;
        this.manejadorListarCitaById = manejadorListarCitaById;
    }

    @GetMapping
    @ApiOperation("Listar Citas")
    public List<DtoCita> listar() {
        return this.manejadorListarCitas.ejecutar();
    }

    @GetMapping(value="/{id}")
    @ApiOperation("Listar Citas by Id")
    public List<DtoCita> listarById(@PathVariable long id) {
        return this.manejadorListarCitaById.ejecutar(id);
    }

}
