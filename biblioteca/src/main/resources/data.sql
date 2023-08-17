-- Inserciones a la tabla tipos_identificacion
INSERT INTO tipos_identificacion (abreviatura, descripcion, creado_en, actualizado_en)
VALUES ('CC', 'Cédula de Ciudadanía', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('TI', 'Tarjeta de Identidad', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserciones a la tabla tipos_usuario
INSERT INTO tipos_usuario (nombre, descripcion, creado_en, actualizado_en)
VALUES ('AFILIADO', 'Usuario afiliado al sistema de bibliotecas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('EMPLEADO', 'Usuario empleado de la biblioteca', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('INVITADO', 'Usuario invitado al sistema de bibliotecas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserciones a la tabla usuarios
INSERT INTO usuarios (tipo_identificacion_id, tipo_usuario_id, nombre_completo, email, creado_en, actualizado_en)
VALUES (1, 1, 'Juan Rodriguez Mazo', 'juan@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, 'Luisa Arciniegas Zuluaga', 'luisa@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (1, 3, 'Adriana Pérez Arango', 'adriana@hotmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
