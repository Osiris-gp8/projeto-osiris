drop database if exists processamento_db;
create database if not exists processamento_db;
use processamento_db;

create table if not exists eventos(
	idEvento int primary key auto_increment,
    idConsumidor int,
    dataNascimento date,
    preco decimal(10,2),
    nome varchar(50),
    categoria varchar(50),
    fkCupom int,
    statusEvento int,
    fkEcommerce int,
    sexo char(1),
    `time` varchar(20),
    dataCompra date
);

create table if not exists acessos(
	idAcesso int primary key auto_increment,
	idConsumidor int,
	inicioAcesso datetime,
	fimAcesso datetime,
	fkEcommerce int,
    localidade int
);

select * from acessos;
select count(1) from eventos;
select count(1) from acessos;

truncate eventos;
truncate acessos;