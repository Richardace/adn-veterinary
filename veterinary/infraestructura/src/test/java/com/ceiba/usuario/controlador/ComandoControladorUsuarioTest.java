package com.ceiba.usuario.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ceiba.ApplicationMock;
import com.ceiba.usuario.comando.ComandoUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.ComandoUsuarioTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorUsuario.class)
public class ComandoControladorUsuarioTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crearUsuarioExitosoTest() throws Exception{
        // arrange
        ComandoUsuario usuario = new ComandoUsuarioTestDataBuilder().build();
        usuario.setCorreo("prueba@gmail.com");

        // act - assert
        mocMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    public void crearUsuarioFallidoTest() throws Exception{
        // arrange
        ComandoUsuario usuario = new ComandoUsuarioTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion': 'ExcepcionDuplicidad'}"));
    }

    @Test
    public void actualizarUsuarioExitosoTest() throws Exception{
        // arrange
        Long id = 2L;
        ComandoUsuario usuario = new ComandoUsuarioTestDataBuilder().build();
        usuario.setCorreo("ceiba@gmail.com");

        // act - assert
        mocMvc.perform(put("/usuarios/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

    @Test
    public void actualizarUsuarioFallidoTest() throws Exception{
        // arrange
        Long id = 2L;
        ComandoUsuario usuario = new ComandoUsuarioTestDataBuilder().build();

        // act - assert
        mocMvc.perform(put("/usuarios/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest());
    }

}
