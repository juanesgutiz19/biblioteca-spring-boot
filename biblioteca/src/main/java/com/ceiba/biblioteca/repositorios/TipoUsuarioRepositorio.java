package com.ceiba.biblioteca.repositorios;

import com.ceiba.biblioteca.modelos.entidades.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepositorio extends JpaRepository<TipoUsuario, Integer> {
}
