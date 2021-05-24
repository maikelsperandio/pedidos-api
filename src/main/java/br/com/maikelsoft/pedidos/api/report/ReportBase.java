package br.com.maikelsoft.pedidos.api.report;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Component
public class ReportBase {

	public byte[] getReportPDFAsByteArray(Map<String, Object> mapaParametros, String nomeJasper, JRDataSource dataSource) throws Exception{
		String pathRel = ResourceUtils.getFile("classpath:reports/".concat(nomeJasper).concat(".jasper")).getAbsolutePath();
//		JasperCompileManager.compileReport(pathRel);
		JasperPrint print = JasperFillManager.fillReport(pathRel, mapaParametros, dataSource);
		return JasperExportManager.exportReportToPdf(print);
//		byte[] bytes = JasperExportManager.exportReportToPdf(print);
//		return new ByteArrayInputStream(bytes);
	}
}
