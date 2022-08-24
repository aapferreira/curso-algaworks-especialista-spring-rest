package com.algaworks.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "zzz_grupo")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "zzz_restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "id_restaurante")
	, inverseJoinColumns = @JoinColumn(name = "id_forma_pagamento"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	
	@ManyToMany
	@JoinTable(name = "zzz_grupo_permissao",
		joinColumns = @JoinColumn(name = "id_group"),
		inverseJoinColumns = @JoinColumn(name = "id_permissao"))
	private List<Permissao> permissoes = new ArrayList<>();

}
