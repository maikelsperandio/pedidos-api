package br.com.maikelsoft.pedidos.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbunidade_medida")
public class UnidadeMedida {

	@Id
	@Column(name="unidade_medida_id")
	private @Getter @Setter Long id;

	private @Getter @Setter String descricao;
	private @Getter @Setter String sigla;
}
