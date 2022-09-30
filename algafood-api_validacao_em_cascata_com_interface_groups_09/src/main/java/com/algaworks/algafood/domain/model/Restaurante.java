package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "zzz_restaurante")
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@NotNull
	@NotEmpty //nulo ou vazio
	@NotBlank (groups = Groups.CadastroRestaurante.class) //nulo ou vazio ou espaço em branco
	@Column(nullable = false)
	private String nome;

	//@DecimalMin("0")
	@PositiveOrZero(groups = Groups.CadastroRestaurante.class)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@JsonIgnore
	@CreationTimestamp //anotação do Hibernate e não da JPA
	//@Column(nullable = false, columnDefinition = "datetime(6)") //com milisegundos
	@Column(nullable = false, columnDefinition = "datetime") //sem milisegundos
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp //anotação do Hibernate e não da JPA
	//@Column(nullable = false, columnDefinition = "datetime(6)") //com milisegundos
	@Column(nullable = false, columnDefinition = "datetime") //sem milisegundos
	private LocalDateTime dataAtualizacao;

	@Valid
	@NotNull(groups = Groups.CadastroRestaurante.class)
	@JsonIgnoreProperties("hibernateLazyInitializer")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "zzz_restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "id_restaurante"), inverseJoinColumns = @JoinColumn(name = "id_forma_pagamento"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

}
