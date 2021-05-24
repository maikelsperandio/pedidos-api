package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.domain.repository.UnidadeMedidaRepo;
import br.com.maikelsoft.pedidos.api.vo.UnidadeMedidaVO;

@RestController
@RequestMapping("/api/v1/unidade")
public class UnidadeMedidaResourceV1 {

	private @Autowired UnidadeMedidaRepo unidadeMedidaRepo;

	@GetMapping("/")
	public List<UnidadeMedidaVO> lista(){
		return unidadeMedidaRepo.findAll()
					.stream()
						.map(und -> new UnidadeMedidaVO(und))
						.collect(Collectors.toList());
	}
}
