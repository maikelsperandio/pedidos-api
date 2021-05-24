package br.com.maikelsoft.pedidos.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.maikelsoft.pedidos.api.domain.model.Cliente;

public interface ClienteRepo extends JpaRepository<Cliente, Long>{

	List<Cliente> findAllByOrderByNome();

	@Query("select cli from Cliente cli where cli.ativo = true order by cli.nome ")
	List<Cliente> buscaTodosAtivosOrdenadosPorNome();

	@Query("select cli from Cliente cli where cli.ativo = true and upper(cli.nome) like upper(:nome)")
	List<Cliente> buscaPorNomeParcial(String nome);
}
