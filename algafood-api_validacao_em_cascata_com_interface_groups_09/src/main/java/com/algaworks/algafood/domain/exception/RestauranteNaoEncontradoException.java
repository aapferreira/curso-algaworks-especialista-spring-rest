package com.algaworks.algafood.domain.exception;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//Vai pegar o response status da EntidadeNaoEncontradaException
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public RestauranteNaoEncontradoException(String msg) {
		super(msg);
	}

	public RestauranteNaoEncontradoException(Long idRestaurante) {
		super(String.format("Não encontrado o restaurante com o código %d", idRestaurante));
	}

}
