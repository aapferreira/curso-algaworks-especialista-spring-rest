CREATE TABLE zzz_produto (
	id SERIAL NOT NULL,
	nome varchar(100) NOT NULL,
	descricao varchar(100) NOT NULL,
    preco NUMERIC(5,2) NOT NULL,
    ativo boolean DEFAULT true,
	primary key(id)
);

INSERT INTO zzz_produto (nome, descricao, preco, ativo) VALUES ('aaaaaa', 'aaaaaa', 1.11, true), ('bbbbbb', 'bbbbb', 2.22, true);
