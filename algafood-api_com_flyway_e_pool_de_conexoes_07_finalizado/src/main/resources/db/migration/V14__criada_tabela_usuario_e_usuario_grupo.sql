CREATE TABLE zzz_usuario (
	id SERIAL NOT NULL,
	nome varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	senha varchar(100) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
	primary key(id)
);

CREATE TABLE zzz_usuario_grupo (
	id_usuario INT NOT NULL,
	id_grupo INT NOT NULL
);

ALTER TABLE "public"."zzz_usuario_grupo"
ADD CONSTRAINT fk_zzz_usuario_grupo_usuario
FOREIGN KEY (id_usuario)
REFERENCES "public"."zzz_usuario"(id);

ALTER TABLE "public"."zzz_usuario_grupo"
ADD CONSTRAINT fk_zzz_usuario_grupo_grupo
FOREIGN KEY (id_grupo)
REFERENCES "public"."zzz_grupo"(id);

insert into zzz_usuario (nome, email, senha, data_cadastro) values ('aaaaaa', 'aaa@aaaa.aaaa', 'aaaaaa', CURRENT_TIMESTAMP), ('bbbbb', 'bbb@bbbb.bbb', 'bbbbbb', CURRENT_TIMESTAMP);

insert into zzz_usuario_grupo (id_usuario, id_grupo) values (1, 1), (1,2), (2,1), (2,2);
