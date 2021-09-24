package com.ceiba.cita.comando;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCita{

    private Long id;
    private Long idUsuario;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime fecha;
    private Integer hora;
    private String notas;

    public ComandoCita(Long idUsuario, LocalDateTime fecha, Integer hora, String notas) {
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.notas = notas;
    }
}
