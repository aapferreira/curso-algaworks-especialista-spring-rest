package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.algaworks.algafood.infrastructure.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infrastructure.spec.RestauranteComNomeSemelhanteSpec;

import static com.algaworks.algafood.infrastructure.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infrastructure.spec.RestauranteSpecs.comNomeSemelhante;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	public RestauranteRepository restauranteRepository;
	@Autowired
	public CadastroRestauranteService cadastroRestauranteService;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK) // 200
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);

		if (restaurante.isPresent()) {
			// return ResponseEntity.status(HttpStatus.OK).body(restaurante);//200
			return ResponseEntity.ok(restaurante.get());
		}

		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();//400
		return ResponseEntity.notFound().build();// 400

	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {

		try {
			restaurante = cadastroRestauranteService.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@RequestBody Restaurante restaurante, @PathVariable Long id) {

		try {
			restaurante = cadastroRestauranteService.alterar(restaurante, id);
			return ResponseEntity.ok(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

		if (restauranteAtual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		merge(restaurante, restauranteAtual.get());

		return alterar(restauranteAtual.get(), restauranteId);
	}

	private void merge(final Restaurante source, final Restaurante target) {

		ReflectionUtils.doWithFields(Restaurante.class, new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

				field.setAccessible(true);
				if (field.get(source) != null) {
					field.set(target, field.get(source));
				}
			}
		}, ReflectionUtils.COPYABLE_FIELDS);
	}

	@GetMapping("/listar-por-taxa-frete")
	public List<Restaurante> listarPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/listar-por-nome-e-nome-cozinha")
	public List<Restaurante> listarPorNomeAndNomeCozinha(String nome, String nomeCozinha) {
		return restauranteRepository.findByNomeContainingAndCozinhaNome(nome, nomeCozinha);
	}

	@GetMapping("/count-por-cozinha")
	public int countRestaurantesPorCozinhas(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}

	@GetMapping("/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal) {
		return restauranteRepository.findPorNomeIntervaloTaxaFrete(nome, taxaFreteInicial, taxaFreteFinal);
	}

//	@GetMapping("/com-frete-gratis")
//	public List<Restaurante> restaurantesComFreteGratis(String nome) {
//		RestauranteComFreteGratisSpec comFreteGratis = new RestauranteComFreteGratisSpec();
//		RestauranteComNomeSemelhanteSpec comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
//
//		return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
//	}
	
	@GetMapping("/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {		
		return restauranteRepository.findComFreteGratis(nome);
	}	
	
	@GetMapping("/buscar-primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {		
		return restauranteRepository.buscarPrimeiro();
	}	
	
}
