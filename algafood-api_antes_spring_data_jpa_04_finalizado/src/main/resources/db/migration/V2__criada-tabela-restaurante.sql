CREATE TABLE zzz_restaurante (
	id SERIAL NOT NULL,
	nome varchar(30) NOT NULL,
	taxa_frete NUMERIC(5,2) NOT NULL,
	primary key(id)
);
