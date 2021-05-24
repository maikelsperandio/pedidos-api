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
@Table(name="tbitem_pedido")
public class ItemPedido {

	@Id
	@Column(name="item_pedido_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Integer quantidade;
	private Double valor;
	private Double total;

	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
}
