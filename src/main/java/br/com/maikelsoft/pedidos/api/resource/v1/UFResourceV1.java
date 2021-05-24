package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.maikelsoft.pedidos.api.domain.model.UF;
import br.com.maikelsoft.pedidos.api.domain.repository.UFRepo;
import br.com.maikelsoft.pedidos.api.vo.UFVO;

@RestController
@RequestMapping("/api/v1/uf")
public class UFResourceV1 {

	private @Autowired UFRepo ufRepo;

	@GetMapping("/{sigla}")
	public UFVO carrega(@PathVariable("sigla") String sigla) {
		UF uf = ufRepo.findBySigla(sigla);
		if(uf == null)
			throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma UF com a sigla ".concat(sigla));
		return new UFVO(uf.getId(), uf.getSigla());
	}

	@GetMapping("/")
	public List<UFVO> lista(){
		return ufRepo.findAllByOrderBySigla().stream()
			.map(uf -> new UFVO(uf.getId(), uf.getSigla()))
			.collect(Collectors.toList());
	}
}
