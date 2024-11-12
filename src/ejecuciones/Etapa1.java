//package ejecuciones;
//
//import tp.*;
//
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Scanner;
//
//public class Etapa1 {
//
//    public static void ejecutar(){
//
//        //Creacion de ArrayLists
//        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
//        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
//
//        //Creacion de coordenadas
//        Coordenada coordenada1 = new Coordenada(0, 0);
//        Coordenada coordenada2 = new Coordenada(-54.807222, -68.304444);
//        Coordenada coordenada3 = new Coordenada(-53.783333, -67.7);
//
//        //Creacion de direcciones
//        Direccion direccion1 = new Direccion("San martin",1000,"Santa Fe", "Argentina");
//        Direccion direccion2 = new Direccion("Belgrano",2000,"Santa Fe", "Argentina");
//        Direccion direccion3 = new Direccion("Chaco",3000,"Santa Fe", "Argentina");
//
//        //Creacion de tres instancias de vendedores y agregar a un arreglo.
//        Vendedor vendedor1 = new Vendedor("Marcelo",direccion1,coordenada1);
//        Vendedor vendedor2 = new Vendedor("Marco",direccion2,coordenada2);
//        Vendedor vendedor3 = new Vendedor("Julio",direccion3,coordenada3);
//
//        Cliente cliente1 = new Cliente("Juan Perez", 20123122431L, "juan@frsf.com", direccion1, coordenada1);
//        Cliente cliente2 = new Cliente("Jorge Ramirez", 12345627814L, "pedro@frsf.com", direccion2, coordenada2);
//        Cliente cliente3 = new Cliente("Alberto Almiron", 98762342103L, "franco@unl.com", direccion3, coordenada3);
//
//        vendedores.add(vendedor1);
//        vendedores.add(vendedor2);
//        vendedores.add(vendedor3);
//
//        clientes.add(cliente1);
//        clientes.add(cliente2);
//        clientes.add(cliente3);
//
//        //Iteracion sobre el arreglo de vendedores para buscar por nombre o por id.
//
//        System.out.println("Buscar vendedor por (1)=Nombre (2)=ID (otro)=Salir");
//        Scanner ven = new Scanner(System.in);
//        int idven = ven.nextInt();
//        if (idven == 1) {
//            System.out.println("Ingrese el nombre: ");
//            Scanner sc1 = new Scanner(System.in);
//            String nombre = sc1.nextLine();
//            for (Vendedor vendedor : vendedores){
//                if(vendedor.nombreIgual(nombre)){
//                    vendedor.printAll();
//                }
//            }
//        } else if (idven == 2) {
//            System.out.println("Ingrese el id: ");
//            Scanner sc2 = new Scanner(System.in);
//            int id = sc2.nextInt();
//            for (Vendedor vendedor : vendedores){
//                if (vendedor.idIgual(id)){
//                    vendedor.printAll();
//                }
//            }
//        }
//
//        System.out.println("--------------------------------------------------");
//
//        //Printeo de la lista de vendedores pre eliminicacion.
//
//        System.out.println("Vendedores en el vector antes de la eliminacion:");
//        for(Vendedor vven : vendedores){
//            vven.printAll();
//        }
//
//        System.out.println("--------------------------------------------------");
//
//        //Iteracion sobre vendedores para eliminar el seleccionado.
//
//        System.out.println("Ingrese el id de un vendedor para eliminarlo de la lista: ");
//        Scanner scid = new Scanner(System.in);
//        int scdelete = scid.nextInt();
//
//        Iterator iv = vendedores.iterator();
//        while(iv.hasNext()){
//            Vendedor v = (Vendedor)iv.next();
//            if(v.idIgual(scdelete)){
//                iv.remove();
//            }
//        }
//
//        System.out.println("--------------------------------------------------");
//
//        //Printeo de la lista de vendedores post eliminicacion.
//
//        System.out.println("Vendedores en el vector despues de la eliminacion:");
//        for(Vendedor vven : vendedores){
//            vven.printAll();
//        }
//
//        System.out.println("--------------------------------------------------");
//
//        //Iteracion sobre el arreglo de clientes para buscar por nombre o por id.
//
//        System.out.println("Buscar cliente por (1)=Nombre (2)=ID (otro)=Salir");
//        Scanner cli = new Scanner(System.in);
//        int idcli = cli.nextInt();
//        if (idcli == 1) {
//            System.out.println("Ingrese el nombre: ");
//            Scanner sc3 = new Scanner(System.in);
//            String nombre = sc3.nextLine();
//            for (Cliente cliente : clientes){
//                if(cliente.nombreIgual(nombre)){
//                    cliente.printAll();
//                }
//            }
//        } else if (idcli == 2) {
//            System.out.println("Ingrese el id: ");
//            Scanner sc4 = new Scanner(System.in);
//            int id = sc4.nextInt();
//            for (Cliente cliente : clientes){
//                if (cliente.idIgual(id)){
//                    cliente.printAll();
//                }
//            }
//        }
//
//        System.out.println("--------------------------------------------------");
//
//        //Printeo de la lista de clientes pre eliminicacion.
//
//        System.out.println("Clientes en el vector antes de la eliminacion:");
//        for(Cliente vcli : clientes){
//            vcli.printAll();
//        }
//
//        System.out.println("--------------------------------------------------");
//
//        //Iteracion sobre clientes para eliminar el seleccionado.
//
//        System.out.println("Ingrese el id de un cliente para eliminarlo de la lista: ");
//        Scanner scid2 = new Scanner(System.in);
//        int scdelete2 = scid2.nextInt();
//
//        Iterator ic = clientes.iterator();
//        while(ic.hasNext()){
//            Cliente c = (Cliente)ic.next();
//            if(c.idIgual(scdelete2)){
//                ic.remove();
//            }
//        }
//
//        System.out.println("--------------------------------------------------");
//
//        //Printeo de la lista de vendedores post eliminicacion.
//
//        System.out.println("Clientes en el vector luego de la eliminacion:");
//        for(Cliente vcli : clientes){
//            vcli.printAll();
//        }
//
//        System.out.println("--------------------------------------------------");
//
//        //Calculo de la distancia entre un vendedor y un cliente.
//
//        System.out.println("Calcular distancia entre un vendedor y un cliente");
//
//        System.out.println("Ingresar ID vendedor: ");
//        Scanner sc5 = new Scanner(System.in);
//        int idv = sc5.nextInt();
//        Vendedor vendedoraux = null;
//        for (Vendedor vendedor : vendedores) if (vendedor.idIgual(idv)) vendedoraux = vendedor;
//
//        System.out.println("Ingresar ID cliente: ");
//        Scanner sc6 = new Scanner(System.in);
//        int idc = sc6.nextInt();
//        Cliente clienteaux = null;
//        for (Cliente cliente: clientes) if (cliente.idIgual(idc)) clienteaux = cliente;
//
//        System.out.println("La distancia es: " + vendedoraux.distancia(clienteaux) + " km");
//
//        System.out.println("--------------------------------------------------");
//    }
//
//}
