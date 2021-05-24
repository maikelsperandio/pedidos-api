package br.com.maikelsoft.pedidos.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maikelsoft.pedidos.api.domain.model.Municipio;
import br.com.maikelsoft.pedidos.api.domain.model.UF;

public interface MunicipioRepo extends JpaRepository<Municipio, Long>{

	List<Municipio> findByUfOrderByDescricao(UF uf);

}
