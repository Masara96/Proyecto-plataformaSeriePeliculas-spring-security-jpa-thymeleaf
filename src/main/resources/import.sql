INSERT INTO `users` (username,password,enabled) VALUES ('admin','$2a$10$pe1OGjq0GCTxTTapKmQDe.INCWu4heOcCRscIL9LUkjoLMxzW7zau',1);
INSERT INTO `users` (username,password,enabled) VALUES ('martin','$2a$10$DnYSLujA33Btkw.k1uDC5OyRT7Hs7VGi054dGBtDP29DesOa57sHq',1);

INSERT INTO `authorities` (user_id,authority) VALUES ('1','ROLE_ADMIN');
INSERT INTO `authorities` (user_id,authority) VALUES ('1','ROLE_USER');
INSERT INTO `authorities` (user_id,authority) VALUES ('2','ROLE_USER');



INSERT INTO personajes(nombre,edad,peso,historia,detalle) VALUES ('Peter Parker','25',57.6,'Un vago distinto','Algun detalle');
INSERT INTO personajes(nombre,edad,peso,historia,detalle) VALUES ('Mary Jane','25',57.6,'ALgo','Algun detalle');
INSERT INTO personajes(nombre,edad,peso,historia,detalle) VALUES ('Harold_lyman','25',57.6,'ALgo','Algun detalle');
INSERT INTO personajes(nombre,edad,peso,historia,detalle) VALUES ('Beto','25',57.6,'Un vago distinto','Algun detalle');


INSERT INTO pelicula_serie(calificacion,create_at,imagen,tipo,titulo,sinopsis,descripcion) VALUES ('4',NOW(),null,'PELICULA','The amazing spiderman',"Es el hombre araña","Es un chico comun que le pico una araña");
INSERT INTO pelicula_serie(calificacion,create_at,imagen,tipo,titulo,sinopsis,descripcion) VALUES ('1',NOW(),null,'PELICULA','Batman',"Es el hombre murcielago","Es un chico comun que se volvio un justiciero");
INSERT INTO pelicula_serie(calificacion,create_at,imagen,tipo,titulo) VALUES ('2',NOW(),null,'SERIE','Flash');
INSERT INTO pelicula_serie(calificacion,create_at,imagen,tipo,titulo) VALUES ('3',NOW(),null,'SERIE','Linterna Verde');
INSERT INTO pelicula_serie(calificacion,create_at,imagen,tipo,titulo) VALUES ('5',NOW(),null,'PELICULA','Infinity wars');