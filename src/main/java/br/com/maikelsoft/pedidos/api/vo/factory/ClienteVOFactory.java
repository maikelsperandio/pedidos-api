package br.com.maikelsoft.pedidos.api.vo.factory;

import java.util.ArrayList;

import br.com.maikelsoft.pedidos.api.domain.model.Cliente;
import br.com.maikelsoft.pedidos.api.domain.model.Referencia;
import br.com.maikelsoft.pedidos.api.domain.model.enumeration.TipoPessoa;
import br.com.maikelsoft.pedidos.api.vo.ClienteVO;
import br.com.maikelsoft.pedidos.api.vo.MunicipioVO;
import br.com.maikelsoft.pedidos.api.vo.ReferenciaVO;

public class ClienteVOFactory {

	public static ClienteVO createThiny(Cliente cli) {
		ClienteVO vo = new ClienteVO();
		vo.setId(cli.getId());
		vo.setNome(cli.getNome());
		vo.setTipoPessoa(cli.getTipo().getId());
		if(TipoPessoa.FISICA.equals(cli.getTipo())) {
			vo.setCpf(cli.getPessoaFisica().getCpf());
			vo.setRg(cli.getPessoaFisica().getRg());
		}else {
			vo.setCnpj(cli.getPessoaJuridica().getCnpj());
			vo.setIe(cli.getPessoaJuridica().getIe());
			vo.setNomeFantasia(cli.getPessoaJuridica().getNomeFantasia());
		}

		return vo;
	}

	public static ClienteVO createFull(Cliente cliente) {
		ClienteVO vo = new ClienteVO();
		vo.setId(cliente.getId());
		vo.setDataCadastro(cliente.getDataCadastro());
		vo.setNome(cliente.getNome());
		vo.setEmail(cliente.getEmail());
		vo.setLogradouro(cliente.getLogradouro());
		vo.setComplemento(cliente.getComplemento());
		vo.setBairro(cliente.getBairro());
		vo.setContato(cliente.getContato());
		vo.setFoneFixo(cliente.getFoneFixo());
		vo.setCelular(cliente.getCelular());
		vo.setTipoPessoa(cliente.getTipo().getId());

		if(TipoPessoa.FISICA.equals(cliente.getTipo())) {
			vo.setCpf(cliente.getPessoaFisica().getCpf());
			vo.setRg(cliente.getPessoaFisica().getRg());
		}else {
			vo.setNomeFantasia(cliente.getPessoaJuridica().getNomeFantasia());
			vo.setCnpj(cliente.getPessoaJuridica().getCnpj());
			vo.setIe(cliente.getPessoaJuridica().getIe());
		}

		if(cliente.getReferencias() != null && !cliente.getReferencias().isEmpty()) {
			vo.setReferencias(new ArrayList<ReferenciaVO>());
			for(Referencia ref : cliente.getReferencias()) {
				vo.getReferencias().add(new ReferenciaVO(ref));
			}
		}

		if(cliente.getMunicipio() != null) {
			vo.setMunicipio(new MunicipioVO(cliente.getMunicipio()));
		}

		return vo;
	}
}
