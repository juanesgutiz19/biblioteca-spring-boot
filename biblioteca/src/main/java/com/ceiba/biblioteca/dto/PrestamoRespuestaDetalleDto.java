package com.ceiba.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoRespuestaDetalleDto {

    private Integer id;

    private String isbn;

    private String identificacionUsuario;

    private Integer tipoUsuario;

    private String fechaMaximaDevolucion;

}
