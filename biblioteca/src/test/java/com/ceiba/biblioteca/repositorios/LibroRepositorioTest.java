package com.ceiba.biblioteca.repositorios;

import com.ceiba.biblioteca.modelos.entidades.Libro;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LibroRepositorioTest {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void cuandoEncuentreLibroPorIsbnEntoncesDevuelveLibro() {
        //Arrange
        Libro libro = Libro.builder()
                .isbn("DASD154212")
                .anoPublicacion(1929)
                .titulo("Las aventuras de Tintín")
                .resumen("Las aventuras de Tintín, creadas por Hergé en 1929, siguen las emocionantes travesías del intrépido reportero Tintín y su fiel perro Milú.")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        testEntityManager.persist(libro);
        testEntityManager.flush();

        //Act
        Optional<Libro> libroEncontrado = libroRepositorio.findByIsbn(libro.getIsbn());

        //Assert
        assertNotNull(libroEncontrado);
        assertAll(
                () -> assertEquals(libroEncontrado.get().getIsbn(), libro.getIsbn()),
                () -> assertEquals(libroEncontrado.get().getAnoPublicacion(), libro.getAnoPublicacion()),
                () -> assertEquals(libroEncontrado.get().getTitulo(), libro.getTitulo()),
                () -> assertEquals(libroEncontrado.get().getResumen(), libro.getResumen()),
                () -> assertEquals(libroEncontrado.get().getCreadoEn(), libro.getCreadoEn()),
                () -> assertEquals(libroEncontrado.get().getActualizadoEn(), libro.getActualizadoEn())
        );
    }

    @Test
    public void cuandoNoEncuentraLibroPorIsbnEntoncesDevuelveVacio() {
        // Act
        Optional<Libro> libroNoEncontrado = libroRepositorio.findByIsbn("DASD154216");

        // Assert
        assertFalse(libroNoEncontrado.isPresent());
    }

}