package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.domain.repository.TipoReferenciaRepo;
import br.com.maikelsoft.pedidos.api.vo.TipoReferenciaVO;

@RestController
@RequestMapping("/api/v1/tiporeferencia")
public class TipoReferenciaResourceV1 {

	private @Autowired TipoReferenciaRepo tipoRepo;

	@GetMapping("/")
	public List<TipoReferenciaVO> lista(){
		return tipoRepo.findAll().stream()
			.map(ref -> new TipoReferenciaVO(ref))
			.collect(Collectors.toList());
	}
}
