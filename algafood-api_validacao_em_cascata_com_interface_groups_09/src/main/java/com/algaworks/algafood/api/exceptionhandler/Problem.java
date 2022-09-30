package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
//@Setter
@Builder
@JsonInclude(Include.NON_NULL) //só vai incluir no json os atributos que não são nulos
public class Problem {
	
	//atributos da especificação
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	//atributos customizados
	private String userMessage;
	private LocalDateTime timestamp;
	private List<Field> fields;
	
	@Getter
	@Builder
	public static class Field {
		
		private String name;
		private String userMessage;
		
	}
	

}
