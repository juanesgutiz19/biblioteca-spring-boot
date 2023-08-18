package com.ceiba.biblioteca.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder(toBuilder = true)
public class PrestamoPeticionDto {

    @NotNull
    @Size(min = 5, max = 10)
    private String isbn;

    @NotNull
    @Size(min = 5, max = 10)
    private String identificacionUsuario;

    @NotNull
    private Integer tipoUsuario;
}
