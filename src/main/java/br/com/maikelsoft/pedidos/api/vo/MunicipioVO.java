package br.com.maikelsoft.pedidos.api.vo;

import br.com.maikelsoft.pedidos.api.domain.model.Municipio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MunicipioVO {

	private Long id;
	private String descricao;
	private String uf;

	public MunicipioVO(Municipio mun) {
		this.id = mun.getId();
		this.descricao = mun.getDescricao();
		this.uf = mun.getUf().getSigla();
	}
}
