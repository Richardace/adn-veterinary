package com.ceiba.cita.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCita {
    private Long id;
    private Long idUsuario;
    private String fecha;
    private Integer hora;
    private Double precio;
    private String notas;

}
