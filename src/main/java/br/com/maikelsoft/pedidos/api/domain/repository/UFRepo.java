package br.com.maikelsoft.pedidos.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maikelsoft.pedidos.api.domain.model.UF;

public interface UFRepo extends JpaRepository<UF, Long>{

	UF findBySigla(String sigla);
	List<UF> findAllByOrderBySigla();
}
