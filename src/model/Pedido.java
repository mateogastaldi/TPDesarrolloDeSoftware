package model;

import DAO.FACTORY.DAOFactory;
import controller.ItemMenusController;
import controller.PedidosController;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Pedido extends EventManager {
    // Atributes ----------------------------------------------------------------------------------------
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itemsPedidos;
    private Estado estado;
    private Pago pago;
    private Vendedor vendedor;
    // --------------------------------------------------------------------------------------------------

    // Constructor --------------------------------------------------------------------------------------
    public Pedido(Cliente cliente, Vendedor vendedor, Pago pago, Estado estado) {
        super();
        setCliente(cliente);
        setVendedor(vendedor);
        itemsPedidos = new ArrayList<>();
        if (estado == null)
            this.estado = new EstadoRECIBIDO();
        else
            this.estado = estado;
    }

    public Pedido(Cliente cliente, List<ItemPedido> itemsPedidos, Vendedor vendedor, Pago pago) {
        super();
        setCliente(cliente);
        setItemsPedidos(itemsPedidos);
        this.estado = new EstadoRECIBIDO();
        setVendedor(vendedor);
    }
    // ---------------------------------------------------------------------------------------------------

    // Getters -------------------------------------------------------------------------------------------
    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItemsPedidos() {
        return itemsPedidos;
    }

    public Pago getPago() {
        return this.pago;
    }

    public int getId() {
        return id;
    }

    public Estado getEstado() {
        return estado;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
    // -------------------------------------------------------------------------------------------------

    // Setters -----------------------------------------------------------------------------------------
    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.addEventListener(cliente);
    }

    private void setItemsPedidos(List<ItemPedido> itemsPedidos) {
        this.itemsPedidos = itemsPedidos;
    }

    private void setPago(Pago pago) {
        this.pago = pago;
    }

    public void addItemPedido(ItemPedido itemPedido) {
        itemsPedidos.add(itemPedido);
        DAOFactory.getInstance().getItemsPedidoDAO().addItemPedido(itemPedido);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    // -------------------------------------------------------------------------------------------------

    // Methods -----------------------------------------------------------------------------------------
    public TipoEstado estado() {
        return estado.getEstado();
    }

    public void actualizarEstado() {
        estado.siguiente();
        if (estado.equals(TipoEstado.ENVIADO)) {
            try{
                PedidosController.getInstance().addPago(pago.getMetodoDePago(), pago.getMonto());
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error al realizar el pago");
                try{
                    PedidosController.getInstance().eliminarPedido(id);
                }catch(Exception e2){
                    JOptionPane.showMessageDialog(null, "Error al eliminar el pedido");
                }
            }
            
        }
        this.notifyListeners(this);
    }

    public double calcularPrecioBase() {
        double precioBase = 0;
        for (ItemPedido itemPedido : itemsPedidos) {
            precioBase += itemPedido.getItemMenu().getPrecio();
        }
        return precioBase;
    }

    public double precioTotal() {
        return pago.getMonto();
    }

    public void pagarPagoStrategy(PagoStrategy ps) {
        this.setPago(new Pago(ps, calcularPrecioBase()));
    }

    public void printItemsPedidos() {
        for (ItemPedido itemPedido : itemsPedidos) {
            System.out.println(itemPedido.getItemMenu().getNombre());
        }
    }

    public void printAllPedido() {
        System.out.println("Pedido de ID: " + this.getId());
        this.printItemsPedidos();
        System.out.println(this.cliente.getNombre());
    }

    public void removeItemPedido(ItemPedido itemPedido) {
        
    }

    @Override
    public boolean equals(Object obj) {
        Pedido pedido = (Pedido) obj;
        return pedido.getId() == this.getId();
    }
    // -------------------------------------------------------------------------------------------------
}
