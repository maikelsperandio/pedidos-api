package br.com.maikelsoft.pedidos.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maikelsoft.pedidos.api.domain.model.Vendedor;
import br.com.maikelsoft.pedidos.api.domain.repository.VendedorRepo;
import br.com.maikelsoft.pedidos.api.vo.VendedorVO;

@Service
public class VendedorService {

	private @Autowired VendedorRepo vendedorRepo;

	public List<VendedorVO> lista(){
		return vendedorRepo.findAll()
								.stream()
								.map(vend -> new VendedorVO(vend))
								.collect(Collectors.toList());
	}

	public void inclui(VendedorVO vend) {
		vendedorRepo.save(this.converte(vend));
	}

	private Vendedor converte(VendedorVO vend) {
		Vendedor vendedor = new Vendedor();
		vendedor.setEmail(vend.getEmail());
		vendedor.setNome(vend.getNome());
		vendedor.setTelefone(vend.getTelefone());

		return vendedor;
	}

	public void atualiza(VendedorVO vend) {
		Vendedor vendor = this.converte(vend);
		vendor.setId(vend.getId());
		vendedorRepo.save(vendor);
	}
}
