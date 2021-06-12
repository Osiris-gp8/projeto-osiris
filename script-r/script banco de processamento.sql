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
    dataCompra date
);

select * from eventos;

select count(*) from eventos;

-- delete from eventos where idEvento > 0