ALTER TABLE zzz_restaurante
ADD COLUMN cozinha_id INTEGER;

ALTER TABLE zzz_restaurante
    ADD CONSTRAINT fk_restaurante_cozinha FOREIGN KEY (cozinha_id) REFERENCES zzz_cozinha (id);
