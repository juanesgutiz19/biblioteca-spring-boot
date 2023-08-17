package com.ceiba.biblioteca.modelos.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder(toBuilder = true)
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_maxima_devolucion", nullable = false)
    private LocalDate fechaMaximaDevolucion;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

}
