package com.algaworks.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	public RestauranteRepository restauranteRepository;
	@Autowired
	public CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		
//		Long cozinhaId = restaurante.getCozinha().getId();
//		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
//		if (cozinha.isEmpty()) {
//			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
//		}
//		restaurante.setCozinha(cozinha.get());
//		return restauranteRepository.salvar(restaurante);
		
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	} 

	public Restaurante alterar(Restaurante restaurante, Long idRestaurante) {
		
		Restaurante restauranteAtual =  restauranteRepository.findById(idRestaurante)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de restaurante com código %d", idRestaurante)));
		
		//BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "dataCadastro", "produtos");
		
		return this.salvar(restauranteAtual);
		
	} 

}
