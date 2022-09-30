CREATE TABLE zzz_grupo (
	id SERIAL NOT NULL,
	nome varchar(100) NOT NULL,
	primary key(id)
);

CREATE TABLE zzz_grupo_permissao (
	id_grupo INT NOT NULL,
	id_permissao INT NOT NULL
);

ALTER TABLE "public"."zzz_grupo_permissao"
ADD CONSTRAINT fk_zzz_grupo_permissao_grupo
FOREIGN KEY (id_grupo)
REFERENCES "public"."zzz_grupo"(id);

ALTER TABLE "public"."zzz_grupo_permissao"
ADD CONSTRAINT fk_zzz_grupo_permissao_permissao
FOREIGN KEY (id_permissao)
REFERENCES "public"."zzz_permissao"(id);
