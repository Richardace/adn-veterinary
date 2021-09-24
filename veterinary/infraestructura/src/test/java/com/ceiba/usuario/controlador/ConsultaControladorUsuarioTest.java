package com.ceiba.usuario.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ceiba.ApplicationMock;
import com.ceiba.usuario.comando.ComandoUsuario;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorUsuario.class)
public class ConsultaControladorUsuarioTest {

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void listarTest() throws Exception {

        mocMvc.perform(get("/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].correo", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].clave", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].rol", IsNull.notNullValue()));

    }

    @Test
    public void autenticacionExitosaTest() throws Exception {

        ComandoUsuario usuario = new ComandoUsuario();
        usuario.setCorreo("richard@gmail.com");
        usuario.setClave("1234");

        mocMvc.perform(get("/usuarios/autenticar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                        .andExpect(status().isOk())
                        .andExpect(content().json("{'correo': 'richard@gmail.com'}"))
                        .andExpect(content().json("{'clave': '1234'}"));
    }

    @Test
    public void autenticacionFallidaTest() throws Exception {

        ComandoUsuario usuario = new ComandoUsuario();
        usuario.setCorreo("richard@gmail.com");
        usuario.setClave("1235");

        mocMvc.perform(get("/usuarios/autenticar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{'nombreExcepcion': 'ExcepcionTecnica'}"))
                .andExpect(content().json("{'mensaje': 'No se encontró usuario con las credenciales proporcionadas'}"));
    }


}
