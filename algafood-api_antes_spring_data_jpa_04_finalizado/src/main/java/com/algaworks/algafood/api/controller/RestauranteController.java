package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
import com.fasterxml.jackson.databind.ObjectMapper;

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
		return restauranteRepository.listar();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteRepository.buscar(id);

		if (restaurante != null) {
			// return ResponseEntity.status(HttpStatus.OK).body(restaurante);//200
			return ResponseEntity.ok(restaurante);
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
		Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

		if (restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}

		merge(restaurante, restauranteAtual);

		return alterar(restauranteAtual, restauranteId);
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

}
