-- -------------------------------------------
-- Usuários e perfis
-- -------------------------------------------
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Alex', 'Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Maria', 'Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('jean', 'fernandine', 'jfernandine@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_DENTIST');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);

-- -------------------------------------------
-- Pacientes
-- -------------------------------------------
INSERT INTO tb_patient (name, phone, appointment_date, dentist, description) VALUES ('Leonardo Santos', '31 971736555', NOW(), 'Dr.Antônio', 'Limpeza Dental (Profilaxia)');
INSERT INTO tb_patient (name, phone, appointment_date, dentist, description) VALUES ('Gabriel Costa', '31 971738952', NOW(), 'Dra.Silvia', 'Extração de Dentes');
INSERT INTO tb_patient (name, phone, appointment_date, dentist, description) VALUES ('Rafael Almeida', '31 978736550', NOW(), 'Dr.Marcone', 'Implantes Dentários');
INSERT INTO tb_patient (name, phone, appointment_date, dentist, description) VALUES ('Camila Rodrigues', '31 997365554', NOW(), 'Dr.Henrik', 'Clareamento Dental');

-- -----------------------------------------------------
-- Endereços
-- -----------------------------------------------------
INSERT INTO tb_address(cep, logradouro,complemento, bairro, localidade, uf) VALUES ('34001090','cinco', 'casa A', 'Parque Santo Antônio', 'Nova Lima', 'MG');
INSERT INTO tb_address(cep, logradouro,complemento, bairro, localidade, uf) VALUES ('34012856','Rua Seis', 'casa B', 'Santa Rita', 'Nova Lima', 'MG');
INSERT INTO tb_address(cep, logradouro,complemento, bairro, localidade, uf) VALUES ('34012856','Rua Seis', 'casa B', 'Santa Rita', 'Nova Lima', 'MG');
INSERT INTO tb_address(cep, logradouro,complemento, bairro, localidade, uf) VALUES ('34012856','Rua Seis', 'casa B', 'Santa Rita', 'Nova Lima', 'MG');

INSERT INTO tb_patient_address (patient_id, address_id ) VALUES (1, 1);
INSERT INTO tb_patient_address (patient_id, address_id ) VALUES (2, 2);
INSERT INTO tb_patient_address (patient_id, address_id ) VALUES (3, 3);
INSERT INTO tb_patient_address (patient_id, address_id ) VALUES (4, 4);
