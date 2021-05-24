package br.com.maikelsoft.pedidos.api.vo;

import java.io.Serializable;

import br.com.maikelsoft.pedidos.api.domain.model.Referencia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReferenciaVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String telefones;
	private Long tipo;
	private String descTipo;

	public ReferenciaVO(Referencia ref) {
		this.id = ref.getId();
		this.nome = ref.getNome();
		this.telefones = ref.getTelefones();
		this.tipo = ref.getTipo().getId();
		this.descTipo = ref.getTipo().getDescricao();
	}
}
