package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	public CidadeRepository cidadeRepository;
	@Autowired
	public CadastroEstadoService cadastroEstadoService;
	
	private static final String MSG_CIDADE_EM_USO = "Cozinha de código %d não pode ser removida";
	
	public Cidade salvar(Cidade cidade) {
		Long idEstado = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscarOuFalhar(idEstado);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long id) {
		
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, id));
		}

	}
	
	public Cidade buscarOuFalhar(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow(() -> new CidadeNaoEncontradaException(id));
	}

}
