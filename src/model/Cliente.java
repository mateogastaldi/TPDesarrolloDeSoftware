package model;

import javax.swing.*;

public class Cliente implements EventListener {
    private static int contadorId = 0;
    private int id;
    private String nombre;
    private long cuit;
    private String email;
    private Direccion direccion;
    private Coordenada coordenadas;

    //constructor
    public Cliente(String nombre, long cuit, String email, Direccion direccion, Coordenada coordenadas) {
        setId();
        setNombre(nombre);
        setCuit(cuit);
        setEmail(email);
        setDireccion(direccion);
        setCoordenadas(coordenadas);
    }

    //getters-setter
    public void setId() {
        this.id = ++contadorId;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCuit(long cuit) {
        this.cuit = cuit;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    public void setCoordenadas(Coordenada coordenadas) {
        this.coordenadas = coordenadas;
    }

    public int getId() {
        return id;
    }
    public long getCuit() {
        return cuit;
    }
    public String getEmail() {
        return email;
    }
    public Direccion getDireccion() {
        return direccion;
    }
    public Coordenada getCoordenadas() {
        return coordenadas;
    }
    public String getNombre() {
        return nombre;
    }

    //metodos
    public void printAll(){
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Cuit: " + getCuit());
        System.out.println("Email: " + getEmail());
        System.out.println("Direccion(" + "Calle:" + this.direccion.getCalle() + ", Altura:" + this.direccion.getAltura() + ", Ciudad:" + this.direccion.getCiudad() + ", Pais:" + this.direccion.getPais()  +")");
        System.out.println("Coordenadas(" + "Lat:" + this.coordenadas.getLat() + ", Lng:" + this.coordenadas.getLng()+")");

    }
    public boolean nombreIgual(String nom){
        return this.getNombre().equalsIgnoreCase(nom);
    }
    public boolean cuitIgual(long cuit){
        return this.getCuit() == cuit;
    }
    public boolean idIgual(int id){
        return this.getId() == id;
    }
    //nose se usa mas este metodo de abajo
//    public Pedido generarPedido(Vendedor vendedor){
//        Pedido pedido = new Pedido(this,vendedor);
//        System.out.println("Los items disponibles del vendedor "+ vendedor.getNombre() + " son: ");
//        vendedor.printAllItemMenu();
//        System.out.println("Elija los productos que desee, poner 0 para finalizar: ");
//        int idProducto = -1;
//
//        while (idProducto == -1) {
//            System.out.print("Ingrese el ID del producto: ");
//            Scanner sc = new Scanner(System.in);
//            idProducto = sc.nextInt();
//
//            if (idProducto != 0) {
//                try {
//                    // Intenta obtener el producto por su ID
//                    ItemMenu item = vendedor.getItemMenu(idProducto);
//
//                    ItemPedido itemPedido = new ItemPedido(item, pedido);
//                    pedido.addItemPedido(itemPedido);
//                    System.out.println("Producto agregado al pedido: " + item.getNombre());
//                    idProducto = -1;
//
//                } catch (ItemPedidoNoEncontradoException e) {
//                    // Captura la excepción si no se encuentra el producto
//                    System.out.println("No existe producto con ese id, ingreselo nuevamente.");
//                    idProducto = -1;
//                }
//            }
//
//        }
//        this.metodoPago(pedido);
//        System.out.println("Pedido generado con éxito.");
//        vendedor.addPedido(pedido);
//
//        return pedido;
//
//    }
//    public void metodoPago(Pedido pedido){
//        System.out.println("Elegir metodo de pago: |  1=Transferencia  |  2=MercadoPago  |  3=Efectivo  |");
//
//        while (true) {
//            Scanner sc = new Scanner(System.in);
//            int i  = sc.nextInt();
//            sc.nextLine();
//
//            if (i == 1) {
//                pedido.pagarTransferencia();
//                pedido.getPago().obtenerInformacion();
//                break;
//            } else if (i == 2) {
//                pedido.pagarMercadoPago();
//                pedido.getPago().obtenerInformacion();
//                break;
//            } else if(i == 3){
//                pedido.pagarEfectivo();
//                pedido.getPago().obtenerInformacion();
//                break;
//            } else {
//                System.out.println("Metodo incorrecto ingrese uno valido.");
//                System.out.println("Elegir metodo de pago:  1=Transferencia  |  2=MercadoPago  |  3=Efectivo");
//            }
//
//        }
//    }
    public void modificarAtributos(String nombre, long cuit, Direccion direccion,String email,Coordenada coordenadas){

        if(!nombre.equals("")) setNombre(nombre);
        if(cuit != -1) setCuit(cuit);
        setDireccion(direccion);
        setCoordenadas(coordenadas);


    }
    @Override
    public void update(Pedido p) {
        if(p.getEstado().equals(TipoEstado.ENVIADO)){
            JOptionPane.showMessageDialog(null, "El pedido se ha actualizado.\n Estado del Pedido:" + p.getEstado().stringEstado() + "\nPago generado");
        }
        else{
            JOptionPane.showMessageDialog(null, "El pedido se ha actualizado.\n Estado del Pedido:" + p.getEstado().stringEstado());
        }
    }

    public void modificarCliente(String nombre, String cuit, String email, Direccion direccion, Coordenada coordenadas){
        setNombre(nombre);
        setCuit(Long.parseLong(cuit));
        setEmail(email);
        setDireccion(direccion);
        setCoordenadas(coordenadas);
    }
}
