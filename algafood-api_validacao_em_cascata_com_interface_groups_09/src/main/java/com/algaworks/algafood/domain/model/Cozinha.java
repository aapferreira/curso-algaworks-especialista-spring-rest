package com.algaworks.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.Groups;
import com.algaworks.algafood.Groups.CadastroRestaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("cozinha") //caso a exibição seja em xml, troca o nome da tag parent (nesse caso: <Cozinha> para <cozinha>)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "zzz_cozinha")
public class Cozinha {

	@NotNull(groups = Groups.CadastroRestaurante.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@Transient
	@JsonIgnore//não mostra o atributo no json
	private String teste = "teste";

	@Transient
	@JsonProperty("nomeCozinha") //altera como o atributo será no json	
	private String nomeCoz = "teste";;
	
	@JsonIgnore
	@OneToMany
	private List<Restaurante> restaurantes = new ArrayList<>();	
	
}
