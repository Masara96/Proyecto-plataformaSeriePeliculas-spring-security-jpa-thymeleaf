INSERT INTO `users` (username,password,enabled) VALUES ('admin','$2a$10$pe1OGjq0GCTxTTapKmQDe.INCWu4heOcCRscIL9LUkjoLMxzW7zau',1);
INSERT INTO `users` (username,password,enabled) VALUES ('martin','$2a$10$DnYSLujA33Btkw.k1uDC5OyRT7Hs7VGi054dGBtDP29DesOa57sHq',1);

INSERT INTO `authorities` (user_id,authority) VALUES ('1','ROLE_ADMIN');
INSERT INTO `authorities` (user_id,authority) VALUES ('1','ROLE_USER');
INSERT INTO `authorities` (user_id,authority) VALUES ('2','ROLE_USER');