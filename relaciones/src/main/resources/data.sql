-- Insertar datos para la tabla de géneros
INSERT INTO genero (nombre) VALUES ('Pop');
INSERT INTO genero (nombre) VALUES ('Rock');
INSERT INTO genero (nombre) VALUES ('Electrónica');
INSERT INTO genero (nombre) VALUES ('Regaetton');
INSERT INTO genero (nombre) VALUES ('Rumba');

-- Insertar datos para la tabla de roles
INSERT INTO rol (name) VALUES ('ROLE_ADMIN');
INSERT INTO rol (name) VALUES ('ROLE_USUARIO');
INSERT INTO rol (name) VALUES ('ROLE_ARTISTA');

-- Insertar datos para la tabla de canciones
INSERT INTO canciones (nombre, fecha_creacion, artistas) VALUES ('Una vez', '2022-01-01', 'Mora');
INSERT INTO canciones (nombre, fecha_creacion, artistas) VALUES ('Fuego', '2020-05-24', 'Estopa');
INSERT INTO canciones (nombre, fecha_creacion, artistas) VALUES ('Amigos', '2023-11-10', 'J Valbin');
INSERT INTO canciones (nombre, fecha_creacion, artistas) VALUES ('El Mambo', '2023-05-28', 'Kiko Rivera');
INSERT INTO canciones (nombre, fecha_creacion, artistas) VALUES ('Rumbatón', '2019-04-16', 'Daddy Yankee');
INSERT INTO canciones (nombre, fecha_creacion, artistas) VALUES ('Yo soy libre', '2022-03-01', 'Bombai');

-- Insertar datos para la tabla de usuarios
INSERT INTO usuario (username, password, enabled) VALUES ('Pepe', '$2a$10$XFCCA3lEhpIJm7Gpv0wENOrxSgu1VKzJ6ncWmtqv9h/P9M8D2ZB8u', true);
INSERT INTO usuario (username, password, enabled) VALUES ('Juan', '$2a$10$aGCwNuP1.lkr6NVdg1Au0eOcjGcHl5h1.BL6R0hdfz/.eX8tl5oxa', true);
INSERT INTO usuario (username, password, enabled) VALUES ('Lola', '$2a$10$5Uxcp2WFdwqk2P8M36NKy.pIXkv7kmKzNjeIdK/F2vTdgjg6OlSsi', true);
INSERT INTO usuario (username, password, enabled) VALUES ('Mora', '$2a$10$HICfEF6I1rPyjrhfO7h51eGDiCoc7dbpJxTR9yHWwMcVMWIYZZ7z2', true);
INSERT INTO usuario (username, password, enabled) VALUES ('Estopa','$2a$10$b38/M6v7DBrXxaq.vN.7wuuqMrJmqmSiRa1sQ342ZBJFJSIy2pZgK', true);
INSERT INTO usuario (username, password, enabled) VALUES ('J Valbin', '$2a$10$.I9pBGJWuIPvCP5TeRPYKeTKhq/gChsEmBzdrGAlhDMKfZPj/Y2ei', true);
INSERT INTO usuario (username, password, enabled) VALUES ('Kiko Rivera', '$2a$10$ULf.cDVZ.Ci.NRCWj3frmeX/JkaYb4GU8u1D.3TrFZWJyGcZUATly', true);
INSERT INTO usuario (username, password, enabled) VALUES ('Daddy Yankee', '$2a$10$sgL0AVEEIeVQuxjUkiCkne.YOQ5mgQ5BRbuFZxHouEWDG5TkVjKgi', true);
INSERT INTO usuario (username, password, enabled) VALUES ('Bombai', '$2a$10$1pqzraUJ59b7f/8mR7jK0eY/Rl31iz0h1N057WsJixPpc9E.XdyqC', true);
INSERT INTO usuario (username, password, enabled) VALUES ('BadBo', '$2a$10$8DUvsnWoqUedTH4lIXPD5.d285jhTdsbIyrch/0IyOpSr.CqS.P1a', true);


-- Insertar datos para las relaciones entre canciones y géneros
INSERT INTO cancion_genero (id_cancion, id_genero) VALUES (1, 4);
INSERT INTO cancion_genero (id_cancion, id_genero) VALUES (2, 1);
INSERT INTO cancion_genero (id_cancion, id_genero) VALUES (2, 2);
INSERT INTO cancion_genero (id_cancion, id_genero) VALUES (3, 4);
INSERT INTO cancion_genero (id_cancion, id_genero) VALUES (4, 4);
INSERT INTO cancion_genero (id_cancion, id_genero) VALUES (5, 5);
INSERT INTO cancion_genero (id_cancion, id_genero) VALUES (6, 1);

-- Insertar datos para las relaciones entre usuarios y canciones
INSERT INTO usuario_cancion (id_usuario, id_cancion) VALUES (4, 1); -- Asociar Usuario 1 con Canción 1
INSERT INTO usuario_cancion (id_usuario, id_cancion) VALUES (10, 1); -- Asociar Usuario 1 con Canción 1
INSERT INTO usuario_cancion (id_usuario, id_cancion) VALUES (5, 2); -- Asociar Usuario 1 con Canción 2
INSERT INTO usuario_cancion (id_usuario, id_cancion) VALUES (6, 3); -- Asociar Usuario 2 con Canción 1
INSERT INTO usuario_cancion (id_usuario, id_cancion) VALUES (7, 4); -- Asociar Usuario 2 con Canción 1
INSERT INTO usuario_cancion (id_usuario, id_cancion) VALUES (8, 5); -- Asociar Usuario 2 con Canción 1
INSERT INTO usuario_cancion (id_usuario, id_cancion) VALUES (9, 6); -- Asociar Usuario 2 con Canción 1

-- Insertar datos para las relaciones entre usuarios y roles
INSERT INTO users_roles (rol_id, user_id) VALUES (1, 1); -- Asociar el rol de admin al usuario 1
INSERT INTO users_roles (rol_id, user_id) VALUES (2, 2); -- Asociar el rol de usuario al usurario 2
INSERT INTO users_roles (rol_id, user_id) VALUES (2, 3); -- Asociar el rol de usuario al usurario 2
INSERT INTO users_roles (rol_id, user_id) VALUES (3, 4); -- Asociar el rol de artista al usuario 3
INSERT INTO users_roles (rol_id, user_id) VALUES (3, 5); -- Asociar el rol de artista al usuario 3
INSERT INTO users_roles (rol_id, user_id) VALUES (3, 6); -- Asociar el rol de artista al usuario 3
INSERT INTO users_roles (rol_id, user_id) VALUES (3, 7); -- Asociar el rol de artista al usuario 3
INSERT INTO users_roles (rol_id, user_id) VALUES (3, 8); -- Asociar el rol de artista al usuario 3
INSERT INTO users_roles (rol_id, user_id) VALUES (3, 9); -- Asociar el rol de artista al usuario 3
INSERT INTO users_roles (rol_id, user_id) VALUES (3, 10); -- Asociar el rol de artista al usuario 3