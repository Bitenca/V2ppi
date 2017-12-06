create table navio(
    id int not null auto_increment,
    nome varchar(20),
    tamanho double,
    nro_passageiros int(10),
    nacionalidade varchar(20),
    primary key (id)
)engine = InnoDB;