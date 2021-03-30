create database if not exists osiris;
use osiris;

create table if not exists dominio_status (
	id_dominio_status int primary key auto_increment,
    nome varchar(45)
);

create table if not exists cupom (
	id_cupom int primary key auto_increment,
    nome_cupom varchar(45),
    valor decimal(2,1),
    data_expirado date,
    data_validado date
);

create table if not exists ecommerce (
	id_ecommerce int primary key auto_increment,
    cnpj char(14),
    nome varchar(45)
);

create table if not exists usuario (
	id_usuario int primary key auto_increment,
    login varchar(45),
    senha varchar(45),
    fk_ecommerce int,
    foreign key (fk_ecommerce) references ecommerce(id_ecommerce)
);

create table if not exists evento (
	id_compra int primary key auto_increment,
    id_consumidor_ecommerce int,
    nome_produto varchar(45),
    preco decimal(6,2),
    nome_categoria varchar(45),
    data_compra date,
    cupom varchar(45),
    fk_ecommerce int,
    fk_cupom int,
    fk_status int,
    foreign key (fk_ecommerce) references ecommerce(id_ecommerce),
    foreign key (fk_cupom) references cupom(id_cupom),
    foreign key (fk_status) references dominio_status(id_dominio_status)
);

create table if not exists acessos (
	id_acessos int primary key auto_increment,
	id_consumidorEcommerce int,
	inicio_acesso date,
	fim_acesso date
);

insert into dominio_status values
(null, "Cancelada"),
(null, "Finalizada");

insert into cupom values
(null, "", 0.0, "2021-03-31", "2021-03-26"),
(null, "OSIRIS10", 0.1, "2021-03-31", "2021-03-26"),
(null, "OSIRIS20", 0.2, "2021-04-10", "2021-04-05"),
(null, "OSIRIS50", 0.5, "2021-04-15", "2021-04-10");

insert into ecommerce values
(null, "01234567891234", "ficticio");

insert into usuario values
(null, "zezinho", "12345", 1);

insert into evento values
(null, 505, "Calça jeans", 70.89, "Roupas", "2021-03-26", "", 1, 1, 1),
(null, 505, "Camiseta", 70.89, "Roupas", "2021-03-26", "OSIRIS20", 1, 2, 2),
(null, 505, "Mouse gamer", 70.89, "Periféricos", "2021-03-26", "CASCA10", 1, 1, 2),
(null, 203, "Teclado USB", 70.89, "Periféricos", "2021-03-26", "CASCA10", 1, 1, 2),
(null, 203, "Teclado USB", 70.89, "Periféricos", "2021-03-26", "CASCA10", 1, 1, 2);

insert into acessos values
(null, 505, "2021-03-26", "2021-03-27"),
(null, 203, "2021-03-27", "2021-03-28");

select * from evento where id_consumidor_ecommerce = 203 group by nome_produto;

select count(id_acessos) as quantidade from acessos where inicio_acesso between current_date()-7 and current_date();
select count(id_compra) as quantidade from evento;
select count(id_acessos) as quantidade from acessos;
select nome_categoria, count(id_compra) as quantidade from evento group by nome_categoria order by quantidade desc limit 5;

select count(id_compra) as quantidade from evento where id_consumidor_ecommerce = 203;

