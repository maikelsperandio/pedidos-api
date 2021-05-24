package br.com.maikelsoft.pedidos.api.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UFVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String sigla;

}
