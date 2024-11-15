
CREATE TABLE Cliente(
id int auto_increment primary key,
nombre varchar(50),
cuit long,
email varchar(50),
calle varchar(50),
altura int,
ciudad varchar(50),
pais varchar(50),
lat double,
lng double
);
    

CREATE TABLE Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    tipo_item VARCHAR(50) NOT NULL  -- Para almacenar el tipo de ItemMenu
);

CREATE TABLE Vendedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    calle varchar(50),
    altura int,
    ciudad varchar(50),
    pais varchar(50),
    lat double,
    lng double
    );


create table ItemMenu(
id int auto_increment primary key,
nombre varchar(50),
descripcion varchar(50),
precio double,
aptoVegano boolean,
aptoCeliaco boolean,
id_categoria int,
foreign key (id_categoria) references Categoria(id),
id_vendedor int references Vendedor(id),
calorias double,
peso double,
gradAlcoholica double,
tamanio double,
tipo enum('Plato','Bebida') );

CREATE TABLE Pago(
id int auto_increment primary key,
monto double,
metodoDePago enum("MERCADO PAGO","TRANSFERENCIA","EFECTIVO"));

CREATE TABLE Pedido(
id int auto_increment primary key,
cliente int references Cliente(id),
estado ENUM('RECIBIDO','ACEPTADO', 'PREPARADO', 'ENVIADO') NOT NULL,
pago int references Pago(id),
vendedor int references Vendedor(id)
);


CREATE TABLE itemPedido(
id int auto_increment primary key,
itemMenu int references ItemMenu(id),
pedido int references Pedido(id)
);