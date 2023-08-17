package com.ceiba.biblioteca.servicios.impl;

import com.ceiba.biblioteca.dto.PrestamoPeticionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaCreacionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaDetalleDto;
import com.ceiba.biblioteca.modelos.entidades.TipoUsuario;
import com.ceiba.biblioteca.modelos.enums.NombreTipoUsuarioEnum;
import com.ceiba.biblioteca.repositorios.PrestamoRepositorio;
import com.ceiba.biblioteca.servicios.PrestamoServicio;
import com.ceiba.biblioteca.servicios.UsuarioServicio;
import com.ceiba.biblioteca.utilidades.FechasUtilidades;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PrestamoServicioImpl implements PrestamoServicio {

    private final PrestamoRepositorio prestamoRepositorio;
    private final UsuarioServicio usuarioServicio;

    @Override
    public PrestamoRespuestaCreacionDto crearPrestamo(PrestamoPeticionDto prestamoPeticionDto) {
        Optional<TipoUsuario> tipoUsuario = usuarioServicio.obtenerTipoUsuarioPorId(prestamoPeticionDto.getTipoUsuario());
        LocalDate fechaDevolucion;
        if (tipoUsuario.isPresent()) {
            fechaDevolucion = calcularFechaDevolucion(tipoUsuario.get().getNombre());
        }

        return new PrestamoRespuestaCreacionDto(1, LocalDate.now());
    }

    @Override
    public PrestamoRespuestaDetalleDto obtenerPrestamoPorId(Integer id) {
        return null;
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
