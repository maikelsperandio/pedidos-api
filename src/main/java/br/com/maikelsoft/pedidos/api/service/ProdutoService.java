package br.com.maikelsoft.pedidos.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.maikelsoft.pedidos.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.maikelsoft.pedidos.api.domain.model.Linha;
import br.com.maikelsoft.pedidos.api.domain.model.Produto;
import br.com.maikelsoft.pedidos.api.domain.repository.LinhaRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.ProdutoRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.UnidadeMedidaRepo;
import br.com.maikelsoft.pedidos.api.vo.ProdutoVO;

@Service
public class ProdutoService {

	private @Autowired ProdutoRepo produtoRepo;
	private @Autowired UnidadeMedidaRepo unidadeMedidaRepo;
	private @Autowired LinhaRepo linhaRepo;
	private @Autowired ProdutoReportService produtoReportService;

	public void inclui(ProdutoVO prodVO) {
		produtoRepo.save(this.converte(prodVO));
	}

	private Produto converte(ProdutoVO prodVO) {
		Produto prod = new Produto();
		prod.setCodigo(prodVO.getCodigo());
		prod.setDescricao(prodVO.getDescricao());
		prod.setPeso(prodVO.getPeso());
		prod.setPrecoCusto(prodVO.getPrecoCusto());
		prod.setPrecoAtacado(prodVO.getPrecoAtacado());
		prod.setPrecoVarejo(prodVO.getPrecoVarejo());
		prod.setQtdeCaixa(prodVO.getQtdeCaixa());
		prod.setQtdeKit(prodVO.getQtdeKit());
		prod.setUnidade(unidadeMedidaRepo.findBySigla(prodVO.getUnidade().getSigla()));
		prod.setLinha(linhaRepo.findById(prodVO.getLinha().getId()).get());

		return prod;
	}

	public void delete(Long id) {
		try {
			produtoRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Não foi possível encontrar um produto com o ID ".concat(id.toString()));
		} 
	}

	public ProdutoVO carregaByCodigo(Long codigo) {
		return produtoRepo.findByCodigo(codigo)
				.map(prod -> new ProdutoVO(prod))
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar um produto com código: ".concat(codigo.toString())));
	}

	public List<ProdutoVO> carregaTodos(){
		return produtoRepo.findAllOrdered().stream()
									.map(prod -> new ProdutoVO(prod))
									.collect(Collectors.toList());
	}

	public void atualiza(ProdutoVO prodVO) {
		Produto prod = this.converte(prodVO);
		prod.setId(prodVO.getId());

		produtoRepo.save(prod);
	}

	public byte[] getReport(Long linhaId, boolean atacado) {
		Linha linha = linhaRepo.findById(linhaId).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar a linha de produtos com ID: ".concat(linhaId.toString())));
		return produtoReportService.getReport(linha, atacado);
	}
}
