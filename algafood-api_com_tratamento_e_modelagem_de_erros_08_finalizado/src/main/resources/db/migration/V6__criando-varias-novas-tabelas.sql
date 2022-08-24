CREATE TABLE zzz_estado (
	id SERIAL NOT NULL,
	nome CHAR(2) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE zzz_cidade (
	id SERIAL NOT NULL,
	nome CHAR(30) NOT NULL,
	id_estado Integer NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE zzz_cidade
ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (id_estado) REFERENCES zzz_estado(id);

CREATE TABLE zzz_permissao (
	id SERIAL NOT NULL,
	nome VARCHAR(30) NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE zzz_forma_pagamento (
	id SERIAL NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	PRIMARY KEY (id)
);

