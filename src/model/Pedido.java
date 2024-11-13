package model;

import DAO.FACTORY.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends EventManager {
    private static int contadorId = 0;
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itemsPedidos;
    private Estado estado;
    private Pago pago;
    private Vendedor vendedor;

    //constructores
    public Pedido(Cliente cliente,Vendedor vendedor) {
        super();
        setCliente(cliente);
        setVendedor(vendedor);
        itemsPedidos = new ArrayList<>();
        this.estado = new EstadoRECIBIDO();
        setId();
    }
    public Pedido(Cliente cliente, List<ItemPedido> itemsPedidos,Vendedor vendedor) {
        super();
        setCliente(cliente);
        setItemsPedidos(itemsPedidos);
        this.estado = new EstadoRECIBIDO();
        setVendedor(vendedor);
        setId();
    }

    //getters-setters
    public Cliente getCliente() {return cliente;}
    public List<ItemPedido> getItemsPedidos() {return itemsPedidos;}
    public Pago getPago(){return this.pago;}
    public int getId() {return id;}
    public Estado getEstado() {return estado;}
    public Vendedor getVendedor() {return vendedor;}

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.addEventListener(cliente);
    }
    private void setItemsPedidos (List<ItemPedido> itemsPedidos) {this.itemsPedidos = itemsPedidos;}
    private void setPago(Pago pago) {this.pago = pago;}
    public void addItemPedido(ItemPedido itemPedido) {
        itemsPedidos.add(itemPedido);
        DAOFactory.getInstance().getItemsPedidoDAO().addItemPedido(itemPedido);
    }
    private void setId(){this.id = ++contadorId;}
    public void setVendedor(Vendedor vendedor) {this.vendedor = vendedor;}

    //metodos
    public TipoEstado estado(){return estado.getEstado();}
    public void actualizarEstado() {
        estado.siguiente();
        if(estado.equals(TipoEstado.ENVIADO)) {
            DAOFactory.getInstance().getPagoDAO().addPago(pago);
        }
        this.notifyListeners(this);
    }

    public double calcularPrecioBase(){
        double precioBase = 0;
        for(ItemPedido itemPedido : itemsPedidos){
            precioBase += itemPedido.getItemMenu().getPrecio();
        }
        return precioBase;
   }
    public double precioTotal(){
        return pago.getMonto();
    }

    public void pagarPagoStrategy(PagoStrategy ps){
        this.setPago(new Pago(ps, calcularPrecioBase()));
   }

    public void printItemsPedidos(){
        for(ItemPedido itemPedido : itemsPedidos){
            System.out.println(itemPedido.getItemMenu().getNombre());
        }}
    public void printAllPedido(){
        System.out.println("Pedido de ID: "+this.getId());
        this.printItemsPedidos();
        System.out.println(this.cliente.getNombre());
    }

    public void removeItemPedido(ItemPedido itemPedido){
        itemsPedidos.remove(itemPedido);
        DAOFactory.getInstance().getItemsPedidoDAO().remove(itemPedido);
    }

    @Override
    public boolean equals(Object obj) {
        Pedido pedido = (Pedido) obj;
        return pedido.getId() == this.getId();
    }
}
