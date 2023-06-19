CREATE DATABASE IF NOT EXISTS Desafio01;
USE Desafio01;

CREATE TABLE produtos(
	id int(3) not null primary key,
    nome varchar(50) not null,
    valor decimal(6,2) not null,
    estoque int(100) not null
);

CREATE TABLE vendas(
	id_produto int(3) not null primary key,
    quantidade int not null,
    id_vendas int(100) not null
);
INSERT INTO vendas VALUES (1, 10, 2);

INSERT INTO produtos (id, nome, valor, estoque) VALUES(1, 'Camiseta', 9.99, 100),
  (2,'Notebook', 1999.99, 50),
  (3,'Mochila', 145.99, 75),
  (4,'Tênis', 240, 200),
  (5,'Meias', 8.50, 150),
  (6, 'Barra de Cereal', 12.99, 80),
  (7, 'Arroz 5kg', 17.20, 120),
  (8, 'Feijão', 6.43, 90),
  (9, 'Celular', 1000.90, 60),
  (10, 'Óleo', 5.99, 30);

