CREATE TABLE IF NOT EXISTS tipos_identificacion
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    abreviatura    VARCHAR(2)   NOT NULL UNIQUE,
    descripcion    VARCHAR(60)  NOT NULL,
    creado_en      TIMESTAMP(6) NOT NULL,
    actualizado_en TIMESTAMP(6) NOT NULL,
    CHECK (abreviatura IN ('CC', 'TI', 'CE'))
);

CREATE INDEX idx_abreviatura ON tipos_identificacion (abreviatura);

CREATE TABLE IF NOT EXISTS tipos_usuario
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    nombre         VARCHAR(8)   NOT NULL UNIQUE,
    descripcion    VARCHAR(60)  NOT NULL,
    creado_en      TIMESTAMP(6) NOT NULL,
    actualizado_en TIMESTAMP(6) NOT NULL,
    CHECK (nombre IN ('AFILIADO', 'EMPLEADO', 'INVITADO'))
);

CREATE INDEX idx_nombre ON tipos_usuario (nombre);

CREATE TABLE IF NOT EXISTS usuarios
(
    id                     VARCHAR(10) PRIMARY KEY,
    tipo_identificacion_id INT      NOT NULL,
    tipo_usuario_id        INT      NOT NULL,
    nombre_completo        VARCHAR(60)  NOT NULL,
    email                  VARCHAR(255) NOT NULL UNIQUE,
    creado_en              TIMESTAMP(6) NOT NULL,
    actualizado_en         TIMESTAMP(6) NOT NULL,
    FOREIGN KEY (tipo_identificacion_id) REFERENCES tipos_identificacion (id),
    FOREIGN KEY (tipo_usuario_id) REFERENCES tipos_usuario (id)
);

CREATE INDEX idx_email ON usuarios (email);

CREATE TABLE IF NOT EXISTS libros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(10) NOT NULL UNIQUE,
    titulo VARCHAR(30) NOT NULL,
    ano_publicacion INT NOT NULL,
    resumen VARCHAR(200) NOT NULL,
    creado_en TIMESTAMP(6) NOT NULL,
    actualizado_en TIMESTAMP(6) NOT NULL
);

CREATE INDEX idx_isbn ON libros (isbn);

CREATE TABLE IF NOT EXISTS prestamos
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    libro_id         INT           NOT NULL,
    usuario_id       INT           NOT NULL,
    fecha_maxima_devolucion DATE          NOT NULL,
    creado_en        TIMESTAMP(6)  NOT NULL,
    actualizado_en   TIMESTAMP(6)  NOT NULL,
    FOREIGN KEY (libro_id) REFERENCES libros (id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);