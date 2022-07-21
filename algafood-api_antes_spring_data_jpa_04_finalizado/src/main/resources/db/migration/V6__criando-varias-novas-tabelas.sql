CREATE TABLE zzz_estado (
	id SERIAL NOT NULL,
	nome CHAR(2) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO zzz_estado (nome) VALUES ('SP'), ('MG');

CREATE TABLE zzz_cidade (
	id SERIAL NOT NULL,
	nome CHAR(30) NOT NULL,
	id_estado Integer NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE zzz_cidade
ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (id_estado) REFERENCES zzz_estado(id);

INSERT INTO zzz_cidade (nome, id_estado) VALUES ('Ribeirão Preto', (SELECT id FROM zzz_estado WHERE nome ILIKE 'SP'));

CREATE TABLE zzz_permissao (
	id SERIAL NOT NULL,
	nome VARCHAR(30) NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO zzz_permissao (nome, descricao) VALUES ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas'),
('EDITAR_COZINHAS', 'Permite editar cozinhas');  

CREATE TABLE zzz_forma_pagamento (
	id SERIAL NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO zzz_forma_pagamento (descricao) VALUES ('Cartão'), ('Pix');