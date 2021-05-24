package br.com.maikelsoft.pedidos.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maikelsoft.pedidos.api.domain.model.Pedido;

public interface PedidoRepo extends JpaRepository<Pedido, Long>{

}
