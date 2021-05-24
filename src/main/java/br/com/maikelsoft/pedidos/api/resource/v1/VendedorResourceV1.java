package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.service.VendedorService;
import br.com.maikelsoft.pedidos.api.vo.VendedorVO;

@RestController
@RequestMapping("/api/v1/vendedor")
public class VendedorResourceV1 {

	private @Autowired VendedorService vendedorService;

	@GetMapping("/")
	public List<VendedorVO> list(){
		System.out.println("executando get vendedores");
		return vendedorService.lista();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void inclui(@RequestBody VendedorVO vend) {
		vendedorService.inclui(vend);
	}

	@PutMapping("/{id}")
	public void atualiza(@RequestBody VendedorVO vend, @PathVariable("id") Long id) {
		vend.setId(id);
		vendedorService.atualiza(vend);
	}
}
