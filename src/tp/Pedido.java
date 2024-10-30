package tp;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends EventManager {
    private static int contadorId = 0;
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itemsPedidos;
    private Estado estado;
    private PagoStrategy metodoPago;

    //constructores
    public Pedido(Cliente cliente) {
        super();
        setCliente(cliente);
        itemsPedidos = new ArrayList<>();
        this.estado = new EstadoRECIBIDO();
        setId();
    }
    public Pedido(Cliente cliente, List<ItemPedido> itemsPedidos) {
        super();
        setCliente(cliente);
        setItemsPedidos(itemsPedidos);
        this.estado = new EstadoRECIBIDO();
        setId();
    }

    //getters-setters
    public Cliente getCliente() {return cliente;}
    public List<ItemPedido> getItemsPedidos() {return itemsPedidos;}
    public PagoStrategy getMetodoPago(){return this.metodoPago;}
    public int getId() {return id;}
    public Estado getEstado() {return estado;}

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.addEventListener(cliente);
    }
    private void setItemsPedidos (List<ItemPedido> itemsPedidos) {this.itemsPedidos = itemsPedidos;}
    private void setMetodoPago(PagoStrategy metodoPago) {this.metodoPago = metodoPago;}
    public void addItemPedido(ItemPedido itemPedido) {
        itemsPedidos.add(itemPedido);
        ItemPedidoMemory.getInstance().addItemPedido(itemPedido);
    }
    private void setId(){this.id = ++contadorId;}

    //metodos
    public TipoEstado estado(){return estado.getEstado();}
    public void actualizarEstado() {
        estado.siguiente();
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
        return metodoPago.precio(this.calcularPrecioBase());
    }

    public void pagarEfectivo(){
        this.setMetodoPago(new Efectivo());
   }
    public void pagarMercadoPago(){this.setMetodoPago(new MercadoPago());}
    public void pagarTransferencia(){
        this.setMetodoPago(new Transferencia());
    }
    public void printItemsPedidos(){
        for(ItemPedido itemPedido : itemsPedidos){
            System.out.println(itemPedido.getItemMenu().getNombre());
        }
    }
    public void printAllPedido(){
        System.out.println("Pedido de ID: "+this.getId());
        this.printItemsPedidos();
        System.out.println(this.cliente.getNombre());
    }


}
