-- -----------------------------------------------------
-- Schema osiris
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS osiris ;
USE osiris ;



-- -----------------------------------------------------
-- Table osiris.ecommerce
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS osiris.ecommerce (
  id_ecommerce INT NOT NULL AUTO_INCREMENT,
  cnpj VARCHAR(255) NULL DEFAULT NULL,
  nome VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id_ecommerce)
);

-- -----------------------------------------------------
-- Table osiris.acesso
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS osiris.acesso (
  id_acessos INT NOT NULL AUTO_INCREMENT,
  fim_acesso DATETIME(6) NOT NULL,
  id_consumidor_ecommerce INT NOT NULL,
  inicio_acesso DATETIME(6) NOT NULL,
  ecommerce_id_ecommerce INT NOT NULL,
  PRIMARY KEY (id_acessos),
  FOREIGN KEY (ecommerce_id_ecommerce) REFERENCES ecommerce(id_ecommerce)
);


-- -----------------------------------------------------
-- Table osiris.cupom
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS osiris.cupom (
  id_cupom INT NOT NULL AUTO_INCREMENT,
  cupom_ecommerce BIT(1) NOT NULL,
  data_emitido DATETIME(6) NOT NULL,
  data_validado DATETIME(6) NOT NULL,
  id_consumidor_ecommerce INT NOT NULL,
  nome_cupom VARCHAR(255) NULL DEFAULT NULL,
  usado BIT(1) NOT NULL,
  valor DOUBLE NOT NULL,
  ecommerce_id_ecommerce INT NOT NULL,
  PRIMARY KEY (id_cupom),
  FOREIGN KEY (ecommerce_id_ecommerce) REFERENCES ecommerce(id_ecommerce)
);


-- -----------------------------------------------------
-- Table osiris.dominio_status
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS osiris.dominio_status (
  id_dominio_status INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id_dominio_status)
);

-- -----------------------------------------------------
-- Table osiris.evento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS osiris.evento (
  id_evento INT NOT NULL AUTO_INCREMENT,
  data_compra DATETIME(6) NOT NULL,
  id_consumidor_ecommerce INT NOT NULL,
  nome_categoria VARCHAR(255) NULL DEFAULT NULL,
  nome_produto VARCHAR(255) NULL DEFAULT NULL,
  preco DOUBLE NOT NULL,
  cupom_id_cupom INT NULL DEFAULT NULL,
  dominio_status_id_dominio_status INT NOT NULL,
  ecommerce_id_ecommerce INT NOT NULL,
  data_inclusao DATETIME(6) NOT NULL,
  PRIMARY KEY (id_evento),
  FOREIGN KEY (dominio_status_id_dominio_status) REFERENCES osiris.dominio_status (id_dominio_status),
  FOREIGN KEY (cupom_id_cupom) REFERENCES osiris.cupom (id_cupom),
  FOREIGN KEY (ecommerce_id_ecommerce) REFERENCES osiris.ecommerce (id_ecommerce)
);


-- -----------------------------------------------------
-- Table osiris.meta
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS osiris.meta (
  id_meta INT NOT NULL AUTO_INCREMENT,
  data_fim DATETIME(6) NOT NULL,
  data_inicio DATETIME(6) NOT NULL,
  valor DOUBLE NOT NULL,
  tipo INT NOT NULL,
  ecommerce_id_ecommerce INT NOT NULL,
  PRIMARY KEY (id_meta),
  FOREIGN KEY (ecommerce_id_ecommerce) REFERENCES osiris.ecommerce (id_ecommerce)
);


-- -----------------------------------------------------
-- Table osiris.perfil
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS osiris.perfil (
  id_perfil INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id_perfil)
);


-- -----------------------------------------------------
-- Table osiris.usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS osiris.usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  login_usuario VARCHAR(255) NULL DEFAULT NULL,
  nome_completo VARCHAR(255) NULL DEFAULT NULL,
  senha VARCHAR(255) NULL DEFAULT NULL,
  ecommerce_id_ecommerce INT NOT NULL,
  PRIMARY KEY (id_usuario),
  FOREIGN KEY (ecommerce_id_ecommerce) REFERENCES ecommerce (id_ecommerce)
);


-- -----------------------------------------------------
-- Table osiris.usuario_perfis
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS osiris.usuario_perfis (
  usuario_id_usuario INT NOT NULL,
  perfis_id_perfil INT NOT NULL,
  FOREIGN KEY (usuario_id_usuario) REFERENCES osiris.usuario (id_usuario),
  FOREIGN KEY (perfis_id_perfil) REFERENCES osiris.perfil (id_perfil)
);


-- -----------------------------------------------------
-- Dados iniciais
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Adicionando campos de Evento
-- -----------------------------------------------------

ALTER TABLE osiris.evento
ADD sexo CHAR(1);

ALTER TABLE osiris.evento
ADD faixa_etaria VARCHAR(14);

ALTER TABLE osiris.acesso
ADD localidade VARCHAR(14);

INSERT INTO osiris.ecommerce
VALUES (null, '85.002.488/0001-49', 'Ecommerce do Kaique')
