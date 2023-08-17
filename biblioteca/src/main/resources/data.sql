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
INSERT INTO usuarios (id, tipo_identificacion_id, tipo_usuario_id, nombre_completo, email, creado_en, actualizado_en)
VALUES ('1152225175', 1, 1, 'Juan Rodriguez Mazo', 'juan@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('154515485', 2, 2, 'Luisa Arciniegas Zuluaga', 'luisa@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('71685974', 3, 3, 'Adriana Pérez Arango', 'adriana@hotmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserciones a la tabla libros
INSERT INTO libros (isbn, titulo, ano_publicacion, resumen, creado_en, actualizado_en)
VALUES ('DASD154212', 'Cien años de soledad', 1967, 'Una novela icónica del realismo mágico.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('DASD154213', 'Don Quijote de la Mancha', 1605, 'La famosa obra de Miguel de Cervantes.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);