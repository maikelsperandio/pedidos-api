package br.com.maikelsoft.pedidos.api.resource.v1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.pedidos.api.domain.repository.LinhaRepo;
import br.com.maikelsoft.pedidos.api.vo.LinhaVO;

@RestController
@RequestMapping("/api/v1/linha")
public class LinhaResourveV1 {

	private @Autowired LinhaRepo linhaRepo;

	@GetMapping("/")
	public List<LinhaVO> lista(){
		return linhaRepo.findAll().stream()
					.map(ln -> new LinhaVO(ln))
					.collect(Collectors.toList());
	}
}
