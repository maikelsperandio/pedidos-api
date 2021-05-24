package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.domain.repository.CondicaoPagamentoRepo;
import br.com.maikelsoft.pedidos.api.vo.CondicaoPagamentoVO;

@RestController
@RequestMapping("/api/v1/condicaopagamento")
public class CondicaoPagamentoResourceV1 {

	private @Autowired CondicaoPagamentoRepo repo;

	@GetMapping("/")
	public List<CondicaoPagamentoVO> lista(){
		return repo.findAll().stream().map(cond -> new CondicaoPagamentoVO(cond)).collect(Collectors.toList());
	}
}
