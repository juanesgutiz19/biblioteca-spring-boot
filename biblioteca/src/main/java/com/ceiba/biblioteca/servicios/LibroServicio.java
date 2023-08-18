package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.modelos.entidades.Libro;

public interface LibroServicio {

    Libro buscarPorIsbn(String isbn);

}
