package br.com.maikelsoft.pedidos.api.vo;

import java.io.Serializable;

import br.com.maikelsoft.pedidos.api.domain.model.CondicaoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CondicaoPagamentoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;

	public CondicaoPagamentoVO(CondicaoPagamento cond) {
		this.id = cond.getId();
		this.descricao = cond.getDescricao();
	}
}
