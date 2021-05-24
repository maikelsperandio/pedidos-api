package br.com.maikelsoft.pedidos.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maikelsoft.pedidos.api.domain.model.UnidadeMedida;

public interface UnidadeMedidaRepo extends JpaRepository<UnidadeMedida, Long>{

	UnidadeMedida findBySigla(String sigla);
}
