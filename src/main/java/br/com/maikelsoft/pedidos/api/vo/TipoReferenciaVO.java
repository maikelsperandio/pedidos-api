package br.com.maikelsoft.pedidos.api.vo;

import java.io.Serializable;

import br.com.maikelsoft.pedidos.api.domain.model.TipoReferencia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoReferenciaVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;

	public TipoReferenciaVO(TipoReferencia tp) {
		this.id = tp.getId();
		this.descricao = tp.getDescricao();
	}
}
