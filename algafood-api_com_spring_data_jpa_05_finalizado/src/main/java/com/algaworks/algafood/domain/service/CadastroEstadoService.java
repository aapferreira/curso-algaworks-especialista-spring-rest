package com.algaworks.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	public EstadoRepository estadoRepository;
	
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }
    
	public Estado alterar(Estado estado, Long id) {
		Estado estadoAtual = estadoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não encontrado o estado com o código %d", id)));
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		
		return salvar(estadoAtual);
	}

	public void remover(Long id) {
		try {	
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um Estado com o código %d.", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido", id));
		}
	}

}
