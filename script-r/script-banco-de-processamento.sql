drop database if exists processamento_db;
create database if not exists processamento_db;
use processamento_db;

create user 'admin'@'%' identified with mysql_native_password by 'bandtec';
grant all privileges on *.* to 'admin'@'%';
flush privileges;

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
	fkEcommerce int
);

create table if not exists metas(
    idMeta int primary key auto_increment,
    dataInicio datetime,
    dataFim datetime,
    valor decimal(10,2),
    tipo int,
    fkEcommerce int
);

select count(1) from eventos;
select count(1) from acessos;

truncate eventos;
truncate acessos;