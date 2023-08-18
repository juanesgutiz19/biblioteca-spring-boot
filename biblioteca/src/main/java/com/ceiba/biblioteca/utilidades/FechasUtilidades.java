package com.ceiba.biblioteca.utilidades;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class FechasUtilidades {

    public static LocalDate agregarDiasOmitiendoFinesDeSemana(LocalDate fecha, int dias) {
        if (dias <= 0) {
            return fecha;
        } else {
            LocalDate siguienteDia = fecha.plusDays(1);
            if (siguienteDia.getDayOfWeek() == DayOfWeek.SATURDAY || siguienteDia.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return agregarDiasOmitiendoFinesDeSemana(siguienteDia, dias);
            } else {
                return agregarDiasOmitiendoFinesDeSemana(siguienteDia, dias - 1);
            }
        }
    }

}
