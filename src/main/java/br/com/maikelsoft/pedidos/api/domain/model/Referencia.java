package br.com.maikelsoft.pedidos.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbreferencia")
public class Referencia {

	@Id
	@Column(name="referencia_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String telefones;

	@ManyToOne
	@JoinColumn(name="cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name="tipo_referencia_id")
	private TipoReferencia tipo;
}
