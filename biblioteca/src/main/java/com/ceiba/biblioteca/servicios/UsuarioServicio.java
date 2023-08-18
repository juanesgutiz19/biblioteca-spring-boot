package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.modelos.entidades.TipoUsuario;
import com.ceiba.biblioteca.modelos.entidades.Usuario;

import java.util.Optional;

public interface UsuarioServicio {

    TipoUsuario obtenerTipoUsuarioPorId(Integer id);

    Usuario buscarPorIdentificacion(String identificacionUsuario);
}
