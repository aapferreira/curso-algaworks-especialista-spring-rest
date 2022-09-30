package com.algaworks.algafood.infrastructure.repository;

import static com.algaworks.algafood.infrastructure.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infrastructure.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueriesCustomizadas;


@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueriesCustomizadas{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> findPorNomeIntervaloTaxaFrete(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
//		var jpql = "from Restaurante where upper(nome) like upper(:nome) "
//				+ "and taxaFrete between :taxaInicial and :taxaFinal";
//		
//		return manager.createQuery(jpql, Restaurante.class)
//				.setParameter("nome", "%" + nome + "%")
//				.setParameter("taxaInicial", taxaFreteInicial)
//				.setParameter("taxaFinal", taxaFreteFinal)
//				.getResultList();
		
		var jpql = new StringBuilder();
		jpql.append("from Restaurante where 0 = 0 ");
		
		HashMap<String, Object> parametros = new HashMap<>();
		
		if (StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		
		if (taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaFreteInicial);
		}
		
		if (taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFreteFinal);
		}
		
		TypedQuery<Restaurante> query = manager
				.createQuery(jpql.toString(), Restaurante.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();		
			
	}

//	@Override
//	//consulta simples Usando CriteriaAPI
//	public List<Restaurante> find(String nome, 
//			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
//		criteria.from(Restaurante.class);
//		
//		TypedQuery<Restaurante> query = manager.createQuery(criteria);
//		return query.getResultList();
//	}
	
	
// Método acima criado com CriteriaAPI
//	@Override
//	public List<Restaurante> findPorNomeIntervaloTaxaFrete(String nome, 
//			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
//		Root<Restaurante> root = criteria.from(Restaurante.class);
//
//		List<Predicate> predicates = new ArrayList<Predicate>();
//		
//		if (StringUtils.hasText(nome)) {
//			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
//		}
//		
//		if (taxaFreteInicial != null) {
//			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
//		}
//		
//		if (taxaFreteFinal != null) {
//			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
//		}
//		
//		criteria.where(predicates.toArray(new Predicate[0]));
//		
//		var query = manager.createQuery(criteria);
//		return query.getResultList();
//	}
	
	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(comFreteGratis()
				.and(comNomeSemelhante(nome)));
	}
	
}