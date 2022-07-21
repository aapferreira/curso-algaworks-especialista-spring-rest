ALTER TABLE zzz_restaurante
ADD COLUMN endereco_cep VARCHAR(9);

ALTER TABLE zzz_restaurante
ADD COLUMN endereco_logradouro VARCHAR(100);
 
ALTER TABLE zzz_restaurante
ADD COLUMN endereco_numero VARCHAR(5);

ALTER TABLE zzz_restaurante
ADD COLUMN endereco_complemento VARCHAR(100);

ALTER TABLE zzz_restaurante
ADD COLUMN endereco_bairro VARCHAR(100);

ALTER TABLE zzz_restaurante
ADD COLUMN id_cidade Integer;

ALTER TABLE zzz_restaurante
ADD CONSTRAINT fk_restaurante_cidade FOREIGN KEY (id_cidade) REFERENCES zzz_cidade(id);

UPDATE zzz_restaurante
SET endereco_cep = '14100-000', 
endereco_logradouro = 'rua aaaaa', 
endereco_numero = '111', 
endereco_complemento = 'aaaaaaa', 
endereco_bairro = 'aaaaaaa', 
id_cidade = 1;
