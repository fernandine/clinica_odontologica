-- -------------------------------------------
-- Usuários e perfis
-- -------------------------------------------
INSERT INTO tb_user (name, phone, email, appointment_date, dentist, description, password) VALUES ('Alex', '31971736555', 'alex@gmail.com', NOW(), 'Dr.Juarez', 'Descrição...', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, phone, email, appointment_date, dentist, description, password) VALUES ('Maria', '31971736555', 'maria@gmail.com', NOW(), 'Dra.Silvia', 'Descrição...', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_PATIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_DENTIST');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);


-- -----------------------------------------------------
-- Endereços
-- -----------------------------------------------------
INSERT INTO tb_address(cep, logradouro,complemento, bairro, localidade, uf, user_id) VALUES ('34001090','Rua Levy Firmino Alves', 'casa A', 'Parque Santo Antônio', 'Nova Lima', 'MG', 1);
INSERT INTO tb_address(cep, logradouro,complemento, bairro, localidade, uf, user_id) VALUES ('34012856','Rua Seis', 'casa B', 'Santa Rita', 'Nova Lima', 'MG', 2);