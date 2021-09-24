package com.ceiba.cita.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.usuario.comando.ComandoUsuario;
import com.ceiba.usuario.controlador.ConsultaControladorUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorUsuario.class)
public class ConsultaControladorCitaTest {

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void listarCitasTest() throws Exception {

        mocMvc.perform(get("/citas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idUsuario", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].fecha", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].hora", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].precio", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].notas", IsNull.notNullValue()));
    }

}
