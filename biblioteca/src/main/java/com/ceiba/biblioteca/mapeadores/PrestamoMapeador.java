package com.ceiba.biblioteca.mapeadores;

import com.ceiba.biblioteca.dto.PrestamoPeticionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaCreacionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaDetalleDto;
import com.ceiba.biblioteca.modelos.entidades.Libro;
import com.ceiba.biblioteca.modelos.entidades.Prestamo;
import com.ceiba.biblioteca.modelos.entidades.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
@Component
public interface PrestamoMapeador {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "libro", source = "isbn")
    @Mapping(target = "usuario", source = "identificacionUsuario")
    @Mapping(target = "fechaMaximaDevolucion", ignore = true)
    @Mapping(target = "creadoEn", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "actualizadoEn", expression = "java(java.time.LocalDateTime.now())")
    Prestamo prestamoPeticionDtoToPrestamo(PrestamoPeticionDto prestamoPeticionDto);

    @Mapping(target = "fechaMaximaDevolucion", source = "fechaMaximaDevolucion")
    PrestamoRespuestaCreacionDto prestamoToPrestamoRespuestaCreacionDto(Prestamo prestamo);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "isbn", source = "libro.isbn")
    @Mapping(target = "identificacionUsuario", source = "usuario.identificacionUsuario")
    @Mapping(target = "tipoUsuario", source = "usuario.tipoUsuario.id")
    @Mapping(target = "fechaMaximaDevolucion", source = "fechaMaximaDevolucion")
    PrestamoRespuestaDetalleDto prestamoToPrestamoRespuestaDetalleDto(Prestamo prestamo);

    default String mapFechaMaximaDevolucionToFechaMaximaDevolucion(LocalDate fechaMaximaDevolucion){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaMaximaDevolucion.format(formato);
    }

    default Libro mapIsbnToLibro(String isbn) {
        if (isbn == null) {
            return null;
        }
        return Libro.builder().isbn(isbn).build();
    }

    default Usuario mapIdentificacionUsuarioToUsuario(String identificacionUsuario) {
        if (identificacionUsuario == null) {
            return null;
        }
        return Usuario.builder().identificacionUsuario(identificacionUsuario).build();
    }

}
