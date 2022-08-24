package com.algaworks.algafood.domain.exception;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//Vai pegar o response status da EntidadeNaoEncontradaException
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public CidadeNaoEncontradaException(String msg) {
		super(msg);
	}

	public CidadeNaoEncontradaException(Long idCidade) {
		super(String.format("Não encontrada a cidade com o código %d", idCidade));
	}

}
