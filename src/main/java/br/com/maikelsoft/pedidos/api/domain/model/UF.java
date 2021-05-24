package br.com.maikelsoft.pedidos.api.domain.model;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Table(name="tbuf")
@ToString(of = {"id", "sigla"})
public class UF {

	@Id
	@Column(name="uf_id")
	private Long id;
	private String descricao;
	private String sigla;
}
