package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.algaworks.algafood.enums.StatusPedido;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "zzz_pedido")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "subtotal", nullable = false)
	private BigDecimal subtotal;

	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxa_frete;

	@Column(name = "valor_total", nullable = false)
	private BigDecimal valor_total;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@Column(name = "data_criacao", nullable = false)
	@CreationTimestamp
	private LocalDateTime data_criacao;

	@Column(name = "data_confirmacao")
	private LocalDateTime dataConfirmacao;

	@Column(name = "data_cancelamento")
	private LocalDateTime dataCancelamento;

	@Column(name = "data_entrega")
	private LocalDateTime dataEntrega;

	@Embedded
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuarioCliente;

	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	@OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

}
