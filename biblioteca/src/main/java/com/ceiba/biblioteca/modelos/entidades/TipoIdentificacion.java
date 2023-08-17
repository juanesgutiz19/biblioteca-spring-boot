package com.ceiba.biblioteca.modelos.entidades;

import com.ceiba.biblioteca.modelos.enums.AbreviaturaTipoIdentificacionEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder(toBuilder = true)
@Table(name = "tipos_identificacion")
public class TipoIdentificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "abreviatura", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private AbreviaturaTipoIdentificacionEnum abreviatura;

    @Size(min = 10, max = 60)
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

}
