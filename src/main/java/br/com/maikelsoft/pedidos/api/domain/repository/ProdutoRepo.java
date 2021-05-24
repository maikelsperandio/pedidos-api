package br.com.maikelsoft.pedidos.api.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.maikelsoft.pedidos.api.domain.model.Linha;
import br.com.maikelsoft.pedidos.api.domain.model.Produto;

public interface ProdutoRepo extends JpaRepository<Produto, Long>{

	@Query("select prod from Produto prod order by prod.linha, prod.descricao")
	List<Produto> findAllOrdered();

	Optional<Produto> findByCodigo(Long codigo);

	List<Produto> findByLinha(Linha linha);
}
