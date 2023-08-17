CREATE TABLE IF NOT EXISTS tipos_identificacion
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    abreviatura    VARCHAR(2)   NOT NULL,
    descripcion    VARCHAR(60)  NOT NULL,
    creado_en      timestamp(6) NOT NULL,
    actualizado_en timestamp(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS tipos_usuario
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    nombre         VARCHAR(8)   NOT NULL,
    descripcion    VARCHAR(60)  NOT NULL,
    creado_en      timestamp(6) NOT NULL,
    actualizado_en timestamp(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios
(
    id                     INT PRIMARY KEY AUTO_INCREMENT,
    tipo_identificacion_id integer      NOT NULL,
    tipo_usuario_id        integer      NOT NULL,
    nombre_completo        VARCHAR(60)  NOT NULL,
    email                  VARCHAR(255) NOT NULL UNIQUE,
    creado_en              timestamp(6) NOT NULL,
    actualizado_en         timestamp(6) NOT NULL,
    FOREIGN KEY (tipo_identificacion_id) REFERENCES tipos_identificacion (id),
    FOREIGN KEY (tipo_usuario_id) REFERENCES tipos_usuario (id)
);

CREATE TABLE IF NOT EXISTS prestamos
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    isbn             VARCHAR(10)   NOT NULL,
    usuario_id       integer       NOT NULL,
    fecha_devolucion DATE          NOT NULL,
    creado_en        timestamp(6)  NOT NULL,
    actualizado_en   timestamp(6)  NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);