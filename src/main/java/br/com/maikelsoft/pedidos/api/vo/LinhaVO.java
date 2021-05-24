package br.com.maikelsoft.pedidos.api.vo;

import br.com.maikelsoft.pedidos.api.domain.model.Linha;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinhaVO {

	private Long id;
	private String descricao;

	public LinhaVO(Linha ln) {
		this.id = ln.getId();
		this.descricao = ln.getDescricao();
	}
}
