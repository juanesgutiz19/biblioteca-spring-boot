package com.ceiba.biblioteca.controladores;

import com.ceiba.biblioteca.dto.PrestamoPeticionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaCreacionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaDetalleDto;
import com.ceiba.biblioteca.servicios.PrestamoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "prestamo", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrestamoControlador {

    private final PrestamoServicio prestamoServicio;

    @PostMapping
    public ResponseEntity<PrestamoRespuestaCreacionDto> crearPrestamo(@RequestBody PrestamoPeticionDto prestamoPeticionDto) {
        PrestamoRespuestaCreacionDto prestamoRespuestaCreacionDto = prestamoServicio.crearPrestamo(prestamoPeticionDto);
        return ResponseEntity.status(HttpStatus.OK).body(prestamoRespuestaCreacionDto);
    }

    @GetMapping("/{id-prestamo}")
    public ResponseEntity<PrestamoRespuestaDetalleDto> obtenerPrestamoPorId(@PathVariable("id-prestamo") Integer prestamoId) {
        return ResponseEntity.ok(prestamoServicio.obtenerPrestamoPorId(prestamoId));
    }

}

