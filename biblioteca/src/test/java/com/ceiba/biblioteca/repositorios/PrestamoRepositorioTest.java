package com.ceiba.biblioteca.repositorios;

import com.ceiba.biblioteca.modelos.entidades.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class PrestamoRepositorioTest {

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void cuandoEncuentrePrestamosPorIdentificacionUsuarioEntoncesDevuelvePrestamosDeUsuarioDado() {
        // Arrange
        Usuario usuario1 = Usuario.builder()
                .identificacionUsuario("114855788")
                .tipoUsuario(TipoUsuario.builder()
                        .id(1)
                        .build())
                .tipoIdentificacion(TipoIdentificacion.builder()
                        .id(1)
                        .build())
                .nombreCompleto("Juan Carlos Arango Rodriguez")
                .email("carlos@test.com")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(usuario1);
        testEntityManager.flush();

        Usuario usuario2 = Usuario.builder()
                .identificacionUsuario("8521444777")
                .tipoUsuario(TipoUsuario.builder()
                        .id(1)
                        .build())
                .tipoIdentificacion(TipoIdentificacion.builder()
                        .id(1)
                        .build())
                .nombreCompleto("Erika Sofia Cardona Zuluaga")
                .email("erika@test.com")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(usuario2);
        testEntityManager.flush();

        Libro libro1 = Libro.builder()
                .isbn("DASD154212")
                .anoPublicacion(1929)
                .titulo("Las aventuras de Tintín")
                .resumen("Las aventuras de Tintín, creadas por Hergé en 1929, siguen las emocionantes travesías del intrépido reportero Tintín y su fiel perro Milú.")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(libro1);
        testEntityManager.flush();

        Prestamo prestamo1 = Prestamo.builder()
                .fechaMaximaDevolucion(LocalDate.now())
                .usuario(Usuario.builder().identificacionUsuario(usuario1.getIdentificacionUsuario()).build())
                .libro(Libro.builder()
                        .isbn(libro1.getIsbn())
                        .build())
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(prestamo1);
        testEntityManager.flush();

        Libro libro2 = Libro.builder()
                .isbn("DASD154219")
                .anoPublicacion(1947)
                .titulo("Nineteen Eighty-Four")
                .resumen("Una novela distópica de George Orwell que describe un futuro totalitario y opresivo en el que el gobierno controla cada aspecto de la vida de las personas.")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(libro2);
        testEntityManager.flush();

        Prestamo prestamo2 = Prestamo.builder()
                .fechaMaximaDevolucion(LocalDate.now())
                .usuario(Usuario.builder().identificacionUsuario(usuario2.getIdentificacionUsuario()).build())
                .libro(Libro.builder()
                        .isbn(libro2.getIsbn())
                        .build())
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(prestamo2);
        testEntityManager.flush();

        // Act
        List<Prestamo> prestamosUsuarioConIdentificacion114855788 = prestamoRepositorio.findByUsuarioIdentificacionUsuario("114855788");

        // Assert
        Assertions.assertThat(prestamosUsuarioConIdentificacion114855788)
                .isNotEmpty()
                .hasSize(1)
                .contains(prestamo1);
    }

    @Test
    public void cuandoNoEncuentrePrestamosPorIdentificacionUsuarioEntoncesDevuelveVacio() {
        // Arrange
        Usuario usuario1 = Usuario.builder()
                .identificacionUsuario("114855788")
                .tipoUsuario(TipoUsuario.builder()
                        .id(1)
                        .build())
                .tipoIdentificacion(TipoIdentificacion.builder()
                        .id(1)
                        .build())
                .nombreCompleto("Juan Carlos Arango Rodriguez")
                .email("carlos@test.com")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(usuario1);
        testEntityManager.flush();

        Usuario usuario2 = Usuario.builder()
                .identificacionUsuario("8521444777")
                .tipoUsuario(TipoUsuario.builder()
                        .id(1)
                        .build())
                .tipoIdentificacion(TipoIdentificacion.builder()
                        .id(1)
                        .build())
                .nombreCompleto("Erika Sofia Cardona Zuluaga")
                .email("erika@test.com")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(usuario2);
        testEntityManager.flush();

        Libro libro1 = Libro.builder()
                .isbn("DASD154212")
                .anoPublicacion(1929)
                .titulo("Las aventuras de Tintín")
                .resumen("Las aventuras de Tintín, creadas por Hergé en 1929, siguen las emocionantes travesías del intrépido reportero Tintín y su fiel perro Milú.")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(libro1);
        testEntityManager.flush();

        Prestamo prestamo1 = Prestamo.builder()
                .fechaMaximaDevolucion(LocalDate.now())
                .usuario(Usuario.builder().identificacionUsuario(usuario1.getIdentificacionUsuario()).build())
                .libro(Libro.builder()
                        .isbn(libro1.getIsbn())
                        .build())
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(prestamo1);
        testEntityManager.flush();

        Libro libro2 = Libro.builder()
                .isbn("DASD154219")
                .anoPublicacion(1947)
                .titulo("Nineteen Eighty-Four")
                .resumen("Una novela distópica de George Orwell que describe un futuro totalitario y opresivo en el que el gobierno controla cada aspecto de la vida de las personas.")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(libro2);
        testEntityManager.flush();

        Prestamo prestamo2 = Prestamo.builder()
                .fechaMaximaDevolucion(LocalDate.now())
                .usuario(Usuario.builder().identificacionUsuario(usuario2.getIdentificacionUsuario()).build())
                .libro(Libro.builder()
                        .isbn(libro2.getIsbn())
                        .build())
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(prestamo2);
        testEntityManager.flush();

        // Act
        List<Prestamo> prestamosUsuarioConIdentificacion114855788 = prestamoRepositorio.findByUsuarioIdentificacionUsuario("1145558477");

        // Assert
        Assertions.assertThat(prestamosUsuarioConIdentificacion114855788).isEmpty();
    }

}
