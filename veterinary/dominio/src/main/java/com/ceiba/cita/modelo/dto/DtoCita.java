package com.ceiba.cita.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoCita {
    private Long id;
    private Long idUsuario;

    private LocalDateTime fecha;
    private Integer hora;
    private Double precio;
    private String notas;

}
