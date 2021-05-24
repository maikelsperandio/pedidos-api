package br.com.maikelsoft.pedidos.api.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import br.com.maikelsoft.pedidos.api.domain.model.Linha;
import br.com.maikelsoft.pedidos.api.domain.model.Vendedor;
import br.com.maikelsoft.pedidos.api.domain.repository.ProdutoRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.VendedorRepo;
import br.com.maikelsoft.pedidos.api.formatter.CellphoneFormatter;
import br.com.maikelsoft.pedidos.api.report.ReportBase;
import br.com.maikelsoft.pedidos.api.vo.ProdutoVO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ProdutoReportService {

	private @Autowired ReportBase reportBase;
	private @Autowired ProdutoRepo produtoRepo;
	private @Autowired VendedorRepo vendedorRepo;
	private CellphoneFormatter cellphoneFormatter = new CellphoneFormatter();

	public byte[] getReport(Linha linha, boolean atacado) {
		List<ProdutoVO> prods = produtoRepo.findByLinha(linha).stream().map(prod -> new ProdutoVO(prod)).collect(Collectors.toList());
		Map<String, Object> params = new HashMap<String, Object>();
		String titulo = "Planilha de preços - ".concat(linha.getDescricao());
		Vendedor vendedor = vendedorRepo.findById(1L).get();
		params.put("tituloRelatorio", atacado ? titulo.concat(" - Atacado") : titulo.concat(" - Varejo"));
		params.put("nomeVendedor", vendedor.getNome().concat(" - ").concat(cellphoneFormatter.format(vendedor.getTelefone())));
		params.put("atacado", atacado);
		try {
			params.put("imgLanura", ResourceUtils.getFile("classpath:images/lanura-logo.png"));
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(prods);
			byte[] report = reportBase.getReportPDFAsByteArray(params, "produtos", ds);
			FileUtils.writeByteArrayToFile(new File("C:\\lixo\\produtos.pdf"), report);
			return report;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
