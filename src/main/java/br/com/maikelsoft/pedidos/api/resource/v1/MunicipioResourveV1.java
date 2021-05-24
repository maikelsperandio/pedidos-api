package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.service.MunicipioService;
import br.com.maikelsoft.pedidos.api.vo.MunicipioVO;

@RestController
@RequestMapping("/api/v1/municipio")
public class MunicipioResourveV1 {

	private @Autowired MunicipioService municipioService;

	@GetMapping("/uf/{sigla}")
	public List<MunicipioVO> carregaPorUf(@PathVariable("sigla") String uf){
		return municipioService.carregaPorUf(uf);
	}

	@GetMapping("/{id}")
	public MunicipioVO carrega(@PathVariable("id") Long id) {
		return municipioService.carrega(id);
	}
}
