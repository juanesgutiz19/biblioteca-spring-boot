package com.ceiba.biblioteca.controladores;

import com.ceiba.biblioteca.dto.PrestamoPeticionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaCreacionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaDetalleDto;
import com.ceiba.biblioteca.excepciones.MensajeError;
import com.ceiba.biblioteca.servicios.PrestamoServicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "prestamo", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Préstamos", description = "Operaciones asociadas a los préstamos de libros en una biblioteca.")
public class PrestamoControlador {

    private final PrestamoServicio prestamoServicio;

    @PostMapping
    @ApiOperation(value = "Crear préstamo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Préstamo creado exitosamente", response = PrestamoRespuestaCreacionDto.class),
            @ApiResponse(code = 400, message = "Errores por incumplimiento de reglas de negocio", response = MensajeError.class),
            @ApiResponse(code = 404, message = "Libro o usuario no encontrados", response = MensajeError.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = MensajeError.class),
            @ApiResponse(code = 201, message = "Este código de estado no se utiliza actualmente"),
            @ApiResponse(code = 401, message = "Este código de estado no se utiliza actualmente"),
            @ApiResponse(code = 403, message = "Este código de estado no se utiliza actualmente")
    })
    public ResponseEntity<PrestamoRespuestaCreacionDto> crearPrestamo(@RequestBody PrestamoPeticionDto prestamoPeticionDto) {
        PrestamoRespuestaCreacionDto prestamoRespuestaCreacionDto = prestamoServicio.crearPrestamo(prestamoPeticionDto);
        return ResponseEntity.status(HttpStatus.OK).body(prestamoRespuestaCreacionDto);
    }

    @GetMapping("/{id-prestamo}")
    @ApiOperation(value = "Obtener un préstamo por su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Préstamo obtenido exitosamente", response = PrestamoRespuestaDetalleDto.class),
            @ApiResponse(code = 404, message = "Préstamo no encontrado por ID", response = MensajeError.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = MensajeError.class),
            @ApiResponse(code = 201, message = "Este código de estado no se utiliza actualmente"),
            @ApiResponse(code = 400, message = "Este código de estado no se utiliza actualmente"),
            @ApiResponse(code = 401, message = "Este código de estado no se utiliza actualmente"),
            @ApiResponse(code = 403, message = "Este código de estado no se utiliza actualmente")
    })
    public ResponseEntity<PrestamoRespuestaDetalleDto> obtenerPrestamoPorId(@PathVariable("id-prestamo") Integer prestamoId) {
        return ResponseEntity.ok(prestamoServicio.obtenerPrestamoPorId(prestamoId));
    }

}

