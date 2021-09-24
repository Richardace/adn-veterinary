package com.ceiba.cita.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.testdatabuilder.ComandoCitaTestDataBuilder;
import com.ceiba.usuario.controlador.ComandoControladorUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorUsuario.class)
public class ComandoControladorCitaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crearCitaExitosoTest() throws Exception{
        // arrange
        ComandoCita cita = new ComandoCitaTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/citas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cita)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    public void crearCitaFallidoTest() throws Exception{
        // arrange
        ComandoCita cita = new ComandoCitaTestDataBuilder().build();
        cita.setHora(0500);

        // act - assert
        mocMvc.perform(post("/citas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cita)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{'nombreExcepcion': 'ExcepcionFechaNoValida'}"))
                .andExpect(content().json("{'mensaje': 'EL HORARIO LOS FINES DE SEMANA ES 8 AM - 6 PM'}"));

    }

    @Test
    public void actualizarCitaExitosoTest() throws Exception{
        // arrange
        Long id = 2L;
        ComandoCita cita = new ComandoCitaTestDataBuilder().build();

        // act - assert
        mocMvc.perform(put("/citas/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cita)))
                .andExpect(status().isOk());
    }

    @Test
    public void actualizarCitaFallidoTest() throws Exception{
        // arrange
        Long id = 2L;
        ComandoCita cita = new ComandoCitaTestDataBuilder().build();
        cita.setHora(0500);
        // act - assert
        mocMvc.perform(put("/citas/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cita)))
                        .andExpect(status().isInternalServerError())
                        .andExpect(content().json("{'nombreExcepcion': 'ExcepcionFechaNoValida'}"))
                        .andExpect(content().json("{'mensaje': 'EL HORARIO LOS FINES DE SEMANA ES 8 AM - 6 PM'}"));
    }

}
