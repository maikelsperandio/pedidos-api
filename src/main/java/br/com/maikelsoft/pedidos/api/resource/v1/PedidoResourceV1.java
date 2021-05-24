package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.service.PedidoService;
import br.com.maikelsoft.pedidos.api.vo.PedidoVO;

@RestController
@RequestMapping("/api/v1/pedido")
public class PedidoResourceV1 {

	private @Autowired PedidoService pedidoService;

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void inclui(@RequestBody PedidoVO vo) {
		pedidoService.inclui(vo);
	}

	@GetMapping("/")
	public List<PedidoVO> lista(){
		return pedidoService.lista();
	}

	@GetMapping("/{id}")
	public PedidoVO carrega(@PathVariable("id") Long id) {
		return pedidoService.carrega(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void atualiza(@PathVariable("id") Long id, @RequestBody PedidoVO vo) {
		vo.setId(id);
		pedidoService.atualiza(vo);
	}

	@GetMapping(value="/{id}/report", produces=MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] getReport(@PathVariable("id") Long id) {
		return pedidoService.getReport(id);
	}
}
