package br.com.maikelsoft.pedidos.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbcondicao_pagamento")
public class CondicaoPagamento {

	@Id
	@Column(name="condicao_pagamento_id")
	private Long id;
	private String descricao;
}
