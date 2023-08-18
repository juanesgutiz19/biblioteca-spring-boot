package com.ceiba.biblioteca.servicios.impl;

import com.ceiba.biblioteca.excepciones.BadRequestException;
import com.ceiba.biblioteca.excepciones.ResourceNotFoundException;
import com.ceiba.biblioteca.modelos.entidades.Libro;
import com.ceiba.biblioteca.repositorios.LibroRepositorio;
import com.ceiba.biblioteca.servicios.LibroServicio;
import com.ceiba.biblioteca.utilidades.MensajesConstantes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LibroServicioImpl implements LibroServicio {

    private final LibroRepositorio libroRepositorio;

    @Override
    public Libro buscarPorIsbn(String isbn) {
        return libroRepositorio.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException(
                MensajesConstantes.LIBRO_NO_ENCONTRADO_ISBN_MENSAJE.concat(" " + isbn)
        ));
    }

}
