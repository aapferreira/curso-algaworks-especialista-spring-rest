package com.algaworks.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	public CidadeRepository cidadeRepository;
	@Autowired
	public EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		
		Long idEstado = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(idEstado)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de Estado com código %d", idEstado)));
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
	
	public Cidade alterar(Long id, Cidade cidade) {
		
		Cidade cidadeAtual = cidadeRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de Cidade com código %d", id)));
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		return this.salvar(cidade);
	}
	
	public void excluir(Long id) {
		
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de Cidade com código %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de Cidade com código %d", id));
		}

	}

}
