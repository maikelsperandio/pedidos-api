package br.com.maikelsoft.pedidos.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maikelsoft.pedidos.api.domain.model.CondicaoPagamento;

public interface CondicaoPagamentoRepo extends JpaRepository<CondicaoPagamento, Long>{

}
