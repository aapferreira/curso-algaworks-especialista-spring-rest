package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlCustomizado;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK) // 200
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlCustomizado listarXML() {
		return new CozinhasXmlCustomizado(cozinhaRepository.listar());
	}

//	@GetMapping("/{id}")
//	//public Cozinha buscar(@PathVariable("id") Long id) {	
//	public Cozinha buscar(@PathVariable Long id) {
//		return cozinhaRepository.buscar(id);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Cozinha cozinha = cozinhaRepository.buscar(id);

		if (cozinha != null) {
			// return ResponseEntity.status(HttpStatus.OK).body(cozinha);//200
			return ResponseEntity.ok(cozinha);// 200
		}

		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();//400
		return ResponseEntity.badRequest().build();// 400

	}

//	//Retornando que o recurso deve ser redirecionado para outra url
//	//cuidado com o POSTMAN pois ele faz o redirecionamento de maneira automática
//	//e pode parecer que não funcionou a mensagem
//	@GetMapping("/{id}")
//	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://novodominio.com.br/cozinhas");
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
//	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED) // 201
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> alterar(@RequestBody Cozinha cozinha, @PathVariable("id") Long id) {
		Cozinha cozinhaAtual = cozinhaRepository.buscar(id);

		if (cozinhaAtual != null) {
			// cozinhaAtual.setNome(cozinha.getNome());
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinhaAtual = cozinhaRepository.salvar(cozinhaAtual);
			return ResponseEntity.ok(cozinhaAtual);
		}

		return ResponseEntity.badRequest().build();// 400
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable("id") Long id) {

		try {
			cadastroCozinhaService.remover(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();// 404

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();// 409
		}
	}
	
	@GetMapping("/por-nome")
	public List<Cozinha> listarPorNome(@RequestParam("nome") String nomeCozinha) {
		return cozinhaRepository.consultarPorNome(nomeCozinha);
	}

}
