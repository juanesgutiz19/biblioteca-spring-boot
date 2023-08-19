package com.ceiba.biblioteca;

import com.ceiba.biblioteca.utilidades.FechasUtilidades;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FechasUtilidadesTest {

    @Test
    public void cuandoSeAgreganDiasPositivosSeDebeGenerarLaNuevaFechaConLosDiasDadosSumados() {
        // Arrange
        LocalDate fechaInicial = LocalDate.of(2023, 8, 1);
        int dias = 10;

        // Act
        LocalDate resultado = FechasUtilidades.agregarDiasOmitiendoFinesDeSemana(fechaInicial, dias);

        // Assert
        LocalDate fechaEsperada = LocalDate.of(2023, 8, 15);
        assertEquals(fechaEsperada, resultado);
    }

    @Test
    public void cuandoSeAgreganCeroDiasSeDebeGenerarLaMismaFecha() {
        // Arrange
        LocalDate fechaInicial = LocalDate.of(2023, 8, 18);
        int dias = 0;

        // Act
        LocalDate resultado = FechasUtilidades.agregarDiasOmitiendoFinesDeSemana(fechaInicial, dias);

        // Assert
        assertEquals(fechaInicial, resultado);
    }

}