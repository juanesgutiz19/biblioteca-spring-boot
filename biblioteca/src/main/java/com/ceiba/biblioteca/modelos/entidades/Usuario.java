package com.ceiba.biblioteca.modelos.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder(toBuilder = true)
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "identificacion_usuario")
    @Size(min = 5, max = 10)
    private String identificacionUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_identificacion_id", nullable = false)
    private TipoIdentificacion tipoIdentificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "nombre_completo", nullable = false)
    @Size(min = 25, max = 60)
    private String nombreCompleto;

    @Column(name = "email", unique = true, nullable = false)
    @Size(min = 5, max = 60)
    private String email;

    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Prestamo> prestamos;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

}
