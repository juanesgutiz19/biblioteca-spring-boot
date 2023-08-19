package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.dto.PrestamoPeticionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaCreacionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaDetalleDto;
import com.ceiba.biblioteca.excepciones.BadRequestException;
import com.ceiba.biblioteca.excepciones.ResourceNotFoundException;
import com.ceiba.biblioteca.mapeadores.PrestamoMapeador;
import com.ceiba.biblioteca.modelos.entidades.*;
import com.ceiba.biblioteca.modelos.enums.AbreviaturaTipoIdentificacionEnum;
import com.ceiba.biblioteca.modelos.enums.NombreTipoUsuarioEnum;
import com.ceiba.biblioteca.repositorios.PrestamoRepositorio;
import com.ceiba.biblioteca.servicios.impl.LibroServicioImpl;
import com.ceiba.biblioteca.servicios.impl.PrestamoServicioImpl;
import com.ceiba.biblioteca.servicios.impl.UsuarioServicioImpl;

import com.ceiba.biblioteca.utilidades.FechasUtilidades;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)


public class PrestamoServicioImplTest {

    @InjectMocks
    PrestamoServicioImpl prestamoServicio;

    @Mock
    UsuarioServicioImpl usuarioServicio;

    @Mock
    LibroServicioImpl libroServicio;

    @Mock
    PrestamoRepositorio prestamoRepositorio;

    @Mock
    private PrestamoMapeador prestamoMapeador;

    @Test
    public void cuandoObtienePorIdExistenteEntoncesDevuelveElPrestamoCorrectamente() {
        // Arrange
        Integer prestamoId = 1;
        Prestamo prestamo = Prestamo.builder()
                .id(prestamoId)
                .fechaMaximaDevolucion(LocalDate.now())
                .usuario(Usuario.builder()
                        .identificacionUsuario("8521444777")
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
                        .build())
                .libro(Libro.builder()
                        .isbn("DASD154219")
                        .anoPublicacion(1947)
                        .titulo("Nineteen Eighty-Four")
                        .resumen("Una novela distópica de George Orwell que describe un futuro totalitario y opresivo en el que el gobierno controla cada aspecto de la vida de las personas.")
                        .creadoEn(LocalDateTime.now())
                        .actualizadoEn(LocalDateTime.now())
                        .build())
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        when(prestamoRepositorio.findById(prestamoId)).thenReturn(Optional.of(prestamo));
        PrestamoRespuestaDetalleDto prestamoRespuestaDetalleDto = PrestamoRespuestaDetalleDto.builder()
                .id(prestamo.getId())
                .fechaMaximaDevolucion(prestamo.getFechaMaximaDevolucion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .identificacionUsuario(prestamo.getUsuario().getIdentificacionUsuario())
                .isbn(prestamo.getLibro().getIsbn())
                .tipoUsuario(prestamo.getUsuario().getTipoUsuario().getId())
                .build();
        when(prestamoMapeador.prestamoToPrestamoRespuestaDetalleDto(prestamo)).thenReturn(prestamoRespuestaDetalleDto);

        // Act
        PrestamoRespuestaDetalleDto prestamoRespuestaDetalleDtoDevuelto = prestamoServicio.obtenerPrestamoPorId(prestamoId);

        // Assert
        assertNotNull(prestamoRespuestaDetalleDtoDevuelto);
        assertEquals(prestamoRespuestaDetalleDto.getId(), prestamoRespuestaDetalleDtoDevuelto.getId());
        assertEquals(prestamoRespuestaDetalleDto.getIsbn(), prestamoRespuestaDetalleDtoDevuelto.getIsbn());
        assertEquals(prestamoRespuestaDetalleDto.getTipoUsuario(), prestamoRespuestaDetalleDtoDevuelto.getTipoUsuario());
        assertEquals(prestamoRespuestaDetalleDto.getFechaMaximaDevolucion(), prestamoRespuestaDetalleDtoDevuelto.getFechaMaximaDevolucion());
        assertEquals(prestamoRespuestaDetalleDto.getIdentificacionUsuario(), prestamoRespuestaDetalleDtoDevuelto.getIdentificacionUsuario());
    }

    @Test
    public void cuandoObtienePorIdNoExistenteEntoncesLanzaUnaResourceNotFoundException() {
        // Arrange
        Integer prestamoId = 1;
        when(prestamoRepositorio.findById(prestamoId)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            prestamoServicio.obtenerPrestamoPorId(prestamoId);
        });
    }

    @Test
    public void cuandoSeEnvianDatosValidosElPrestamoEsCreadoSatisfactoriamente() {
        // Arrange
        LocalDate fechaMaximaDevolucion = LocalDate.now();
        PrestamoPeticionDto peticionDto = PrestamoPeticionDto.builder()
                .identificacionUsuario("1123445322")
                .isbn("DASD154212")
                .tipoUsuario(1)
                .build();
        TipoUsuario tipoUsuario = TipoUsuario.builder()
                .id(1)
                .nombre(NombreTipoUsuarioEnum.AFILIADO)
                .descripcion("Usuario afiliado de la biblioteca")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        when(usuarioServicio.obtenerTipoUsuarioPorId(peticionDto.getTipoUsuario())).thenReturn(tipoUsuario);
        Prestamo prestamo = Prestamo.builder()
                .id(1)
                .usuario(Usuario.builder()
                        .identificacionUsuario("1123445322")
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
                        .build())
                .libro(Libro.builder()
                        .isbn("DASD154212")
                        .anoPublicacion(1947)
                        .titulo("Nineteen Eighty-Four")
                        .resumen("Una novela distópica de George Orwell que describe un futuro totalitario y opresivo en el que el gobierno controla cada aspecto de la vida de las personas.")
                        .creadoEn(LocalDateTime.now())
                        .actualizadoEn(LocalDateTime.now())
                        .build())
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        prestamo.setFechaMaximaDevolucion(fechaMaximaDevolucion);
        PrestamoRespuestaCreacionDto prestamoRespuestaCreacionDto = PrestamoRespuestaCreacionDto.builder()
                .fechaMaximaDevolucion(FechasUtilidades.agregarDiasOmitiendoFinesDeSemana(fechaMaximaDevolucion, 7).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .id(1)
                .build();
        when(prestamoMapeador.prestamoPeticionDtoToPrestamo(peticionDto)).thenReturn(prestamo);
        when(prestamoRepositorio.save(any(Prestamo.class))).thenReturn(prestamo);
        when(prestamoMapeador.prestamoToPrestamoRespuestaCreacionDto(prestamo)).thenReturn(prestamoRespuestaCreacionDto);

        // Act
        PrestamoRespuestaCreacionDto prestamoRespuestaCreacionDtoDevuelto = prestamoServicio.crearPrestamo(peticionDto);

        // Assert
        assertNotNull(prestamoRespuestaCreacionDtoDevuelto);
        assertEquals(prestamo.getId(), prestamoRespuestaCreacionDtoDevuelto.getId());
    }

    @Test
    public void cuandoUnUsuarioInvitadoCreaUnPrestamoConUnPrestamoYaExistenteSeLanzaUnaBadRequestException() {
        PrestamoPeticionDto peticionDto = PrestamoPeticionDto.builder()
                .identificacionUsuario("1123445322")
                .isbn("DASD154212")
                .tipoUsuario(1)
                .build();
        TipoUsuario tipoUsuario = TipoUsuario.builder()
                .id(3)
                .nombre(NombreTipoUsuarioEnum.INVITADO)
                .descripcion("Usuario invitado de la biblioteca")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        when(usuarioServicio.obtenerTipoUsuarioPorId(peticionDto.getTipoUsuario())).thenReturn(tipoUsuario);

        Prestamo prestamo = Prestamo.builder()
                .id(2)
                .usuario(Usuario.builder()
                        .identificacionUsuario("1123445322")
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
                        .build())
                .libro(Libro.builder()
                        .isbn("DASD154212")
                        .anoPublicacion(1947)
                        .titulo("Nineteen Eighty-Four")
                        .resumen("Una novela distópica de George Orwell que describe un futuro totalitario y opresivo en el que el gobierno controla cada aspecto de la vida de las personas.")
                        .creadoEn(LocalDateTime.now())
                        .actualizadoEn(LocalDateTime.now())
                        .build())
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        when(usuarioServicio.obtenerTipoUsuarioPorId(peticionDto.getTipoUsuario())).thenReturn(tipoUsuario);

        when(prestamoRepositorio.findByUsuarioIdentificacionUsuario(prestamo.getUsuario().getIdentificacionUsuario())).thenReturn(Arrays.asList(Prestamo.builder()
                .id(1)
                .usuario(Usuario.builder()
                        .identificacionUsuario("1123445322")
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
                        .build())
                .libro(Libro.builder()
                        .isbn("DASD154218")
                        .anoPublicacion(1929)
                        .titulo("Las aventuras de Tintín")
                        .resumen("Las aventuras de Tintín, creadas por Hergé en 1929, siguen las emocionantes travesías del intrépido reportero Tintín y su fiel perro Milú.")
                        .creadoEn(LocalDateTime.now())
                        .actualizadoEn(LocalDateTime.now())
                        .build())
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build()));

        assertThrows(BadRequestException.class, () -> {
            prestamoServicio.crearPrestamo(peticionDto);
        });
    }
}
