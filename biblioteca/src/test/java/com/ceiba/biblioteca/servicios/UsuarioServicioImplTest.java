package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.excepciones.BadRequestException;
import com.ceiba.biblioteca.excepciones.ResourceNotFoundException;
import com.ceiba.biblioteca.modelos.entidades.TipoIdentificacion;
import com.ceiba.biblioteca.modelos.entidades.TipoUsuario;
import com.ceiba.biblioteca.modelos.entidades.Usuario;
import com.ceiba.biblioteca.modelos.enums.AbreviaturaTipoIdentificacionEnum;
import com.ceiba.biblioteca.modelos.enums.NombreTipoUsuarioEnum;
import com.ceiba.biblioteca.repositorios.TipoUsuarioRepositorio;
import com.ceiba.biblioteca.repositorios.UsuarioRepositorio;
import com.ceiba.biblioteca.servicios.impl.UsuarioServicioImpl;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UsuarioServicioImplTest {

    @InjectMocks
    UsuarioServicioImpl usuarioServicio;
    @Mock
    TipoUsuarioRepositorio tipoUsuarioRepositorio;

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Test
    public void cuandoObtengaElTipoUsuarioPorIdExistenteEntoncesDevuelveElTipoDeUsuarioCorrectamente() {
        // Arrange
        Integer tipoUsuarioId = 1;
        TipoUsuario tipoUsuario = TipoUsuario.builder()
                .id(tipoUsuarioId)
                .nombre(NombreTipoUsuarioEnum.INVITADO)
                .descripcion("Usuario invitado de la biblioteca")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();

        when(tipoUsuarioRepositorio.findById(tipoUsuarioId)).thenReturn(Optional.of(tipoUsuario));

        // Act
        TipoUsuario tipoUsuarioDevuelto = usuarioServicio.obtenerTipoUsuarioPorId(tipoUsuarioId);

        // Assert
        assertNotNull(tipoUsuarioDevuelto);
        assertEquals(tipoUsuario.getId(), tipoUsuarioDevuelto.getId());
        assertEquals(tipoUsuario.getNombre(), tipoUsuarioDevuelto.getNombre());
        assertEquals(tipoUsuario.getDescripcion(), tipoUsuarioDevuelto.getDescripcion());
        assertEquals(tipoUsuario.getCreadoEn(), tipoUsuarioDevuelto.getCreadoEn());
        assertEquals(tipoUsuario.getActualizadoEn(), tipoUsuarioDevuelto.getActualizadoEn());
    }

    @Test
    public void cuandoObtengaElTipoUsuarioPorIdNoExistenteEntoncesLanzaUnaBadRequestException() {
        // Arrange
        Integer tipoUsuarioId = 1;
        when(tipoUsuarioRepositorio.findById(tipoUsuarioId)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(BadRequestException.class, () -> {
            usuarioServicio.obtenerTipoUsuarioPorId(tipoUsuarioId);
        });
    }

    @Test
    public void cuandoObtengaElUsuarioPorIdentificacionExistenteEntoncesDevuelveElUsuarioCorrectamente() {
        String identificacion = "1547885744";
        Usuario usuario = Usuario.builder()
                .identificacionUsuario(identificacion)
                .tipoUsuario(TipoUsuario.builder()
                        .id(1)
                        .nombre(NombreTipoUsuarioEnum.INVITADO)
                        .descripcion("Usuario invitado de la biblioteca")
                        .creadoEn(LocalDateTime.now())
                        .actualizadoEn(LocalDateTime.now())
                        .build())
                .tipoIdentificacion(TipoIdentificacion.builder()
                        .id(1)
                        .abreviatura(AbreviaturaTipoIdentificacionEnum.CC)
                        .descripcion("Cédula de ciudadanía")
                        .creadoEn(LocalDateTime.now())
                        .actualizadoEn(LocalDateTime.now())
                        .build())
                .nombreCompleto("Erika Sofia Cardona Zuluaga")
                .email("erika@test.com")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();

        when(usuarioRepositorio.findById(identificacion)).thenReturn(Optional.of(usuario));

        Usuario usuarioDevuelto = usuarioServicio.buscarPorIdentificacion(identificacion);

        assertNotNull(usuarioDevuelto);
        assertEquals(usuario.getIdentificacionUsuario(), usuarioDevuelto.getIdentificacionUsuario());
        assertEquals(usuario.getTipoUsuario().getId(), usuarioDevuelto.getTipoUsuario().getId());
        assertEquals(usuario.getTipoUsuario().getNombre(), usuarioDevuelto.getTipoUsuario().getNombre());
        assertEquals(usuario.getTipoUsuario().getDescripcion(), usuarioDevuelto.getTipoUsuario().getDescripcion());
        assertEquals(usuario.getTipoUsuario().getCreadoEn(), usuarioDevuelto.getTipoUsuario().getCreadoEn());
        assertEquals(usuario.getTipoUsuario().getActualizadoEn(), usuarioDevuelto.getTipoUsuario().getActualizadoEn());
        assertEquals(usuario.getTipoIdentificacion().getId(), usuarioDevuelto.getTipoIdentificacion().getId());
        assertEquals(usuario.getTipoIdentificacion().getAbreviatura(), usuarioDevuelto.getTipoIdentificacion().getAbreviatura());
        assertEquals(usuario.getTipoIdentificacion().getDescripcion(), usuarioDevuelto.getTipoIdentificacion().getDescripcion());
        assertEquals(usuario.getTipoIdentificacion().getCreadoEn(), usuarioDevuelto.getTipoIdentificacion().getCreadoEn());
        assertEquals(usuario.getTipoIdentificacion().getActualizadoEn(), usuarioDevuelto.getTipoIdentificacion().getActualizadoEn());

        assertEquals(usuario.getNombreCompleto(), usuarioDevuelto.getNombreCompleto());
        assertEquals(usuario.getEmail(), usuarioDevuelto.getEmail());
        assertEquals(usuario.getCreadoEn(), usuarioDevuelto.getCreadoEn());
        assertEquals(usuario.getActualizadoEn(), usuarioDevuelto.getActualizadoEn());
    }

    @Test
    public void cuandoObtengaElUsuarioPorIdentificacionNoExistenteEntoncesLanzaUnResourceNotFoundException() {
        // Arrange
        String identificacion = "1152665788";
        when(usuarioRepositorio.findById(identificacion)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            usuarioServicio.buscarPorIdentificacion(identificacion);
        });
    }

}
