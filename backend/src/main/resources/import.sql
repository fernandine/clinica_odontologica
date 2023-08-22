-- -------------------------------------------
-- Usuários e perfis
-- -------------------------------------------
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Alex', 'Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Maria', 'Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('jean', 'fernandine', 'jfernandine@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_PATIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_DENTIST');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);

-- -------------------------------------------
-- Pacientes
-- -------------------------------------------
INSERT INTO tb_patient (name, phone, appointment_date, dentist, description) VALUES ('Maria', '31 971736555', NOW(), 'Dra.Silvia', 'Descrição...');
INSERT INTO tb_patient (name, phone, appointment_date, dentist, description) VALUES ('Maria', '31 971736555', NOW(), 'Dra.Silvia', 'Descrição...');
INSERT INTO tb_patient (name, phone, appointment_date, dentist, description) VALUES ('Maria', '31 971736555', NOW(), 'Dra.Silvia', 'Descrição...');
INSERT INTO tb_patient (name, phone, appointment_date, dentist, description) VALUES ('Maria', '31 971736555', NOW(), 'Dra.henrik', 'Descrição...');

-- -----------------------------------------------------
-- Endereços
-- -----------------------------------------------------
INSERT INTO tb_address(cep, logradouro,complemento, bairro, localidade, uf, patient_id) VALUES ('34001090','Rua Levy Firmino Alves', 'casa A', 'Parque Santo Antônio', 'Nova Lima', 'MG', 1);
INSERT INTO tb_address(cep, logradouro,complemento, bairro, localidade, uf, patient_id) VALUES ('34012856','Rua Seis', 'casa B', 'Santa Rita', 'Nova Lima', 'MG', 2);