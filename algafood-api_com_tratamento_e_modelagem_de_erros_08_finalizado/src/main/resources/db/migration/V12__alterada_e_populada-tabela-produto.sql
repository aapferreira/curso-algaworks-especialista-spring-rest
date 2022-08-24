DELETE FROM zzz_produto;

ALTER TABLE zzz_produto
ADD COLUMN id_restaurante int;

ALTER TABLE zzz_produto
ADD CONSTRAINT fk_produto_id_restaurante FOREIGN KEY (id_restaurante) REFERENCES zzz_restaurante(id);
