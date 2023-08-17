package com.ceiba.biblioteca.controladores;

import com.ceiba.biblioteca.dto.PrestamoPeticionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaCreacionDto;
import com.ceiba.biblioteca.servicios.PrestamoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

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
}

