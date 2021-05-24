CREATE TABLE `tbproduto` (
	`produto_id` INT NOT NULL,
	`descricao` VARCHAR(500) NOT NULL,
	`codigo` BIGINT NOT NULL,
	`peso` DOUBLE NOT NULL,
	`qtde_kit` DOUBLE NOT NULL,
	`qtde_caixa` DOUBLE NOT NULL,
	`preco_custo` DECIMAL(10,2) NOT NULL,
	`preco_venda` DECIMAL(10,2) NOT NULL,
	`percentual_lucro` DECIMAL(10,2) NOT NULL,
	PRIMARY KEY (`produto_id`)
)
