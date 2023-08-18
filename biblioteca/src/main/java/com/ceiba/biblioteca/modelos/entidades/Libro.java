package com.ceiba.biblioteca.modelos.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder(toBuilder = true)
@Table(name = "libros")
public class Libro {

    @Id
    @Column(name = "isbn", nullable = false)
    @Size(min = 5, max = 10)
    private String isbn;

    @Column(name = "titulo", nullable = false)
    @Size(min = 5, max = 30)
    private String titulo;

    @Column(name = "ano_publicacion", nullable = false)
    @Min(value = -1500, message = "El año de publicación debe ser al menos -1500 a. C")
    @Max(value = 2023, message = "El año de publicación debe ser máximo 2023")
    private int anoPublicacion;

    @Column(name = "resumen", nullable = false)
    @Size(min = 100, max = 200)
    private String resumen;

    @OneToMany(
            mappedBy = "libro",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Prestamo> prestamos;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

}
