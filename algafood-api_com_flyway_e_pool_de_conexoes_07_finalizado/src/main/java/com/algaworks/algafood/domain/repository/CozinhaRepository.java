package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.algaworks.algafood.domain.model.Cozinha;

public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>{

	//a consulta abaixo é por nome exato
	//estrutura: find + QualquerCoisa + By + Atributo
	List<Cozinha> findListaCozinhasByNome(String nome);
	
	//O método abaixo retornará um erro caso o nome da cozinha não seja único 
	//os prefixos podem ser: find, get, read, query ou stream
	Optional<Cozinha> findByNome(String nome);
	
	//prefixo: exists
	boolean existsByNome(String nome);
	
	//prefixo: count
	int countByNome(String nome);
	
	//lista de palavras chaves para os query methods
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	List<Cozinha> findListaCozinhasByNomeContaining(String nome);
	
	//utilizando flags: first
	Optional<Cozinha> findFirstCozinhasByNomeContaining(String nome);
	
	//utilizando flags: top
	List<Cozinha> findTop2CozinhasByNomeContaining(String nome);
	
//	//@Query("from Restaurante where upper(nome) like upper(concat('%',:nome,'%')) and cozinha.id = :id")
//	@Query("FROM Cozinha c WHERE upper(c.nome) LIKE upper(concat('%',:nome,'%')) ")
//	List<Cozinha> listarCozinhas(@Param("nome") String nomeCozinha);
	
	//método utilizando o arquivo src/main/resources/META-INF/orm.xml
	List<Cozinha> listarCozinhas(@Param("nome") String nomeCozinha);
	
	
}
