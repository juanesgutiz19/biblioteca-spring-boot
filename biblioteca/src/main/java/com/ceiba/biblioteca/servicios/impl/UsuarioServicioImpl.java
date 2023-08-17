package com.ceiba.biblioteca.servicios.impl;

import com.ceiba.biblioteca.excepciones.BadRequestException;
import com.ceiba.biblioteca.modelos.entidades.TipoUsuario;
import com.ceiba.biblioteca.repositorios.TipoUsuarioRepositorio;
import com.ceiba.biblioteca.repositorios.UsuarioRepositorio;
import com.ceiba.biblioteca.servicios.UsuarioServicio;
import com.ceiba.biblioteca.utilidades.MensajesConstantes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final TipoUsuarioRepositorio tipoUsuarioRepositorio;

    @Override
    public Optional<TipoUsuario> obtenerTipoUsuarioPorId(Integer id) {
        return Optional.ofNullable(tipoUsuarioRepositorio.findById(id).orElseThrow(() -> new BadRequestException(
                MensajesConstantes.TIPO_USUARIO_NO_PERMITIDO_MENSAJE
        )));
    }
}
