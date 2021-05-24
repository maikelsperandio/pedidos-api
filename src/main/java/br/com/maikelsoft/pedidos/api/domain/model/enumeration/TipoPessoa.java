package br.com.maikelsoft.pedidos.api.domain.model.enumeration;

import lombok.Getter;

public enum TipoPessoa {

	FISICA(0, "FÍSICA"),
	JURIDICA(1, "JURÍDICA");

	private @Getter String descricao;
	private @Getter Integer id;

	private TipoPessoa(Integer id, String desc) {
		this.descricao = desc;
		this.id = id;
	}
}
