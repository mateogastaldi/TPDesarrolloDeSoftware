package ejecuciones;

import tp.*;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Etapa2 {
    public static void ejecutar(){

        //Creacion de coordenadas
        Coordenada coordenada1 = new Coordenada(0, 0);
        Coordenada coordenada2 = new Coordenada(-54.807222, -68.304444);
        Coordenada coordenada3 = new Coordenada(-53.783333, -67.7);

        //Creacion de direcciones
        Direccion direccion1 = new Direccion("San martin",1000,"Santa Fe", "Argentina");
        Direccion direccion2 = new Direccion("Belgrano",2000,"Santa Fe", "Argentina");
        Direccion direccion3 = new Direccion("Chaco",3000,"Santa Fe", "Argentina");

        //Creacion de vendedores
        Vendedor vendedor1 = new Vendedor("La Parrilla de Don Julio",direccion1,coordenada1);
        Vendedor vendedor2 = new Vendedor("McDonalds",direccion2,coordenada2);
        Vendedor vendedor3 = new Vendedor("Heladeria Trevi",direccion3,coordenada3);

        //Creacion de clientes
        Cliente cliente1 = new Cliente("Juan Perez", 20123122431L, "juan@frsf.com", direccion1, coordenada1);
        Cliente cliente2 = new Cliente("Jorge Ramirez", 12345627814L, "pedro@frsf.com", direccion2, coordenada2);
        Cliente cliente3 = new Cliente("Alberto Almiron", 98762342103L, "franco@unl.com", direccion3, coordenada3);

        //Creacion de categorias
        Categoria categoria1 = new Categoria("Postres",Plato.class);
        Categoria categoria2 = new Categoria("Entradas", Plato.class);
        Categoria categoria3 = new Categoria("Principales",Plato.class);
        Categoria categoria4 = new Categoria("Vinos", Bebida.class);
        Categoria categoria5 = new Categoria("Cervezas", Bebida.class);
        Categoria categoria6 = new Categoria("Gaseosa", Bebida.class);
        Categoria categoria7 = new Categoria("Agua", Bebida.class);

        //Creacion de Items Menu de Don Julio
        Plato plato1 = new Plato("Milanesa de ternera a la pizza","Milanesa de carne de ternera con salsa de tomate y queso muzzarella",345.65,false,categoria1,vendedor1,900,false,370);
        Plato plato2 = new Plato("Boga Roque","Boga despinada con limon y queso roquefort",335.69,false,categoria3,vendedor1,500,true,250);
        Bebida bebida1 = new Bebida("Pinta Cerveza Santa Fe","500ml de Cerveza rubia Santa Fe",68,true,false,categoria5,vendedor1,5,500);
        Bebida bebida2 = new Bebida( "Agua mineral Bon Aqua","500ml agua mineral no gasificada", 45,true,true,categoria7,vendedor1,0,500);

        //Creacion de Items Menu de McDonalds
        Bebida bebida3 = new Bebida("Vaso grande de Fanta", "Vaso de 700ml de Fanta",44,true,true,categoria6,vendedor2,0,7);
        Plato plato3 = new Plato("TrpleMac","Hamburgues triple con lechuga y salsa Mc",690,false,categoria3,vendedor2,1500,false,45);

        vendedor1.addItemMenu(plato1);
        vendedor1.addItemMenu(plato2);
        vendedor1.addItemMenu(bebida1);
        vendedor1.addItemMenu(bebida2);

        vendedor2.addItemMenu(bebida3);
        vendedor2.addItemMenu(plato3);

        System.out.println("Del vendedor: "+ vendedor1.getNombre().toString());

        System.out.println("Bebidas del vendedor:");
        vendedor1.printBebida();
        System.out.println("Comidas del vendedor:");
        vendedor1.printPlato();
        System.out.println("Comidas veganas del vendedor:");
        vendedor1.printPlatoVegano();
        System.out.println("Bedidas sin alcohol del vendedor");
        vendedor1.printBebidaSinAlcohol();

        System.out.println("------------------------------------------------------------------------------------------------");

        //Printeo del nombre del vendedor 1 y sus listas de Items.

        System.out.println("Del vendedor: "+ vendedor1.getNombre().toString());

        System.out.println("Bebidas del vendedor:");
        vendedor1.printBebida();
        System.out.println("Comidas del vendedor:");
        vendedor1.printPlato();
        System.out.println("Comidas veganas del vendedor:");
        vendedor1.printPlatoVegano();
        System.out.println("Bedidas sin alcohol del vendedor");
        vendedor1.printBebidaSinAlcohol();
    }
}
