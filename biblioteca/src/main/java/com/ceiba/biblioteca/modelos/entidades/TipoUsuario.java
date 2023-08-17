package com.ceiba.biblioteca.modelos.entidades;

import com.ceiba.biblioteca.modelos.enums.NombreTipoUsuarioEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder(toBuilder = true)
@Table(name = "tipos_usuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private NombreTipoUsuarioEnum nombre;

    @NotNull
    @Size(min = 20, max = 60)
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

}
