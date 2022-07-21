package com.algaworks.algafood.api.model;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "cozinhas") //ou @JsonRootElement("cozinhas")
@Data
public class CozinhasXmlCustomizado {
	
	@JacksonXmlProperty(localName = "cozinha") //ou @JsonProperty("cozinhas")
	@JacksonXmlElementWrapper(useWrapping = false)
	@NonNull //essa anotação é para que o @Date gere o construtor (@Data só gera construtor para atributos obrigatorios)
	public List<Cozinha> cozinhas;
	
//customizando de:
/**
<CozinhasXmlCustomizado>
    <cozinhas>
        <cozinhas>
            <id>1</id>
            <nome>Italiana</nome>
        </cozinhas>
        <cozinhas>
            <id>2</id>
            <nome>Japonesa</nome>
        </cozinhas>
    </cozinhas>
</CozinhasXmlCustomizado>
*/
//para:
/**
<cozinhas>
    <cozinha>
        <id>1</id>
        <nome>Italiana</nome>
        <nomeCozinha>teste</nomeCozinha>
    </cozinha>
    <cozinha>
        <id>2</id>
        <nome>Japonesa</nome>
        <nomeCozinha>teste</nomeCozinha>
    </cozinha>
</cozinhas>
*/
}
