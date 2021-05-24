package br.com.maikelsoft.pedidos.api.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
//	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataCadastro;
	private String nome;
	private String email;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String contato;
	private String foneFixo;
	private String celular;
	private Integer tipoPessoa;

	private String cpf;
	private String rg;

	private String nomeFantasia;
	private String cnpj;
	private String ie;

	private MunicipioVO municipio;

	private List<ReferenciaVO> referencias;

}
