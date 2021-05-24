package br.com.maikelsoft.pedidos.api.vo;

import java.io.Serializable;

import br.com.maikelsoft.pedidos.api.domain.model.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;
	private Long codigo;
	private Double peso;
	private Double qtdeKit;
	private Double qtdeCaixa;
	private Double precoCusto;
	private Double precoAtacado;
	private Double precoVarejo;
	private UnidadeMedidaVO unidade;
	private LinhaVO linha;

	public ProdutoVO(Produto prod){
		this.codigo = prod.getCodigo();
		this.descricao = prod.getDescricao();
		this.id = prod.getId();
		this.peso = prod.getPeso();
		this.precoCusto = prod.getPrecoCusto();
		this.precoAtacado = prod.getPrecoAtacado();
		this.precoVarejo = prod.getPrecoVarejo();
		this.qtdeCaixa = prod.getQtdeCaixa();
		this.qtdeKit = prod.getQtdeKit();
		this.unidade = new UnidadeMedidaVO(prod.getUnidade());
		this.linha = new LinhaVO(prod.getLinha());
	}

	public String getPesoFormatado() {
		if(peso == null)
			return null;
		if(unidade == null)
			return peso.intValue() + "";
		return peso.intValue() + unidade.getSigla();
	}
}
