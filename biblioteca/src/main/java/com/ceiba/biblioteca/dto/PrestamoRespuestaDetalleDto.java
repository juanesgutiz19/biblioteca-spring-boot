package com.ceiba.biblioteca.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class PrestamoRespuestaDetalleDto {

    private Integer id;

    private String isbn;

    private String identificacionUsuario;

    private Integer tipoUsuario;

    private LocalDate fechaMaximaDevolucion;
}
