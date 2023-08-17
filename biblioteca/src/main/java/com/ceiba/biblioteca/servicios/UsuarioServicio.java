package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.modelos.entidades.TipoUsuario;

import java.util.Optional;

public interface UsuarioServicio {

    Optional<TipoUsuario> obtenerTipoUsuarioPorId(Integer id);
}
