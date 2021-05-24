package br.com.maikelsoft.pedidos.api.vo;

import java.io.Serializable;

import br.com.maikelsoft.pedidos.api.domain.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendedorVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String telefone;
	private String email;

	public VendedorVO(Vendedor vend) {
		this.id = vend.getId();
		this.nome = vend.getNome();
		this.telefone = vend.getTelefone();
		this.email = vend.getEmail();
	}
}
