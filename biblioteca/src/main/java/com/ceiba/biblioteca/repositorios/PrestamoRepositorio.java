package com.ceiba.biblioteca.repositorios;

import com.ceiba.biblioteca.modelos.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {
}
