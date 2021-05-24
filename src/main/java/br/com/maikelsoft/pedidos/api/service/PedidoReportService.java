package br.com.maikelsoft.pedidos.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.maikelsoft.pedidos.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.maikelsoft.pedidos.api.domain.model.Pedido;
import br.com.maikelsoft.pedidos.api.domain.model.enumeration.TipoPessoa;
import br.com.maikelsoft.pedidos.api.domain.repository.PedidoRepo;
import br.com.maikelsoft.pedidos.api.formatter.CellphoneFormatter;
import br.com.maikelsoft.pedidos.api.formatter.PhoneFormatter;
import br.com.maikelsoft.pedidos.api.report.ReportBase;
import br.com.maikelsoft.pedidos.api.vo.ItemPedidoVO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class PedidoReportService {

	private @Autowired PedidoRepo pedidoRepo;
	private @Autowired ReportBase reportBase;

	private CPFFormatter cpfFormatter = new CPFFormatter();
	private CNPJFormatter cnpjFormatter = new CNPJFormatter();
	private CellphoneFormatter cellphoneFormatter = new CellphoneFormatter();
	private PhoneFormatter phoneFormatter = new PhoneFormatter();

	public byte[] getReport(Long id){
		Pedido ped = pedidoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar um pedido com o ID: ".concat(id.toString())));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", ped.getData());
		params.put("formaPagamento", ped.getCondicaoPagamento().getDescricao());
		params.put("nomeCliente", ped.getCliente().getNome());
		params.put("tipoCliente", ped.getCliente().getTipo().getId());

		if(ped.getCliente().getTipo().equals(TipoPessoa.FISICA)) {
			params.put("descDoc1", "CPF:");
			String cpf = cpfFormatter.format(ped.getCliente().getPessoaFisica().getCpf());
			params.put("doc1", cpf);

			params.put("descDoc2", "RG:");
			params.put("doc2", ped.getCliente().getPessoaFisica().getRg());
		}else {
			params.put("descDoc1", "CNPJ:");
			String cnpj = cnpjFormatter.format(ped.getCliente().getPessoaJuridica().getCnpj());
			params.put("doc1", cnpj);

			params.put("descDoc2", "Insc. Est.:");
			params.put("doc2", ped.getCliente().getPessoaJuridica().getIe());

			params.put("fantasia", ped.getCliente().getPessoaJuridica().getNomeFantasia());
		}

		params.put("endereco", ped.getCliente().getLogradouro());
		params.put("complemento", ped.getCliente().getComplemento());
		params.put("bairro", ped.getCliente().getBairro());
		params.put("cidade", ped.getCliente().getMunicipio().getDescricao());
		params.put("uf", ped.getCliente().getMunicipio().getUf().getSigla());
		params.put("contato", ped.getCliente().getContato());
		params.put("email", ped.getCliente().getEmail());

		if(StringUtils.isNotEmpty(ped.getCliente().getFoneFixo()))
			params.put("foneFixo", phoneFormatter.format(ped.getCliente().getFoneFixo()));
		if(StringUtils.isNotEmpty(ped.getCliente().getCelular()))
			params.put("foneCelular", cellphoneFormatter.format(ped.getCliente().getCelular()));

		params.put("vendedor", ped.getVendedor().getNome());
		params.put("telefoneVendedor", cellphoneFormatter.format(ped.getVendedor().getTelefone()));
		params.put("totalItens", ped.getTotalItens());
		params.put("totalPedido", ped.getTotal());
		try {
			params.put("imgLanura", ResourceUtils.getFile("classpath:images/lanura-logo.png"));
	
			List<ItemPedidoVO> itens = ped.getItens().stream().map(it -> new ItemPedidoVO(it)).collect(Collectors.toList());
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(itens);

			byte[] report = reportBase.getReportPDFAsByteArray(params, "pedido2", ds);
//			FileUtils.writeByteArrayToFile(new File("C:\\lixo\\pedido.pdf"), report);
			return report;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
