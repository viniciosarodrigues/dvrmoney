CREATE TABLE categoria(
	ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(50) NOT NULL	
)ENGINE=InnoDB CHARSET=utf8;

INSERT INTO categoria(NOME) VALUES ('Lazer');
INSERT INTO categoria(NOME) VALUES ('Alimentação');
INSERT INTO categoria(NOME) VALUES ('Supermercado');
INSERT INTO categoria(NOME) VALUES ('Farmácia');
INSERT INTO categoria(NOME) VALUES ('Empréstimo');
INSERT INTO categoria(NOME) VALUES ('Saúde e beleza');
INSERT INTO categoria(NOME) VALUES ('Educação');
INSERT INTO categoria(NOME) VALUES ('Outros');