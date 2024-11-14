
package model;
import exceptions.itemPedido.ItemPedidoNoEncontradoException;
import exceptions.vendedor.VendedorNoEncontradoException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vendedor {
    // Atributes ---------------------------------------------------------------
    private static int contadorId;
    private int id;
    private String nombre;
    private Direccion direccion;
    private Coordenada coordenadas;
    private ArrayList<ItemMenu> itemMenu;
    private ArrayList<Pedido> pedidos;
    // -------------------------------------------------------------------------
    // Constructor -------------------------------------------------------------
    public Vendedor(String nombre, Direccion direccion, Coordenada coordenadas) {
        setId();
        setNombre(nombre);
        setDireccion(direccion);
        setCoordenadas(coordenadas);
        setItemMenu();
        setPedidos();
    }
    public Vendedor(String nombre, Coordenada coordenadas, ArrayList<ItemMenu> itemMenu, ArrayList<Pedido> pedidos) {
        setId();
        setNombre(nombre);
        setCoordenadas(coordenadas);
        setItemMenu(itemMenu);
        setPedidos(pedidos);
    }
    // -------------------------------------------------------------------------
    // Getter-Setter -----------------------------------------------------------
    public ArrayList<Bebida> getItemBebida(){
        ArrayList<Bebida> bebidas = new ArrayList<>();
        for(ItemMenu b: this.itemMenu) if(b.esBebida()) bebidas.add((Bebida)b);
        return bebidas; 
    }
    public ArrayList<Bebida> getItemBebidasSinAlcohol(){
        ArrayList<Bebida> bebidasSinAlcohol = new ArrayList<>();
        for(ItemMenu b: this.itemMenu) if(b.esBebida() && ((Bebida)b).getGraduacionAlcoholica() == 0.0 ) bebidasSinAlcohol.add((Bebida)b);
        return bebidasSinAlcohol;
    }
    public ArrayList<Plato> getItemComida(){
        ArrayList<Plato> platos = new ArrayList<>();
        for(ItemMenu p: this.itemMenu) if(p.esComida()) platos.add((Plato)p);
        return platos;
    }
    public ArrayList<Plato> getItemComidaVegana(){
       ArrayList<Plato> platosVeganos = new ArrayList<>();
       for(ItemMenu p: this.itemMenu) if(p.esComida() && ((Plato)p).aptoVegano()) platosVeganos.add((Plato)p);
       return platosVeganos; 
    }
    public Direccion getDireccion() {
        return direccion;
    }
    public Coordenada getCoordenadas() {
        return coordenadas;
    }
    public int getId() {
        return this.id;
    }
    public String getNombre() {
        return nombre;
    }
    public ArrayList<ItemMenu> getItemMenu(){return this.itemMenu;}
    public ArrayList<Pedido> getPedidos(){return this.pedidos;}

    public ItemMenu getItemMenu(int id) throws ItemPedidoNoEncontradoException {
        for(ItemMenu item: this.itemMenu) if(item.getId() == id) return item;
        throw new ItemPedidoNoEncontradoException("no encontrado");
    }

    private void setId() {this.id = ++contadorId;}
    private void setNombre(String nombre) {this.nombre = nombre;}
    private void setDireccion(Direccion direccion) {this.direccion = direccion;}
    private void setCoordenadas(Coordenada coordenadas) {this.coordenadas = coordenadas;}
    private void setItemMenu(ArrayList<ItemMenu> itemMenu){this.itemMenu = itemMenu;}
    private void setItemMenu() {this.itemMenu = new ArrayList<>();}
    private void setPedidos(){this.pedidos = new ArrayList<>();}
    private void setPedidos(ArrayList<Pedido> pedidos){this.pedidos = pedidos;}
    // -------------------------------------------------------------------------
    // Methods ----------------------------------------------------------------
    public void addItemMenu(ItemMenu itemMenu){this.itemMenu.add(itemMenu);}
    public void addPedido(Pedido pedido){this.pedidos.add(pedido);}

    //metodos
    public void printAll(){
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Direccion(" + "Calle:" + this.direccion.getCalle() + ", Altura:" + this.direccion.getAltura() + ", Ciudad:" + this.direccion.getCiudad() + ", Pais:" + this.direccion.getPais()  +")");
        System.out.println("Coordenadas(" + "Lat:" + this.coordenadas.getLat() + " ,Lng:" + this.coordenadas.getLng()+")");
    }
    public void printAllItemMenu(){for(ItemMenu item: this.itemMenu){
            item.printItemMenu();
        }}
    public void printBebida(){
        ArrayList<Bebida> bebidas = this.getItemBebida();
        if(bebidas.isEmpty()) System.out.println("No hay bebidas");
        else for(Bebida b : bebidas) b.printItemMenu();
    }
    public void printBebidaSinAlcohol(){
        ArrayList<Bebida> bebidas = this.getItemBebidasSinAlcohol();
        if(bebidas.isEmpty()) System.out.println("No hay bebidas sin alcohol");
        else for(Bebida b : bebidas) b.printItemMenu();
    }
    public void printPlato(){
        ArrayList<Plato> platos = this.getItemComida();
        if(platos.isEmpty()) System.out.println("No dispone de platos");
        else for(Plato p : platos) p.printItemMenu();
    }
    public void printPlatoVegano(){
        ArrayList<Plato> platosVeganos = this.getItemComidaVegana();
        if(platosVeganos.isEmpty()) System.out.println("No dispone de comida vegana");
        else for(Plato p : platosVeganos) p.printItemMenu();
    }
    public ArrayList<Pedido> filtrarPedidosPorEstado(TipoEstado estado){
        ArrayList<Pedido> pedidosFiltrados = new ArrayList<>();
        for(Pedido p : this.pedidos){
            if(p.getEstado().getEstado().equals(estado)) pedidosFiltrados.add(p);
        }
        return pedidosFiltrados;
    }
    public void cambiarEstadoPedido(TipoEstado e){
       ArrayList<Pedido> pedidosFiltrados = this.filtrarPedidosPorEstado(e);
       if (pedidosFiltrados.isEmpty()) System.out.println("No hay pedidos encontrados");
       for(Pedido p : pedidosFiltrados){
           p.printAllPedido();
       }
        Scanner scanner = new Scanner(System.in);
       boolean bandera = true;
        while(bandera){
            System.out.println("Ingrese el id del pedido que se desea cambiar el estado:");

            int id_ingreso = scanner.nextInt();
            for(Pedido p : pedidosFiltrados) {
                if(p.getId() == id_ingreso) {
                    p.actualizarEstado();
                    bandera = false;
                    break;
                }
            }
            if(bandera){
                System.out.println("No se ingreso un id correcto. Ingreselo nuevamente.");
            }

        }

    }
    public void printAllPedidosPorEstado(TipoEstado e){
        for(Pedido p : this.pedidos){
            p.printAllPedido();
        }
    }



    // Metodo para calcular la distancia entre el Vendedor y un Cliente usando la formula de Haversine
    public double distancia(Cliente cliente) {
        
        int r = 6371;// radio de la tierra en km
        
        double lat1 = this.coordenadas.getLat();
        double lon1 = this.coordenadas.getLng();
        double lat2 = cliente.getCoordenadas().getLat();
        double lon2 = cliente.getCoordenadas().getLng();
       
        double difLat = Math.toRadians(lat2 - lat1);
        double difLon = Math.toRadians(lon2 - lon1);
        
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        
        // formula de Haversine
        double a = Math.sin(difLat / 2) * Math.sin(difLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(difLon / 2) * Math.sin(difLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return r * c;// Distancia en km
        }
    public boolean nombreIgual(String nom){return this.getNombre().equalsIgnoreCase(nom);}
    public boolean idIgual(int id){return this.getId() == id;}

    public void editar(String nombre, Coordenada coordenadas, ArrayList<ItemMenu> itemMenu, ArrayList<Pedido> pedidos){
        if(nombre != null){ setNombre(nombre); }
        if(coordenadas != null){ setCoordenadas(coordenadas); }
        if(itemMenu != null){ setItemMenu(itemMenu); }
        if(pedidos != null){ setPedidos(pedidos); }
    }


    public void modificarVendedor(String nombre, Direccion direccion, Coordenada coordenada){
        setNombre(nombre);
        setDireccion(direccion);
        setCoordenadas(coordenada);
    }

    }
