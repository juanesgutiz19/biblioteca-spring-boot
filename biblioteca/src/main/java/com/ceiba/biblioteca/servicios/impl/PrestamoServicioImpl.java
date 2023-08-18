package com.ceiba.biblioteca.servicios.impl;

import com.ceiba.biblioteca.dto.PrestamoPeticionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaCreacionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaDetalleDto;
import com.ceiba.biblioteca.excepciones.BadRequestException;
import com.ceiba.biblioteca.excepciones.ResourceNotFoundException;
import com.ceiba.biblioteca.mapeadores.PrestamoMapeador;
import com.ceiba.biblioteca.modelos.entidades.Prestamo;
import com.ceiba.biblioteca.modelos.entidades.TipoUsuario;
import com.ceiba.biblioteca.modelos.enums.NombreTipoUsuarioEnum;
import com.ceiba.biblioteca.repositorios.PrestamoRepositorio;
import com.ceiba.biblioteca.servicios.LibroServicio;
import com.ceiba.biblioteca.servicios.PrestamoServicio;
import com.ceiba.biblioteca.servicios.UsuarioServicio;
import com.ceiba.biblioteca.utilidades.FechasUtilidades;

import com.ceiba.biblioteca.utilidades.MensajesConstantes;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class PrestamoServicioImpl implements PrestamoServicio {

    private final PrestamoRepositorio prestamoRepositorio;
    private final UsuarioServicio usuarioServicio;
    private final LibroServicio libroServicio;
    private final PrestamoMapeador prestamoMapeador;

    @Override
    public PrestamoRespuestaCreacionDto crearPrestamo(PrestamoPeticionDto prestamoPeticionDto) {
        // Validaciones de reglas de negocio
        usuarioServicio.buscarPorIdentificacion(prestamoPeticionDto.getIdentificacionUsuario());
        libroServicio.buscarPorIsbn(prestamoPeticionDto.getIsbn());
        TipoUsuario tipoUsuario = usuarioServicio.obtenerTipoUsuarioPorId(prestamoPeticionDto.getTipoUsuario());
        NombreTipoUsuarioEnum nombreTipoUsuario = tipoUsuario.getNombre();
        if (nombreTipoUsuario.name().equalsIgnoreCase("INVITADO")) {
            List<Prestamo> listaPrestamosDeUsuario = prestamoRepositorio.findByUsuarioIdentificacionUsuario(prestamoPeticionDto.getIdentificacionUsuario());
            if (!listaPrestamosDeUsuario.isEmpty()) {
                throw new BadRequestException("El usuario con identificación " + prestamoPeticionDto.getIdentificacionUsuario() + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
            }
        }

        // Cálculo de fecha máxima de devolución
        LocalDate fechaMaximaDevolucionCalculada = calcularFechaDevolucion(nombreTipoUsuario);

        // Preparación de préstamo a guardar en la base de datos
        Prestamo prestamoAGuardar = prestamoMapeador.prestamoPeticionDtoToPrestamo(prestamoPeticionDto);
        prestamoAGuardar.setFechaMaximaDevolucion(fechaMaximaDevolucionCalculada);

        return prestamoMapeador.prestamoToPrestamoRespuestaCreacionDto(
                prestamoRepositorio.save(
                        prestamoAGuardar
                )
        );
    }

    @Override
    public PrestamoRespuestaDetalleDto obtenerPrestamoPorId(Integer id) {
        return prestamoMapeador.prestamoToPrestamoRespuestaDetalleDto(
                prestamoRepositorio.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException(
                                MensajesConstantes.PRESTAMO_NO_ENCONTRADO_ID_MENSAJE.concat(" " + id)
                        )
                )
        );
    }

    private LocalDate calcularFechaDevolucion(NombreTipoUsuarioEnum nombreTipoUsuario) {
        int numeroDeDiasASumar = 0;
        switch (nombreTipoUsuario) {
            case AFILIADO:
                numeroDeDiasASumar = 10;
                break;
            case EMPLEADO:
                numeroDeDiasASumar = 8;
                break;
            case INVITADO:
                numeroDeDiasASumar = 7;
                break;
        }
        return FechasUtilidades.agregarDiasOmitiendoFinesDeSemana(LocalDate.now(), numeroDeDiasASumar);
    }
}
