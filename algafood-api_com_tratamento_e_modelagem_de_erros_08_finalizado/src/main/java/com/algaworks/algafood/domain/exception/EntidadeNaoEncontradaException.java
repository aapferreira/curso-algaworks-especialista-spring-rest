package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
//public class EntidadeNaoEncontradaException extends RuntimeException {
public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}

}
