package br.com.maikelsoft.pedidos.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.maikelsoft.pedidos.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.maikelsoft.pedidos.api.domain.model.Cliente;
import br.com.maikelsoft.pedidos.api.domain.model.CondicaoPagamento;
import br.com.maikelsoft.pedidos.api.domain.model.ItemPedido;
import br.com.maikelsoft.pedidos.api.domain.model.Pedido;
import br.com.maikelsoft.pedidos.api.domain.model.Produto;
import br.com.maikelsoft.pedidos.api.domain.model.Vendedor;
import br.com.maikelsoft.pedidos.api.domain.model.enumeration.TipoPedido;
import br.com.maikelsoft.pedidos.api.domain.repository.ClienteRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.CondicaoPagamentoRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.PedidoRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.ProdutoRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.VendedorRepo;
import br.com.maikelsoft.pedidos.api.vo.ItemPedidoVO;
import br.com.maikelsoft.pedidos.api.vo.PedidoVO;
import br.com.maikelsoft.pedidos.api.vo.factory.PedidoVOFactory;

@Component
public class PedidoService {

	private @Autowired PedidoRepo pedidoRepo;
	private @Autowired ClienteRepo clienteRepo;
	private @Autowired VendedorRepo vendedorRepo;
	private @Autowired CondicaoPagamentoRepo condicaoPagamentoRepo;
	private @Autowired ProdutoRepo produtoRepo;

	private @Autowired PedidoReportService pedidoReportService;


	public void inclui(PedidoVO vo) {
		pedidoRepo.save(this.converte(vo));
	}

	public List<PedidoVO> lista(){
		return pedidoRepo.findAll().stream()
					.map(ped -> PedidoVOFactory.createThiny(ped))
					.collect(Collectors.toList());
	}

	public PedidoVO carrega(Long id) {
		Pedido ped = this.getPedido(id);
		return PedidoVOFactory.createFull(ped);
	}

	public void atualiza(PedidoVO vo) {
		Pedido ped = this.getPedido(vo.getId());
		ped.setCliente(this.getCliente(vo.getCliente().getId()));
		ped.setCondicaoPagamento(this.getCondicao(vo.getCondicaoPagamento().getId()));
		ped.setData(vo.getData());
		ped.setEmissaoDireta(vo.isEmissaoDireta());
		ped.setTipo(TipoPedido.values()[vo.getTipoPedido()]);
		ped.setTotal(vo.getTotal());
		ped.setTotalItens(vo.getTotalItens());
		ped.setVendedor(this.getVendedor(vo.getVendedor().getId()));

		ped.getItens().clear();
		ped.getItens().addAll(this.converteItens(ped, vo));

		pedidoRepo.save(ped);
	}

	public byte[] getReport(Long id) {
		return pedidoReportService.getReport(id);
	}

	private Pedido converte(PedidoVO vo) {
		Pedido ped = new Pedido();
		ped.setCliente(this.getCliente(vo.getCliente().getId()));
		ped.setCondicaoPagamento(this.getCondicao(vo.getCondicaoPagamento().getId()));
		ped.setData(vo.getData());
		ped.setEmissaoDireta(vo.isEmissaoDireta());
		ped.setTipo(TipoPedido.values()[vo.getTipoPedido()]);
		ped.setTotal(vo.getTotal());
		ped.setTotalItens(vo.getTotalItens());
		ped.setVendedor(this.getVendedor(vo.getVendedor().getId()));

		ped.setItens(this.converteItens(ped, vo));

		return ped;
	}

	private List<ItemPedido> converteItens(Pedido ped, PedidoVO vo){
		List<ItemPedido> itens = new ArrayList<ItemPedido>();
		for(ItemPedidoVO itemVO : vo.getItens()) {
			ItemPedido item = new ItemPedido();
			item.setPedido(ped);
			item.setProduto(this.getProduto(itemVO.getProduto().getId()));
			item.setQuantidade(itemVO.getQuantidade());
			item.setTotal(itemVO.getTotal());
			item.setValor(itemVO.getValor());

			itens.add(item);
		}

		return itens;
	}

	private Cliente getCliente(Long id) {
		return clienteRepo.findById(id)
		.orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar um cliente com o ID: ".concat(id.toString())));
	}

	private CondicaoPagamento getCondicao(Long id) {
		return condicaoPagamentoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar a condição de pagamento para o pedido."));
	}

	private Vendedor getVendedor(Long id) {
		return vendedorRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Vendedor não encontrado."));
	}

	private Produto getProduto(Long id) {
		return produtoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));
	}

	private Pedido getPedido(Long id) {
		return pedidoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado"));
	}
}
