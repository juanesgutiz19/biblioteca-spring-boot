package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.excepciones.ResourceNotFoundException;
import com.ceiba.biblioteca.modelos.entidades.Libro;
import com.ceiba.biblioteca.repositorios.LibroRepositorio;
import com.ceiba.biblioteca.servicios.impl.LibroServicioImpl;

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
public class LibroServicioImplTest {

    @InjectMocks
    LibroServicioImpl libroServicio;
    @Mock
    LibroRepositorio libroRepositorio;

    @Test
    public void cuandoBusquePorIsbnExistenteEntoncesDebeDevolverElLibroCorrectamente() {
        // Arrange
        String isbn = "DASD154212";
        Libro libro = Libro.builder()
                .isbn(isbn)
                .anoPublicacion(1929)
                .titulo("Las aventuras de Tintín")
                .resumen("Las aventuras de Tintín, creadas por Hergé en 1929, siguen las emocionantes travesías del intrépido reportero Tintín y su fiel perro Milú.")
                .creadoEn(LocalDateTime.now())
                .actualizadoEn(LocalDateTime.now())
                .build();
        when(libroRepositorio.findByIsbn(isbn)).thenReturn(Optional.of(libro));

        // Act
        Libro libroDevuelto = libroServicio.buscarPorIsbn(isbn);

        // Assert
        assertNotNull(libroDevuelto);
        assertEquals(libro.getIsbn(), libroDevuelto.getIsbn());
        assertEquals(libro.getAnoPublicacion(), libroDevuelto.getAnoPublicacion());
        assertEquals(libro.getTitulo(), libroDevuelto.getTitulo());
        assertEquals(libro.getResumen(), libroDevuelto.getResumen());
        assertEquals(libro.getCreadoEn(), libroDevuelto.getCreadoEn());
        assertEquals(libro.getActualizadoEn(), libroDevuelto.getActualizadoEn());
    }

    @Test
    public void cuandoBusquePorIsbnNoExistenteEntoncesDebeLanzarUnResourceNotFoundException() {
        // Arrange
        String isbn = "LASD154214";
        when(libroRepositorio.findByIsbn(isbn)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            libroServicio.buscarPorIsbn(isbn);
        });
    }

}
