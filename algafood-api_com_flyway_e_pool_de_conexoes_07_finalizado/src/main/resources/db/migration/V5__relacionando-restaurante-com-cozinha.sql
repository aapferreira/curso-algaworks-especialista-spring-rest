ALTER TABLE zzz_restaurante
ADD COLUMN cozinha_id INTEGER;

ALTER TABLE zzz_restaurante
    ADD CONSTRAINT fk_restaurante_cozinha FOREIGN KEY (cozinha_id) REFERENCES zzz_cozinha (id);

INSERT INTO zzz_restaurante (nome, taxa_frete, cozinha_id) VALUES ('aaaaaa',1.11, 1), ('bbbbbb',2.22, 2);

