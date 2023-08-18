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
    identificacion_usuario VARCHAR(10) PRIMARY KEY,
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
    isbn VARCHAR(10) PRIMARY KEY,
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
    libro_id         VARCHAR(10)           NOT NULL,
    usuario_id       INT           NOT NULL,
    fecha_maxima_devolucion DATE          NOT NULL,
    creado_en        TIMESTAMP(6)  NOT NULL,
    actualizado_en   TIMESTAMP(6)  NOT NULL,
    FOREIGN KEY (libro_id) REFERENCES libros (isbn),
    FOREIGN KEY (usuario_id) REFERENCES usuarios (identificacion_usuario)
);

-- Inserciones a la tabla tipos_identificacion
INSERT INTO tipos_identificacion (abreviatura, descripcion, creado_en, actualizado_en)
VALUES ('CC', 'Cédula de Ciudadanía', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('TI', 'Tarjeta de Identidad', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('CE', 'Cédula de Extranjería', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserciones a la tabla tipos_usuario
INSERT INTO tipos_usuario (nombre, descripcion, creado_en, actualizado_en)
VALUES ('AFILIADO', 'Usuario afiliado al sistema de bibliotecas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('EMPLEADO', 'Usuario empleado de la biblioteca', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('INVITADO', 'Usuario invitado al sistema de bibliotecas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserciones a la tabla usuarios
INSERT INTO usuarios (identificacion_usuario, tipo_identificacion_id, tipo_usuario_id, nombre_completo, email, creado_en, actualizado_en)
VALUES ('974148', 1, 1, 'Juan Rodriguez Mazo', 'juan@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('7481545', 2, 2, 'Luisa Arciniegas Zuluaga', 'luisa@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('1111111111', 3, 3, 'Adriana Pérez Arango', 'adriana@hotmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('74851254', 3, 3, 'Jesús Pérez Arango', 'jesus@hotmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserciones a la tabla libros
INSERT INTO libros (isbn, titulo, ano_publicacion, resumen, creado_en, actualizado_en)
VALUES ('ASDA7884', 'Cien años de soledad', 1967, 'Una novela icónica del realismo mágico.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('AWQ489', 'Don Quijote de la Mancha', 1605, 'La famosa obra de Miguel de Cervantes.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('EQWQW8545', 'Tin Tin', 1605, 'La famosa obra de Miguel de Cervantes.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);