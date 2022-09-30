create table zzz_pedido (
  id SERIAL NOT NULL,
  subtotal numeric(10,2) not null,
  taxa_frete numeric(10,2) not null,
  valor_total numeric(10,2) not null,

  id_restaurante int not null,
  id_usuario_cliente int not null,
  id_forma_pagamento int not null,
  
  id_cidade int not null,
  endereco_cep varchar(9) not null,
  endereco_logradouro varchar(100) not null,
  endereco_numero varchar(20) not null,
  endereco_complemento varchar(60),
  endereco_bairro varchar(60) not null,
  
  status varchar(10) not null,
  data_criacao timestamp not null,
  data_confirmacao timestamp,
  data_cancelamento timestamp,
  data_entrega timestamp,

  primary key(id),

  constraint fk_pedido_restaurante foreign key(id_restaurante) references zzz_restaurante(id),
  constraint fk_pedido_usuario_cliente foreign key(id_usuario_cliente) references zzz_usuario(id),
  constraint fk_pedido_forma_pagamento foreign key(id_forma_pagamento) references zzz_forma_pagamento(id)
);

create table zzz_item_pedido (
  id SERIAL NOT NULL,
  quantidade int not null,
  preco_unitario numeric(10,2) not null,
  preco_total numeric(10,2) not null,
  observacao varchar(255),
  id_pedido int not null,
  id_produto int not null,
  
  primary key(id),
  constraint uk_item_pedido_produto UNIQUE (id_pedido, id_produto),

  constraint fk_item_pedido_pedido foreign key(id_pedido) references zzz_pedido(id),
  constraint fk_item_pedido_produto foreign key(id_produto) references zzz_produto(id)
);
