package com.algaworks.algafood.domain.exception;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//Vai pegar o response status da EntidadeNaoEncontradaException
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public EstadoNaoEncontradoException(String msg) {
		super(msg);
	}

	public EstadoNaoEncontradoException(Long idEstado) {
		super(String.format("Não encontrado o estado com o código %d", idEstado));
	}

}
