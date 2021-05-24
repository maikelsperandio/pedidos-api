package br.com.maikelsoft.pedidos.api.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.maikelsoft.pedidos.api.domain.model.enumeration.TipoPedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbpedido")
public class Pedido {

	@Id
	@Column(name="pedido_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Date data;

	@Column(name="qtde_itens")
	private Long totalItens;

	@Column(name="total")
	private Double total;

	@Column(name="emissao_direta")
	private boolean emissaoDireta;

	@Enumerated(EnumType.ORDINAL)
	private TipoPedido tipo;

	@ManyToOne
	@JoinColumn(name="vendedor_id")
	private Vendedor vendedor;

	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name="condicao_pagamento_id")
	private CondicaoPagamento condicaoPagamento;

	@OneToMany(mappedBy="pedido", orphanRemoval=true, fetch=FetchType.EAGER)
	@Cascade(value={CascadeType.ALL})
	private List<ItemPedido> itens;
}
