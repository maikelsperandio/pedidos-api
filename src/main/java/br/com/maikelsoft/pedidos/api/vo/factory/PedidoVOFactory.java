package br.com.maikelsoft.pedidos.api.vo.factory;

import java.util.ArrayList;

import br.com.maikelsoft.pedidos.api.domain.model.ItemPedido;
import br.com.maikelsoft.pedidos.api.domain.model.Pedido;
import br.com.maikelsoft.pedidos.api.vo.CondicaoPagamentoVO;
import br.com.maikelsoft.pedidos.api.vo.ItemPedidoVO;
import br.com.maikelsoft.pedidos.api.vo.PedidoVO;
import br.com.maikelsoft.pedidos.api.vo.VendedorVO;

public class PedidoVOFactory {

	public static PedidoVO createThiny(Pedido ped) {
		PedidoVO vo = new PedidoVO();
		vo.setId(ped.getId());
		vo.setCliente(ClienteVOFactory.createThiny(ped.getCliente()));
		vo.setCondicaoPagamento(new CondicaoPagamentoVO(ped.getCondicaoPagamento()));
		vo.setData(ped.getData());
		vo.setEmissaoDireta(ped.isEmissaoDireta());
		vo.setTipoPedido(ped.getTipo().getId());
		vo.setTotal(ped.getTotal());
		vo.setTotalItens(ped.getTotalItens());
		vo.setVendedor(new VendedorVO(ped.getVendedor()));

		return vo;
	}

	public static PedidoVO createFull(Pedido ped) {
		PedidoVO vo = createThiny(ped);
		vo.setItens(new ArrayList<ItemPedidoVO>());
		for(ItemPedido item : ped.getItens()) {
			vo.getItens().add(new ItemPedidoVO(item));
		}

		return vo;
	}
}
