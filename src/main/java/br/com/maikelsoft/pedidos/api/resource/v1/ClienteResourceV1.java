package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.service.ClienteService;
import br.com.maikelsoft.pedidos.api.vo.ClienteVO;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteResourceV1 {

	private @Autowired ClienteService clienteService;

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void inclui(@RequestBody ClienteVO vo) {
		clienteService.salva(vo);
	}

	@GetMapping("/")
	public List<ClienteVO> carrega(){
		return clienteService.carrega();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void atualiza(@PathVariable("id") Long id, @RequestBody ClienteVO vo) {
		vo.setId(id);
		clienteService.atualiza(vo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		clienteService.delete(id);
	}

	@GetMapping("/parcial/{nome}")
	public List<ClienteVO> buscaParcial(@PathVariable("nome") String nome){
		return clienteService.buscaParcial(nome);
	}
}
