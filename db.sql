create database db_api_de_produtos;

use db_api_de_produtos;

create table tbl_categorias(
	categoria_id int not null primary key auto_increment,
    categoria_descricao text not null,
    categoria_data_cadastro date not null
);
create table tbl_produtos(
	produto_id int not null primary key auto_increment,
    produto_nome varchar(255) not null,
    produto_descricao_resumida text not null,
    produto_descricao_completa text not null,
    produto_preco_venda double not null,
    produto_em_destaque bit not null,
    produto_data_cadastro date not null,
    produto_quantidade_unidades_em_estoque int not null,
    categoria_id int not null,
    constraint fk_categoria_id foreign key(categoria_id) references tbl_categorias(categoria_id)
);
select * from tbl_categorias; 
select * from tbl_produtos;

insert into tbl_categorias(categoria_descricao, categoria_data_cadastro) values('Categoria de teste 3', now());
insert into tbl_produtos(
	produto_nome,
    produto_descricao_resumida,
    produto_descricao_completa,
    produto_preco_venda,
    produto_em_destaque,
    produto_data_cadastro,
    produto_quantidade_unidades_em_estoque,
    categoria_id
) values('Produto de teste 1', 'Produto de teste 1 - descrição resumida', 'Produto de teste 1 - descrição completa', 12.87, 1, now(), 23, 1);