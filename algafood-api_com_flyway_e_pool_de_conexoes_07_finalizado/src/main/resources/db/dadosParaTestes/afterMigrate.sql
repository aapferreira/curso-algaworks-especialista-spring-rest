insert into zzz_cozinha (id, nome) values (1, 'Tailandesa') ON CONFLICT DO NOTHING;
insert into zzz_cozinha (id, nome) values (2, 'Indiana') ON CONFLICT DO NOTHING;
insert into zzz_cozinha (id, nome) values (3, 'Argentina') ON CONFLICT DO NOTHING;
insert into zzz_cozinha (id, nome) values (4, 'Brasileira') ON CONFLICT DO NOTHING;

insert into zzz_estado (id, nome) values (1, 'MG') ON CONFLICT DO NOTHING;
insert into zzz_estado (id, nome) values (2, 'SP') ON CONFLICT DO NOTHING;
insert into zzz_estado (id, nome) values (3, 'CE') ON CONFLICT DO NOTHING;

insert into zzz_cidade (id, nome, id_estado) values (1, 'Uberlândia', 1) ON CONFLICT DO NOTHING;
insert into zzz_cidade (id, nome, id_estado) values (2, 'Belo Horizonte', 1) ON CONFLICT DO NOTHING;
insert into zzz_cidade (id, nome, id_estado) values (3, 'São Paulo', 2) ON CONFLICT DO NOTHING;
insert into zzz_cidade (id, nome, id_estado) values (4, 'Campinas', 2) ON CONFLICT DO NOTHING;
insert into zzz_cidade (id, nome, id_estado) values (5, 'Fortaleza', 3) ON CONFLICT DO NOTHING;

insert into zzz_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, id_cidade, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro') ON CONFLICT DO NOTHING;
insert into zzz_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ON CONFLICT DO NOTHING;
insert into zzz_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ON CONFLICT DO NOTHING;
insert into zzz_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ON CONFLICT DO NOTHING;
insert into zzz_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ON CONFLICT DO NOTHING;
insert into zzz_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ON CONFLICT DO NOTHING;

insert into zzz_forma_pagamento (id, descricao) values (1, 'Cartão de crédito') ON CONFLICT DO NOTHING;
insert into zzz_forma_pagamento (id, descricao) values (2, 'Cartão de débito') ON CONFLICT DO NOTHING;
insert into zzz_forma_pagamento (id, descricao) values (3, 'Dinheiro') ON CONFLICT DO NOTHING;

insert into zzz_permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas') ON CONFLICT DO NOTHING;
insert into zzz_permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas') ON CONFLICT DO NOTHING;

insert into zzz_restaurante_forma_pagamento (id_restaurante, id_forma_pagamento) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3) ON CONFLICT DO NOTHING;

insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1) ON CONFLICT DO NOTHING;
insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1) ON CONFLICT DO NOTHING;

insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada', 87.20, true, 2) ON CONFLICT DO NOTHING;

insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3) ON CONFLICT DO NOTHING;
insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3) ON CONFLICT DO NOTHING;

insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura', 79, true, 4) ON CONFLICT DO NOTHING;
insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T', 89, true, 4) ON CONFLICT DO NOTHING;

insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5) ON CONFLICT DO NOTHING;

insert into zzz_produto (nome, descricao, preco, ativo, id_restaurante) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6) ON CONFLICT DO NOTHING;
