package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.dto.PrestamoPeticionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaCreacionDto;
import com.ceiba.biblioteca.dto.PrestamoRespuestaDetalleDto;

public interface PrestamoServicio {

    PrestamoRespuestaCreacionDto crearPrestamo(PrestamoPeticionDto prestamoPeticionDto);

    PrestamoRespuestaDetalleDto obtenerPrestamoPorId(Integer id);

}
