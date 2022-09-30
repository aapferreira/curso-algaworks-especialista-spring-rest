package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.algaworks.algafood.domain.model.Restaurante;
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
		return cozinhaRepository.findAll();
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlCustomizado listarXML() {
		return new CozinhasXmlCustomizado(cozinhaRepository.findAll());
	}

//	@GetMapping("/{id}")
//	//public Cozinha buscar(@PathVariable("id") Long id) {	
//	public Cozinha buscar(@PathVariable Long id) {
//		return cozinhaRepository.buscar(id);
//	}

//	@GetMapping("/{id}")
//	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
//		Optional<Cozinha> cozinhaOpt = cozinhaRepository.findById(id);
//
//		if (cozinhaOpt.isPresent()) {
//			// return ResponseEntity.status(HttpStatus.OK).body(cozinha);//200
//			return ResponseEntity.ok(cozinhaOpt.get());// 200
//		}
//
//		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();//400
//		return ResponseEntity.badRequest().build();// 400
//
//	}

//	@GetMapping("/{id}")
//	public Cozinha buscar(@PathVariable Long id) {
//		return cozinhaRepository.findById(id)
//				.orElseThrow(() -> new EntidadeNaoEncontradaException(null));
//	}

//	@GetMapping("/{id}")
//	public Cozinha buscar(@PathVariable Long id) {
//		return cozinhaRepository.findById(id)
//				.orElseThrow(() -> new EntidadeNaoEncontradaException(null));
//	}

	@GetMapping("/{id}")
	public Cozinha buscar(@PathVariable Long id) {
		return cadastroCozinhaService.buscarOuFalhar(id);
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
	public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<Cozinha> alterar(@RequestBody Cozinha cozinha, @PathVariable("id") Long id) {
//		Optional<Cozinha> cozinhaAtualOpt = cozinhaRepository.findById(id);
//
//		if (cozinhaAtualOpt.isPresent()) {
//			// cozinhaAtual.setNome(cozinha.getNome());
//			BeanUtils.copyProperties(cozinha, cozinhaAtualOpt.get(), "id");
//			Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtualOpt.get());
//			return ResponseEntity.ok(cozinhaSalva);
//		}
//
//		return ResponseEntity.badRequest().build();// 400
//	}

	@PutMapping("/{id}")
	public Cozinha alterar(@RequestBody Cozinha cozinha, @PathVariable("id") Long id) {
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(id);
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return cadastroCozinhaService.salvar(cozinhaAtual);
	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<Object> excluir(@PathVariable("id") Long id) {
//
//		try {
//			cadastroCozinhaService.remover(id);
//			return ResponseEntity.noContent().build();
//
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();// 404
//
//		} catch (EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();// 409
//		}
//	}

	// substituindo o código acima para
	// Lançar exceções customizadas anotadas com @ResponseStatus
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		cadastroCozinhaService.remover(id);
	}

	@GetMapping("/listar-por-nome")
	public List<Cozinha> listarPorNome(@RequestParam("nome") String nomeCozinha) {
		return cozinhaRepository.findListaCozinhasByNome(nomeCozinha);
	}

	@GetMapping("/buscar-por-nome")
	public Optional<Cozinha> buscaPorNome(@RequestParam("nome") String nomeCozinha) {
		return cozinhaRepository.findByNome(nomeCozinha);
	}

	@GetMapping("/listar-por-nome-containing")
	public List<Cozinha> listarPorNomeContaining(@RequestParam("nome") String nomeCozinha) {
		return cozinhaRepository.findListaCozinhasByNomeContaining(nomeCozinha);
	}

	@GetMapping("/buscar-primeira-por-nome")
	public Optional<Cozinha> buscaPrimeiraPorNome(@RequestParam("nome") String nomeCozinha) {
		return cozinhaRepository.findFirstCozinhasByNomeContaining(nomeCozinha);
	}

	@GetMapping("/buscar-primeiros2-por-nome")
	public List<Cozinha> buscaPrimeiros2PorNome(@RequestParam("nome") String nomeCozinha) {
		return cozinhaRepository.findTop2CozinhasByNomeContaining(nomeCozinha);
	}

	@GetMapping("/existe-por-nome")
	public boolean existsPorNome(@RequestParam("nome") String nomeCozinha) {
		return cozinhaRepository.existsByNome(nomeCozinha);
	}

	@GetMapping("/qtd-cozinhas")
	public int qtdCozinhas(@RequestParam("nome") String nomeCozinha) {
		return cozinhaRepository.countByNome(nomeCozinha);
	}

	@GetMapping("/listar-com-query")
	public List<Cozinha> listarCozinhas(@RequestParam("nome") String nomeCozinha) {
		return cozinhaRepository.listarCozinhas(nomeCozinha);
	}

	@GetMapping("/buscar-primeiro")
	public Optional<Cozinha> cozinhaPrimeiro() {
		return cozinhaRepository.buscarPrimeiro();
	}

}
