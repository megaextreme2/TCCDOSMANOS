USE master if exists (SELECT * FROM SYS.databases WHERE name = 'BD_Cantina_Finn_Tech') 
DROP DATABASE BD_Cantina_Finn_Tech 
GO 

create database BD_Cantina_Finn_Tech 
go 

use BD_Cantina_Finn_Tech 


create table Cliente( 

id bigint not null identity (1,1) primary key, 
nome varchar(100), 
email varchar(50) not null unique, 
senha varchar(20) not null unique, 
cod_status bit not null 

/* OKOK */ 

);

create table Pedido( 

id bigint not null identity (1,1) primary key, 
qtd_produtos integer not null, 
preco_total decimal (4,2) not null, 
metodo_pagamento varchar(25) not null, 
id_cliente bigint not null, 
constraint fk_pedido_cliente_id foreign key (id_cliente) references Cliente(id), 
cod_status bit not null

/* OK */ 

);


create table Produto( 

id bigint not null identity (1,1) primary key, 
nome varchar(100) not null,  
descricao varchar(100), 
preco decimal (4,2) not null, 
qtd_estoque integer not null, 
id_pedido bigint not null, 
constraint fk_produto_pedido_id foreign key (id_pedido) references Pedido(id), 
cod_status bit not null 

/* OK */ 

);

create table Categoria( 

id bigint not null identity (1,1) primary key, 
nome varchar(30) not null, 
descricao varchar(100), 
id_produto bigint not nulL, 
constraint fk_categoria_produto_id foreign key (id_produto) references Produto(id), 
cod_status bit not null 

/* OKOK */

);

create table Funcionario( 

id bigint not null identity (1,1) primary key, 
nome varchar(100), 
turno_trabalho varchar(30) not null, 
cod_status bit not null  

/* OKOK */ 

);

INSERT into Cliente(nome, email, senha, cod_status) 
VALUES 
('raphoso', 'tccdosmanos@gmail.com','raphosonaopegue', 1) 

Select * from Cliente

INSERT into Pedido(qtd_produtos, preco_total, metodo_pagamento, id_cliente, cod_status) 
VALUES 
(1, 22.29, 'Pix', 1, 1) 

Select * from Pedido

INSERT into Produto(nome, preco, qtd_estoque, id_pedido, cod_status) 
VALUES 
('Sopa de Raphoso', 1.99, 3, 1, 1) 

Select * from Produto


INSERT into Categoria(nome, id_produto, cod_status) 
VALUES 
('Categoria Raphosa', 1, 1) 

Select * from Categoria

INSERT into Funcionario(nome, turno_trabalho, cod_status) 
VALUES 
('Sr. Raphoso', 'Manhã', 1) 

Select * from Funcionario