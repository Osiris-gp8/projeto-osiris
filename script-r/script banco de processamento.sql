create database if not exists processamento_db;
use processamento_db;

create table if not exists eventos(
	idEvento int primary key auto_increment,
    idConsumidor int,
    idade int,
    preco decimal(10,2),
    nome varchar(50),
    categoria varchar(50),
    fkCupom int,
    statusEvento int,
    fkEcommerce int,
    dataCompra int
);

select count(*) from eventos;