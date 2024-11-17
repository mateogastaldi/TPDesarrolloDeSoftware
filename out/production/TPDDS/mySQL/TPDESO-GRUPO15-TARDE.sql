
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
                        tipo_item VARCHAR(50) NOT NULL  #-- Para almacenar el tipo de ItemMenu
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
                        descripcion varchar(200),
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
                    metodoDePago enum("MERCADO PAGO","TRANSFERENCIA","EFECTIVO"),
                    alias VARCHAR(50),
                    cbu VARCHAR(50),
                    cuit long,
                    pagado bool
);

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

INSERT INTO Cliente(nombre, cuit, email, calle, altura, ciudad, pais, lat, lng) VALUES
                                                                                    ("Mateo Gastaldi", 2454893433, "mateogastaldi@gmail.com", "Llerena", 2240, "Santa Fe", "Argentina", 31.37, 60.45),
                                                                                    ("Sofía Rodríguez", 3104567890, "sofia.rodriguez@gmail.com", "Mitre", 125, "Rosario", "Argentina", 32.94, 60.64),
                                                                                    ("Lucas Martínez", 2709876543, "lucas.martinez@gmail.com", "San Martín", 3500, "Córdoba", "Argentina", 31.41, 64.18),
                                                                                    ("Martina López", 2901234567, "martina.lopez@gmail.com", "Alem", 987, "Mendoza", "Argentina", 32.88, 68.84),
                                                                                    ("Julián Fernández", 3009871234, "julian.fernandez@gmail.com", "Belgrano", 452, "Buenos Aires", "Argentina", -34.61, -58.38),
                                                                                    ("Camila Sánchez", 2804567891, "camila.sanchez@gmail.com", "Rivadavia", 789, "Salta", "Argentina", -24.79, -65.41),
                                                                                    ("Emilia Pérez", 3106785432, "emilia.perez@gmail.com", "Sarmiento", 2345, "Neuquén", "Argentina", -38.95, -68.06),
                                                                                    ("Joaquín Gómez", 2954321098, "joaquin.gomez@gmail.com", "Independencia", 456, "Tucumán", "Argentina", -26.82, -65.22),
                                                                                    ("Valentina Herrera", 3056789012, "valentina.herrera@gmail.com", "Italia", 102, "Santa Cruz", "Argentina", -50.33, -72.27),
                                                                                    ("Francisco Romero", 3158901234, "francisco.romero@gmail.com", "España", 678, "La Pampa", "Argentina", -36.62, -64.29);

INSERT INTO Vendedor(nombre,calle,altura,ciudad,pais,lat,lng) VALUES
                                                                    ("La Parrillada de Julio","Mitre",2328,"Santa Fe","Argentina",37.8,65),
                                                                    ("Panadería Los Trigales", "Belgrano", 1456, "Rosario", "Argentina", -32.94, -60.64),
                                                                    ("Heladería Dulce Frío", "Colón", 1024, "Bariloche", "Argentina", -41.13, -71.31);

INSERT INTO Categoria(descripcion, tipo_item) VALUES
                                                    ("Postre","Plato"),#--1
                                                    ("Gaseosa","Bebida"),#--2
                                                    ("Plato Principal","Plato"),#--3
                                                    ("Entrada","Plato"),#--4
                                                    ("Vino","Bebida"),#--5
                                                    ("Cerveza","Bebida"),#--6
                                                    ("Facturas","Plato"),#--7
                                                    ("Jugos","Bebida"),#--8
                                                    ("Agua","Bebida");#--9


INSERT INTO ItemMenu( nombre, descripcion, precio, aptovegano, aptoceliaco, id_categoria, id_vendedor, calorias, peso, gradalcoholica, tamanio, tipo) VALUES
                                                                                                                                                            ("Parrillada para 2","Parrilla completa para dos personas",15600,false,true,3,1,500,1000,null,null,"Plato"),#--1
                                                                                                                                                            ("Milanesa con papas fritas", "Clásica milanesa de carne con papas fritas doradas", 12000, false, false, 3, 1, 850, 700, NULL, NULL, "Plato"),#--2
                                                                                                                                                            ("Ensalada César", "Lechuga, pollo grillado, croutones y aderezo césar", 8900, true, true, 3, 1, 250, 350, NULL, NULL, "Plato"),#--3
                                                                                                                                                            ("Sopa de verduras", "Sopa caliente con vegetales frescos", 5600, true, true, 4, 1, 120, 300, NULL, NULL, "Plato"),#--4
                                                                                                                                                            ("Pizza Margarita", "Pizza con salsa de tomate, mozzarella y albahaca", 15000, true, false, 3, 1, 1200, 900, NULL, NULL, "Plato"),#--5
                                                                                                                                                            ("Empanadas de carne", "Media docena de empanadas al horno", 7200, false, false, 4, 1, 450, 600, NULL, NULL, "Plato"),#--6

#-- Bebidas (calorias y peso son NULL)
                                                                                                                                                            ("Cerveza Quilmes", "Botella de cerveza Quilmes 1L", 2000, true, true, 6, 1, NULL, NULL, 4.5, 1000, "Bebida"),#--7
                                                                                                                                                            ("Fanta Naranja", "Botella de Fanta de 500ml", 1400, true, true, 2, 1, NULL, NULL, 0, 500, "Bebida"),#--8
                                                                                                                                                            ("Vino Malbec", "Botella de vino Malbec de 750ml", 3500, true, true, 5, 1, NULL, NULL, 13.5, 750, "Bebida"),#--9
                                                                                                                                                            ("Jugo de naranja", "Vaso de jugo de naranja exprimido", 1200, true, true, 8, 1, NULL, NULL, 0, 300, "Bebida"),#--10
                                                                                                                                                            ("CocaCola chica","Botella de CocaCola de 500ml",1500,true,true,2,1,null,null,0,500,"Bebida"),#--11
                                                                                                                                                            ("Agua mineral", "Botella de agua mineral de 500ml", 800, true, true, 9, 1, NULL, NULL, 0, 500, "Bebida"),#--12


                                                                                                                                                            ("Medialunas de manteca", "Medialunas frescas y esponjosas con un toque de manteca", 1000, false, false, 7, 2, 120, 50, NULL, NULL, "Plato"),#--13
                                                                                                                                                            ("Torta de chocolate", "Bizcochuelo de chocolate con relleno de dulce de leche", 4500, true, false, 1, 2, 420, 1200, NULL, NULL, "Plato"),#--14
                                                                                                                                                            ("Facturas surtidas", "Media docena de facturas variadas", 3500, false, false, 7, 2, 250, 600, NULL, NULL, "Plato"),#--15
                                                                                                                                                            ("Pan de campo", "Pan artesanal horneado al estilo tradicional", 1800, true, false, 7, 2, 150, 500, NULL, NULL, "Plato"),#--16
                                                                                                                                                            ("Cookies con chips de chocolate", "Galletas caseras con chips de chocolate", 2200, true, false, 7, 2, 300, 300, NULL, NULL, "Plato"),#--17
                                                                                                                                                            ("Tarta de frutilla", "Tarta con base de masa sablée, crema pastelera y frutillas frescas", 5200, true, false, 1, 2, 380, 800, NULL, NULL, "Plato"),#--18


                                                                                                                                                            ("Helado de chocolate", "Helado artesanal de chocolate con cacao puro", 1500, true, true, 1, 3, 250, 200, NULL, NULL, "Plato"),#--19
                                                                                                                                                            ("Helado de vainilla", "Helado cremoso de vainilla con un toque de esencia natural", 1400, true, true, 1, 3, 220, 200, NULL, NULL, "Plato"),#--20
                                                                                                                                                            ("Helado de dulce de leche", "Helado artesanal de dulce de leche con salsa extra", 1600, true, true, 1, 3, 270, 200, NULL, NULL, "Plato"),#--21
                                                                                                                                                            ("Helado de frutilla", "Helado fresco de frutilla elaborado con fruta natural", 1400, true, true, 1, 3, 190, 200, NULL, NULL, "Plato"),#--22
                                                                                                                                                            ("Copa helada combinada", "Copa de helado con 3 sabores a elección, salsa y topping", 2800, true, false, 1, 3, 380, 400, NULL, NULL, "Plato"),#--23
                                                                                                                                                            ("Helado vegano de almendras", "Helado vegano elaborado con leche de almendras", 1900, true, true, 1, 3, 200, 200, NULL, NULL, "Plato"),#--24
                                                                                                                                                            ("Helado sin azúcar de limón", "Helado apto para diabéticos con sabor a limón", 1800, true, true, 1, 3, 90, 200, NULL, NULL, "Plato"),#--25
#-- Bebidas (Heladería)
                                                                                                                                                            ("Batido de frutilla", "Batido frío de frutilla con leche", 2000, false, false, 1, 3, NULL, NULL, 0, 500, "Bebida"),#--26
                                                                                                                                                            ("Batido de vainilla", "Batido de helado de vainilla con leche", 2000, false, false, 1, 3, NULL, NULL, 0, 500, "Bebida"),#--27
                                                                                                                                                            ("Batido vegano de chocolate", "Batido helado vegano de chocolate con leche de almendras", 2200, true, true, 1, 3, NULL, NULL, 0, 500, "Bebida");#--28
#--Pago primer pedido
INSERT INTO Pago(monto, metodoDePago, alias, cbu, cuit, pagado) VALUE
                                                                    (37960,"MERCADO PAGO",NULL,NULL,NULL,false);

#--Pago segundo pedido
INSERT INTO Pago(monto, metodoDePago, alias, cbu, cuit, pagado) VALUE
                                                                    (4590,"TRANSFERENCIA",NULL,null,null,false);

#--Pago tercer pedido
INSERT INTO Pago(monto, metodoDePago, alias, cbu, cuit, pagado) VALUES
                                                                    (9000,"EFECTIVO",NULL,NULL,NULL,true);

#--Primer pedido
INSERT INTO Pedido(cliente, estado, pago, vendedor) VALUE
                                                    (1, "RECIBIDO", 1, 1);

INSERT INTO ItemPedido (itemMenu, pedido) VALUES
                                                (1, 1),
                                                (2, 1),
                                                (3, 1);

#--Segundo pedido
INSERT INTO Pedido(cliente, estado, pago, vendedor) VALUE
                                                        (2, "PREPARADO", 2, 3);


INSERT INTO ItemPedido (itemMenu, pedido) VALUES
                                                (19, 2),
                                                (20, 2),
                                                (21, 2);

#--Tercer pedido
INSERT INTO Pedido(cliente, estado, pago, vendedor) VALUE
                                                        (3, "ENVIADO", 3, 2);


INSERT INTO ItemPedido (itemMenu, pedido) VALUES
                                                (13, 3),
                                                (14, 3),
                                                (15, 3);                                                