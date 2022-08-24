ALTER TABLE zzz_restaurante
ADD COLUMN data_cadastro TIMESTAMP;

ALTER TABLE zzz_restaurante
ADD COLUMN data_atualizacao TIMESTAMP;

UPDATE zzz_restaurante
SET data_cadastro = CURRENT_TIMESTAMP, 
data_atualizacao = CURRENT_TIMESTAMP;

ALTER TABLE zzz_restaurante
ALTER COLUMN data_cadastro SET NOT NULL;

ALTER TABLE zzz_restaurante
ALTER COLUMN data_atualizacao SET NOT NULL;
