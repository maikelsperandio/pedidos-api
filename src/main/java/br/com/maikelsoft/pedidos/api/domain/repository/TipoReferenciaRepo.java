package br.com.maikelsoft.pedidos.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maikelsoft.pedidos.api.domain.model.TipoReferencia;

public interface TipoReferenciaRepo extends JpaRepository<TipoReferencia, Long> {

}
