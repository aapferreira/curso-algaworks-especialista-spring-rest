DELETE FROM zzz_produto;

ALTER TABLE zzz_produto
ADD COLUMN id_restaurante int;

ALTER TABLE zzz_produto
ADD CONSTRAINT fk_produto_id_restaurante FOREIGN KEY (id_restaurante) REFERENCES zzz_restaurante(id);

insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);
insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina', 87.20, true, 2);
insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);
insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura', 79, true, 4);
insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T', 89, true, 4);

