package com.algaworks.algafood.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	public CidadeRepository cidadeRepository;
	@Autowired
	public CadastroCidadeService cadastroCidadeService;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
//		Optional<Cidade> cidade = cidadeRepository.findById(id);
//		
//		if (cidade.isEmpty()) {
//			ResponseEntity.notFound().build();
//		}
//		
//		return ResponseEntity.ok(cidade.get());
//	}

	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable Long id) {
		return cadastroCidadeService.buscarOuFalhar(id);
	}

//	@PostMapping
//	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
//		
//		try {
//			cidade = cadastroCidadeService.salvar(cidade);
//			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
//			
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); 
//		}
//
//	}

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Cidade adicionar(@RequestBody Cidade cidade) {
//		return cadastroCidadeService.salvar(cidade);
//	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		try {
			return cadastroCidadeService.salvar(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Cidade cidade) {
//		
//		try {
//			cidade = cadastroCidadeService.alterar(id, cidade);
//			return ResponseEntity.ok(cidade);
//			
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); 
//		}
//
//	}

//	public Cidade alterar(Long id, Cidade cidade) {
//	Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(id);
//	BeanUtils.copyProperties(cidade, cidadeAtual, "id");
//	return cadastroCidadeService.salvar(cidade);
//}

	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		try {
			Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			return cadastroCidadeService.salvar(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCidadeService.excluir(id);
	}

	//Ao invés de tratar as execeções controlador por controlador
	//foi criada uma classe que para capturar as exceções de todos os
	//controladores
	
////	// capturando exceções lançadas pelo controlador e 
////	// formatando o retorno
////	// o método abaixo captura tanto EntidadeNaoEncontradaException
////	// quanto NegocioException. Isso porque NegocioException recebe a causa
////	// da exceção e o @ExceptionHandler é capaz de capturar a causa também 
////	@ExceptionHandler(EntidadeNaoEncontradaException.class)
////	public ResponseEntity<?> tratarEntidadeNaoEncontradaExcpetion(EntidadeNaoEncontradaException e) {
////		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
////	}
////	
////	@ExceptionHandler(NegocioException.class)
////	public ResponseEntity<?> tratarNegocioException(NegocioException e) {
////		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
////	}
//	
//	// capturando exceções lançadas pelo controlador e 
//	// formatando o retorno
//	// o método abaixo captura tanto EntidadeNaoEncontradaException
//	// quanto NegocioException. Isso porque NegocioException recebe a causa
//	// da exceção e o @ExceptionHandler é capaz de capturar a causa também 
//	@ExceptionHandler(EntidadeNaoEncontradaException.class)
//	public ResponseEntity<?> tratarEntidadeNaoEncontradaExcpetion(EntidadeNaoEncontradaException e) {
//		
////		Problema problema = new Problema();
////		problema.setDataHora(LocalDateTime.now());
////		problema.setMensagem(e.getMessage());
//		
//		//usando @Builder aos invés do @Setter (exemplo acima)
//		Problema problema = Problema.builder()
//				.dataHora(LocalDateTime.now())
//				.mensagem(e.getMessage())
//				.build();
//		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
//	}
//	
//	@ExceptionHandler(NegocioException.class)
//	public ResponseEntity<?> tratarNegocioException(NegocioException e) {
//		
////		Problema problema = new Problema();
////		problema.setDataHora(LocalDateTime.now());
////		problema.setMensagem(e.getMessage());
//		
//		//usando @Builder aos invés do @Setter (exemplo acima)
//		Problema problema = Problema.builder()
//				.dataHora(LocalDateTime.now())
//				.mensagem(e.getMessage())
//				.build();
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
//	}

}