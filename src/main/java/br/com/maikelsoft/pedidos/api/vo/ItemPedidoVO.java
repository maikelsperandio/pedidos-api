package br.com.maikelsoft.pedidos.api.vo;

import java.io.Serializable;

import br.com.maikelsoft.pedidos.api.domain.model.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer quantidade;
	private Double valor;
	private Double total;

	private ProdutoVO produto;

	public ItemPedidoVO(ItemPedido item) {
		this.id = item.getId();
		this.quantidade = item.getQuantidade();
		this.valor = item.getValor();
		this.total = item.getTotal();
		this.produto = new ProdutoVO(item.getProduto());
	}

	public String getDescricaoProduto() {
		if(produto == null)
			return null;
		StringBuilder build = new StringBuilder();
		build.append(produto.getDescricao());
		build.append(" ");
		build.append(produto.getPeso().intValue());
		build.append(" ");
		build.append(produto.getUnidade().getSigla());
		return build.toString();
	}

	public Long getCodigo() {
		if(produto == null)
			return null;
		return produto.getCodigo();
	}
}
