package com.ceiba.biblioteca.servicios.impl;

import com.ceiba.biblioteca.excepciones.BadRequestException;
import com.ceiba.biblioteca.excepciones.ResourceNotFoundException;
import com.ceiba.biblioteca.modelos.entidades.TipoUsuario;
import com.ceiba.biblioteca.modelos.entidades.Usuario;
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
    public TipoUsuario obtenerTipoUsuarioPorId(Integer id) {
        return tipoUsuarioRepositorio.findById(id).orElseThrow(() -> new BadRequestException(
                MensajesConstantes.TIPO_USUARIO_NO_PERMITIDO_MENSAJE
        ));
    }

    @Override
    public Usuario buscarPorIdentificacion(String identificacionUsuario) {
        return usuarioRepositorio.findById(identificacionUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MensajesConstantes.USUARIO_NO_ENCONTRADO_IDENTIFICACION_MENSAJE.concat(" " + identificacionUsuario)
                ));
    }

}
