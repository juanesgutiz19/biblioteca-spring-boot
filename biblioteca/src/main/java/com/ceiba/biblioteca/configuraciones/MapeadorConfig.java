package com.ceiba.biblioteca.configuraciones;

import com.ceiba.biblioteca.mapeadores.PrestamoMapeador;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapeadorConfig {

    @Bean
    public PrestamoMapeador prestamoMapeador() {
        return Mappers.getMapper(PrestamoMapeador.class);
    }

}
