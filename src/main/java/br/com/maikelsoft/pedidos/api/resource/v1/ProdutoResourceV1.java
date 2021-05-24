package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.service.ProdutoService;
import br.com.maikelsoft.pedidos.api.vo.ProdutoVO;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoResourceV1 {

	private @Autowired ProdutoService produtoService;

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void inclui(@RequestBody ProdutoVO prod) {
		produtoService.inclui(prod);
	}

	@GetMapping("/{codigo}")
	public ProdutoVO carrega(@PathVariable("codigo") Long codigo) {
		return produtoService.carregaByCodigo(codigo);
	}

	@GetMapping("/")
	public List<ProdutoVO> lista(){
		return produtoService.carregaTodos();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void atualiza(@RequestBody ProdutoVO prodVO, @PathVariable("id") Long id) {
		prodVO.setId(id);
		produtoService.atualiza(prodVO);
	}

	@DeleteMapping("/{id}")
	public void exclui(@PathVariable("id") Long id) {
		produtoService.delete(id);
	}

	@GetMapping(value="/linha/{linhaId}/report/atacado", produces=MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] getReportAtacado(@PathVariable("linhaId") Long linhaId) {
		return produtoService.getReport(linhaId, true);
	}
	@GetMapping(value="/linha/{linhaId}/report/varejo", produces=MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] getReportVarejo(@PathVariable("linhaId") Long linhaId) {
		return produtoService.getReport(linhaId, false);
	}
}
