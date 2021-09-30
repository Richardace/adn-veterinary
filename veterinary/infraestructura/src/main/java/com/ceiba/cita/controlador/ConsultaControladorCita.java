package com.ceiba.cita.controlador;

import com.ceiba.cita.consulta.ManejadorListarCita;
import com.ceiba.cita.consulta.ManejadorListarCitaPorId;
import com.ceiba.cita.modelo.dto.DtoCita;
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
    private final ManejadorListarCitaPorId manejadorListarCitaPorId;

    public ConsultaControladorCita(ManejadorListarCita manejadorListarCitas,
                                   ManejadorListarCitaPorId manejadorListarCitaPorId) {
        this.manejadorListarCitas = manejadorListarCitas;
        this.manejadorListarCitaPorId = manejadorListarCitaPorId;
    }

    @GetMapping
    @ApiOperation("Listar Citas")
    public List<DtoCita> listar() {
        return this.manejadorListarCitas.ejecutar();
    }

    @GetMapping(value="/{id}")
    @ApiOperation("Listar Citas by Id")
    public List<DtoCita> listarById(@PathVariable long id) {
        return this.manejadorListarCitaPorId.ejecutar(id);
    }

}
