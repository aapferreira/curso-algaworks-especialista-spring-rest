CREATE TABLE zzz_restaurante_forma_pagamento (
	id_restaurante INT NOT NULL,
	id_forma_pagamento INT NOT NULL
);

ALTER TABLE "public"."zzz_restaurante_forma_pagamento"
ADD CONSTRAINT fk_restaurante_forma_pagamento_restaurante
FOREIGN KEY (id_restaurante)
REFERENCES "public"."zzz_restaurante"(id);

ALTER TABLE "public"."zzz_restaurante_forma_pagamento"
ADD CONSTRAINT fk_restaurante_forma_pagamento_forma_pagamento
FOREIGN KEY (id_forma_pagamento)
REFERENCES "public"."zzz_forma_pagamento"(id);
