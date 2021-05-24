package br.com.maikelsoft.pedidos.api.domain.model.enumeration;

import lombok.Getter;

public enum TipoPedido {

	B1(0, "B1"),
	B2(1, "B2");

	private @Getter String descricao;
	private @Getter Integer id;

	private TipoPedido(Integer id, String desc) {
		this.descricao = desc;
		this.id = id;
	}
}
