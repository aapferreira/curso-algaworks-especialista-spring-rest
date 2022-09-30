package com.algaworks.algafood.domain.exception;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//Vai pegar o response status da EntidadeNaoEncontradaException
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public CozinhaNaoEncontradaException(String msg) {
		super(msg);
	}

	public CozinhaNaoEncontradaException(Long idCozinha) {
		super(String.format("Não encontrado a cozinha com o código %d", idCozinha));
	}

}
