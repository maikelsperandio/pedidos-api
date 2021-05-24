package br.com.maikelsoft.pedidos.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.maikelsoft.pedidos.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.maikelsoft.pedidos.api.domain.model.Municipio;
import br.com.maikelsoft.pedidos.api.domain.model.UF;
import br.com.maikelsoft.pedidos.api.domain.repository.MunicipioRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.UFRepo;
import br.com.maikelsoft.pedidos.api.vo.MunicipioVO;

@Component
public class MunicipioService {

	private @Autowired MunicipioRepo municipioRepo;
	private @Autowired UFRepo ufRepo;

	public List<MunicipioVO> carregaPorUf(String sigla){
		UF uf = ufRepo.findBySigla(sigla);
		if(uf == null)
			throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma UF com a sigla ".concat(sigla));

		List<Municipio> list = municipioRepo.findByUfOrderByDescricao(uf);
		if(list == null || list.isEmpty())
			throw new EntidadeNaoEncontradaException("Não foi possível encontrar nenhum município para a UF ".concat(uf.getSigla()));

		return list.stream()
			.map(mun -> new MunicipioVO(mun))
			.collect(Collectors.toList());
	}

	public MunicipioVO carrega(Long id) {
		return municipioRepo.findById(id)
				.map(mun -> new MunicipioVO(mun))
			.orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar um município com o ID ".concat(String.valueOf(id))));
	}
}
