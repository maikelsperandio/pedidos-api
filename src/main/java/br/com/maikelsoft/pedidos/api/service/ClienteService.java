package br.com.maikelsoft.pedidos.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maikelsoft.pedidos.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.maikelsoft.pedidos.api.domain.model.Cliente;
import br.com.maikelsoft.pedidos.api.domain.model.ClientePessoaFisica;
import br.com.maikelsoft.pedidos.api.domain.model.ClientePessoaJuridica;
import br.com.maikelsoft.pedidos.api.domain.model.Referencia;
import br.com.maikelsoft.pedidos.api.domain.model.enumeration.TipoPessoa;
import br.com.maikelsoft.pedidos.api.domain.repository.ClienteRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.MunicipioRepo;
import br.com.maikelsoft.pedidos.api.domain.repository.TipoReferenciaRepo;
import br.com.maikelsoft.pedidos.api.vo.ClienteVO;
import br.com.maikelsoft.pedidos.api.vo.ReferenciaVO;
import br.com.maikelsoft.pedidos.api.vo.factory.ClienteVOFactory;

@Service
public class ClienteService {

	private @Autowired ClienteRepo clienteRepo;
	private @Autowired MunicipioRepo municipioRepo;
	private @Autowired TipoReferenciaRepo tipoReferenciaRepo;

	public List<ClienteVO> carrega(){
		return clienteRepo.buscaTodosAtivosOrdenadosPorNome()
					.stream()
						.map(cli -> ClienteVOFactory.createFull(cli))
						.collect(Collectors.toList());
	}

	public void salva(ClienteVO vo) {
		clienteRepo.save(this.converte(vo));
	}

	public List<ClienteVO> buscaParcial(String nome){
		return clienteRepo.buscaPorNomeParcial("%".concat(nome).concat("%"))
			.stream()
			.map(cli -> ClienteVOFactory.createThiny(cli))
			.collect(Collectors.toList());	
	}

	public void atualiza(ClienteVO vo) {
		Cliente cli = clienteRepo.findById(vo.getId())
					.orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar um cliente com ID ".concat(vo.getId().toString()))); 

		cli.setBairro(vo.getBairro());
		cli.setCelular(vo.getCelular());
		cli.setComplemento(vo.getComplemento());
		cli.setContato(vo.getContato());
		cli.setDataCadastro(vo.getDataCadastro());
		cli.setEmail(vo.getEmail());
		cli.setFoneFixo(vo.getFoneFixo());
		cli.setLogradouro(vo.getLogradouro());
		cli.setNome(vo.getNome());

		cli.setMunicipio(municipioRepo.findById(vo.getMunicipio().getId())
					.orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar um município com ID ".concat(vo.getId().toString()))));

		TipoPessoa pessoaVO = TipoPessoa.values()[vo.getTipoPessoa()];
		if(pessoaVO.equals(cli.getTipo())) {
			if(TipoPessoa.FISICA.equals(cli.getTipo())) {
				cli.getPessoaFisica().setCpf(vo.getCpf());
				cli.getPessoaFisica().setRg(vo.getRg());
			}else {
				cli.getPessoaJuridica().setCnpj(vo.getCnpj());
				cli.getPessoaJuridica().setIe(vo.getIe());
				cli.getPessoaJuridica().setNomeFantasia(vo.getNomeFantasia());
			}
		}
//		else {
//			cli.setTipo(pessoaVO);
//			if(pessoaVO.equals(TipoPessoa.FISICA)) {
//				ClientePessoaFisica fisica = new ClientePessoaFisica();
//				fisica.setCpf(vo.getCpf());
//				fisica.setRg(vo.getRg());
//				cli.setPessoaFisica(fisica);
//			}else {
//				ClientePessoaJuridica juridica = new ClientePessoaJuridica();
//				juridica.setCnpj(vo.getCnpj());
//				juridica.setIe(vo.getIe());
//				juridica.setNomeFantasia(vo.getNomeFantasia());
//				cli.setPessoaJuridica(juridica);
//			}
//		}

		this.createReferencias(vo, cli);

		clienteRepo.saveAndFlush(cli);
	}

	public void delete(Long id) {
		Cliente cli = clienteRepo.findById(id)
			.orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar um cliente com ID ".concat(id.toString())));
		cli.setAtivo(false);
		clienteRepo.saveAndFlush(cli);
	}

	private Cliente converte(ClienteVO vo) {
		Cliente cli = new Cliente();
		cli.setBairro(vo.getBairro());
		cli.setCelular(vo.getCelular());
		cli.setComplemento(vo.getComplemento());
		cli.setContato(vo.getContato());
		cli.setDataCadastro(vo.getDataCadastro());
		cli.setEmail(vo.getEmail());
		cli.setFoneFixo(vo.getFoneFixo());
		cli.setLogradouro(vo.getLogradouro());
		cli.setNome(vo.getNome());
		cli.setTipo(TipoPessoa.values()[vo.getTipoPessoa()]);

		if(TipoPessoa.FISICA.getId().equals(vo.getTipoPessoa())) {
			cli.setPessoaFisica(new ClientePessoaFisica());
			cli.getPessoaFisica().setCpf(vo.getCpf());
			cli.getPessoaFisica().setRg(vo.getRg());
		}else {
			cli.setPessoaJuridica(new ClientePessoaJuridica());
			cli.getPessoaJuridica().setCnpj(vo.getCnpj());
			cli.getPessoaJuridica().setIe(vo.getIe());
			cli.getPessoaJuridica().setNomeFantasia(vo.getNomeFantasia());
		}

		this.createReferencias(vo, cli);

		cli.setMunicipio(municipioRepo.getOne(vo.getMunicipio().getId()));

		return cli;
	}

	private void createReferencias(ClienteVO vo, Cliente cli) {
		cli.setReferencias(new ArrayList<Referencia>());
		if(vo.getReferencias() != null && !vo.getReferencias().isEmpty()) {
			for(ReferenciaVO refVO: vo.getReferencias()) {
				Referencia ref = new Referencia();
				ref.setId(refVO.getId());
				ref.setCliente(cli);
				ref.setNome(refVO.getNome());
				ref.setTelefones(refVO.getTelefones());
				ref.setTipo(tipoReferenciaRepo.findById(refVO.getTipo()).get());

				cli.getReferencias().add(ref);
			}
		}
	}
}
