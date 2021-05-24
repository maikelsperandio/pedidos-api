package br.com.maikelsoft.pedidos.api.vo;

import br.com.maikelsoft.pedidos.api.domain.model.UnidadeMedida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeMedidaVO {

	private Long id;
	private String sigla;

	public UnidadeMedidaVO(UnidadeMedida und) {
		this.id = und.getId();
		this.sigla = und.getSigla();
	}
}
