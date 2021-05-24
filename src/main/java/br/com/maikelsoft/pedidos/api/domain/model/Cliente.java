package br.com.maikelsoft.pedidos.api.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.maikelsoft.pedidos.api.domain.model.enumeration.TipoPessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbcliente")
public class Cliente {

	@Id
	@Column(name="cliente_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="data_cadastro")
	private Date dataCadastro;
	private boolean ativo = true;
	private String nome;
	private String email;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String contato;

	@Column(name="fone_fixo")
	private String foneFixo;

	@Column(name="fone_celular")
	private String celular;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="tipo_pessoa")
	private TipoPessoa tipo;

	@ManyToOne
	@JoinColumn(name="cliente_fisica_id")
	@Cascade(CascadeType.ALL)
	private ClientePessoaFisica pessoaFisica;

	@ManyToOne
	@JoinColumn(name="cliente_juridica_id")
	@Cascade(CascadeType.ALL)
	private ClientePessoaJuridica pessoaJuridica;

	@ManyToOne
	@JoinColumn(name="municipio_id")
	private Municipio municipio;

	@OneToMany(mappedBy="cliente")
	@Cascade(value={CascadeType.ALL})
	private List<Referencia> referencias;
}
