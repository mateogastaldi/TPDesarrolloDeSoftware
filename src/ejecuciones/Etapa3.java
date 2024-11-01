package ejecuciones;

import memory.ItemPedidoMemory;
import tp.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Etapa3 {
    public static void ejecutar() {

        //Creacion de coordenadas
        Coordenada coordenada1 = new Coordenada(0, 0);
        Coordenada coordenada2 = new Coordenada(-54.807222, -68.304444);
        Coordenada coordenada3 = new Coordenada(-53.783333, -67.7);

        //Creacion de direcciones
        Direccion direccion1 = new Direccion("San martin", 1000, "Santa Fe", "Argentina");
        Direccion direccion2 = new Direccion("Belgrano", 2000, "Santa Fe", "Argentina");
        Direccion direccion3 = new Direccion("Chaco", 3000, "Santa Fe", "Argentina");

        //Creacion de vendedores
        Vendedor vendedor1 = new Vendedor("La Parrilla de Don Julio", direccion1, coordenada1);
        Vendedor vendedor2 = new Vendedor( "McDonalds", direccion2, coordenada2);
        Vendedor vendedor3 = new Vendedor( "Heladeria Trevi", direccion3, coordenada3);

        //Creacion de clientes
        Cliente cliente1 = new Cliente("Juan Perez", 20123122431L, "juan@frsf.com", direccion1, coordenada1);
        Cliente cliente2 = new Cliente("Jorge Ramirez", 12345627814L, "pedro@frsf.com", direccion2, coordenada2);
        Cliente cliente3 = new Cliente("Alberto Almiron", 98762342103L, "franco@unl.com", direccion3, coordenada3);

        //Creacion de categorias
        Categoria categoria1 = new Categoria("Postres", Plato.class);
        Categoria categoria2 = new Categoria( "Entradas", Plato.class);
        Categoria categoria3 = new Categoria("Principales", Plato.class);
        Categoria categoria4 = new Categoria("Vinos", Bebida.class);
        Categoria categoria5 = new Categoria("Cervezas", Bebida.class);
        Categoria categoria6 = new Categoria("Gaseosa", Bebida.class);
        Categoria categoria7 = new Categoria("Agua", Bebida.class);

        //Creacion de Items Menu de Don Julio
        Plato plato1 = new Plato("Milanesa de ternera a la pizza", "Milanesa de carne de ternera con salsa de tomate y queso muzzarella", 345.65, false, categoria1, vendedor1, 900, false, 370);
        Plato plato2 = new Plato("Boga Roque", "Boga despinada con limon y queso roquefort", 335.69, false, categoria3, vendedor1, 500, true, 250);
        Bebida bebida1 = new Bebida("Pinta Cerveza Santa Fe", "500ml de Cerveza rubia Santa Fe", 68, true, false, categoria5, vendedor1, 5, 500);
        Bebida bebida2 = new Bebida("Agua mineral Bon Aqua", "500ml agua mineral no gasificada", 45, true, true, categoria7, vendedor1, 0, 500);
        Bebida bebida4 = new Bebida("CocaCola Zero", "CocaCola cero azucares",2500,true,true,categoria6,vendedor1,0,500);
        Plato plato5 = new Plato("Bife de Chorizo a la parrilla", "Bife de ternera cocinado a la parrilla", 10000,false,categoria3,vendedor1,800,false,400);
        Bebida bebida5 = new Bebida("Vino Malbec Toro", "Vino malbec",7000,true,true,categoria4,vendedor1,0,500);
        Plato postre = new Plato("Flan con ddl","Flan casero con dulce de leche", 2500,false,categoria1,vendedor1,1200,false,100);

        vendedor1.addItemMenu(plato1);
        vendedor1.addItemMenu(plato2);
        vendedor1.addItemMenu(bebida1);
        vendedor1.addItemMenu(bebida2);
        vendedor1.addItemMenu(bebida4);
        vendedor1.addItemMenu(bebida5);
        vendedor1.addItemMenu(plato5);
        vendedor1.addItemMenu(postre);

        //Creacion de Items Menu de McDonalds
        Bebida bebida3 = new Bebida("Vaso grande de Fanta", "Vaso de 700ml de Fanta", 44, true, true, categoria6, vendedor2, 0, 7);
        Plato plato3 = new Plato("TripleMac", "Hamburgues triple con lechuga y salsa Mc", 690, false, categoria3, vendedor2, 1500, false, 45);
        Plato plato4 = new Plato("McFlury Oreo","Helado de vainilla con trozos de oreo",1300,false,categoria1,vendedor2,1500,false,250);

        vendedor2.addItemMenu(bebida3);
        vendedor2.addItemMenu(plato3);
        vendedor2.addItemMenu(plato4);


        //Creacion de Items Menu Heladeria Trevi
        Plato cuartoTrevi = new Plato("Pote 1/4Kg","Pote de un cuarto kilo de helado", 1500, false,categoria1,vendedor3,900,false,250);
        Plato medioTrevi = new Plato("Pote 1/2Kg","Pote de medio kilo de helado", 2500, false,categoria1,vendedor3,1800,false,500);
        Plato unKiloTrevi = new Plato("Pote 1Kg","Pote de un kilo de helado", 4000, false,categoria1,vendedor3,3000,false,1000);

        vendedor3.addItemMenu(cuartoTrevi);
        vendedor3.addItemMenu(medioTrevi);
        vendedor3.addItemMenu(unKiloTrevi);

        //Creacion de pedidos
        Pedido pedido1 = new Pedido(cliente1);
        ItemPedido itemPedido1 = new ItemPedido(plato1, pedido1);
        ItemPedido itemPedido2 = new ItemPedido(plato2, pedido1);
        ItemPedido itemPedido3 = new ItemPedido(plato3, pedido1);
        ItemPedido itemPedido4 = new ItemPedido(bebida1, pedido1);
        ItemPedido itemPedido5 = new ItemPedido(bebida2, pedido1);
        ItemPedido itemPedido6 = new ItemPedido(bebida3, pedido1);

        pedido1.addItemPedido(itemPedido1);
        pedido1.addItemPedido(itemPedido2);
        pedido1.addItemPedido(itemPedido3);
        pedido1.addItemPedido(itemPedido4);
        pedido1.addItemPedido(itemPedido5);
        pedido1.addItemPedido(itemPedido6);

        Pedido pedido2 = new Pedido(cliente2);
        ItemPedido itemPedido7 = new ItemPedido(plato1, pedido2);
        ItemPedido itemPedido8 = new ItemPedido(plato2, pedido2);
        ItemPedido itemPedido9 = new ItemPedido(bebida1, pedido2);
        ItemPedido itemPedido10 = new ItemPedido(bebida2, pedido2);

        pedido2.addItemPedido(itemPedido7);
        pedido2.addItemPedido(itemPedido8);
        pedido2.addItemPedido(itemPedido9);
        pedido2.addItemPedido(itemPedido10);

        Pedido pedido3 = new Pedido(cliente3);
        ItemPedido itemPedido11 = new ItemPedido(plato1, pedido3);
        ItemPedido itemPedido12 = new ItemPedido(bebida1, pedido3);
        ItemPedido itemPedido13 = new ItemPedido(plato5,pedido3);
        ItemPedido itemPedido14 = new ItemPedido(postre,pedido3);
        ItemPedido itemPedido15 = new ItemPedido(bebida5,pedido3);
        ItemPedido itemPedido16 = new ItemPedido(bebida4,pedido3);
        ItemPedido itemPedido17 = new ItemPedido(plato4,pedido3);

        pedido3.addItemPedido(itemPedido11);
        pedido3.addItemPedido(itemPedido12);
        pedido3.addItemPedido(itemPedido13);
        pedido3.addItemPedido(itemPedido14);
        pedido3.addItemPedido(itemPedido15);
        pedido3.addItemPedido(itemPedido16);
        pedido3.addItemPedido(itemPedido17);

        //Printeo de los pedidos en la lista de ItemPedidoMemory antes de ser filtrados
        System.out.println("Items pedidos antes de ser filtrados: ");
        //ItemPedidoMemory.getInstance().printPedidos();

        //Filtra e imprime el nombre de los items cuyo cliente tenga el nombre ingresado
        System.out.println("Filtrado por nombre del cliente\nIngrese un nombre del cliente: ");
        Scanner sc1 = new Scanner(System.in);
        String nombreCliente = sc1.nextLine();
        Iterator<ItemPedido> i = ItemPedidoMemory.getInstance().filtrarPorCliente(nombreCliente).iterator();
        while (i.hasNext()) {System.out.print(i.next().getItemMenu().getNombre() + ", ");}

        //Filtra e imprime el nombre de los items cuyo vendedor tenga el nombre ingresado
        System.out.println("Filtrado por nombre de restaurante\nIngrese el nombre del restaurante : ");
        Scanner sc2 = new Scanner(System.in);
        String nombreVendedor = sc2.nextLine();
        Iterator<ItemPedido> it = ItemPedidoMemory.getInstance().filtrarPorNombreVendedor(nombreVendedor).iterator();
        while (it.hasNext()) {System.out.print(it.next().getItemMenu().getNombre() + ", ");}

        //Filtra e imprime el nombre de los items cuyo vendedor tenga el id ingresado
        System.out.println("Filtrado por id restaurante\nIngrese el id del restaurante: ");
        Scanner sc3 = new Scanner(System.in);
        int idVendedor = sc3.nextInt();
        Iterator<ItemPedido> k = ItemPedidoMemory.getInstance().filtrarPorIdVendedor(idVendedor).iterator();
        while (k.hasNext()) {System.out.print(k.next().getItemMenu().getNombre() + ", ");}

        //Filtra e imprime el nombre de los items cuyo precio se encuentre entre los valores ingresados
        System.out.println("Filtrado por precio\n Ingrese un precio minimo: ");
        Scanner sc4 = new Scanner(System.in);
        double precioMin = sc4.nextDouble();
        System.out.println("Ingrese un precio maximo");
        Scanner sc5 = new Scanner(System.in);
        double precioMax = sc5.nextDouble();
        Iterator<ItemPedido> n = ItemPedidoMemory.getInstance().filtrarPorRangoPrecio(precioMin,precioMax).iterator();
        while (n.hasNext()) {System.out.print(n.next().getItemMenu().getNombre() + ", ");}
    }
}
