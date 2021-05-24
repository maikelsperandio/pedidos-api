package br.com.maikelsoft.pedidos.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name="tbproduto")
public class Produto {

	@Id
	@Column(name="produto_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String descricao;
	private Long codigo;
	private Double peso;

	@Column(name="qtde_kit")
	private Double qtdeKit;

	@Column(name="qtde_caixa")
	private Double qtdeCaixa;

	@Column(name="preco_custo")
	private Double precoCusto;

	@Column(name="preco_atacado")
	private Double precoAtacado;

	@Column(name="preco_varejo")
	private Double precoVarejo;

	@ManyToOne
	@JoinColumn(name = "unidade_medida_id")
	private UnidadeMedida unidade;

	@ManyToOne
	@JoinColumn(name = "linha_id")
	private Linha linha;
}
