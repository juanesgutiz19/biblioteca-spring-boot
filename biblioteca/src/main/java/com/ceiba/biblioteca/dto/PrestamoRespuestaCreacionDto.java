package com.ceiba.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class PrestamoRespuestaCreacionDto {

    private Integer id;

    private String fechaMaximaDevolucion;

}
