package com.ceiba.biblioteca.repositorios;

import com.ceiba.biblioteca.modelos.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libro, Integer> {
    Optional<Libro> findByIsbn(String isbn);
}
