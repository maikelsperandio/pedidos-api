package br.com.maikelsoft.pedidos.api.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date data;
	private Long totalItens;
	private Double total;
	private boolean emissaoDireta;
	private Integer tipoPedido;

	private ClienteVO cliente;
	private VendedorVO vendedor;
	private CondicaoPagamentoVO condicaoPagamento;

	private List<ItemPedidoVO> itens;

}
