package br.com.maikelsoft.pedidos.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="tbmunicipio")
@ToString(includeFieldNames = true)
public class Municipio {

	@Id
	@Column(name="municipio_id")
	private Long id;
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "uf_id")
	private UF uf;
}
