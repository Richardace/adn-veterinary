create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 correo text not null,
 clave varchar(45) not null,
 rol varchar(100) not null,
 primary key (id)
);

create table cita (
 id int(11) not null auto_increment,
 idUsuario int(11) not null,
 fecha datetime not null,
 hora int(11) not null,
 precio double(100) not null,
 notas text not null,
 primary key (id),
 FOREIGN KEY (idUsuario) REFERENCES usuario(id)
);
