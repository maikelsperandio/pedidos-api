package br.com.maikelsoft.pedidos.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbtipo_referencia")
public class TipoReferencia {

	@Id
	@Column(name="tipo_referencia_id")
	private Long id;
	private String descricao;
}
