create table sala(
    id int not null auto_increment,
    placa varchar(20),
    marca varchar(50),
    modelo varchar(100),
    primary key (id)
)engine = InnoDB;