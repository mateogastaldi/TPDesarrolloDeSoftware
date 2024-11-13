package controller;

import java.util.List;

import DAO.FACTORY.DAOFactory;
import exceptions.Pedido.PedidoNoEncontradoException;
import exceptions.itemPedido.ItemPedidoNoEncontradoException;
import model.Cliente;
import model.ItemMenu;
import model.ItemPedido;
import model.Pago;
import model.PagoStrategy;
import model.Pedido;
import model.Vendedor;

public class PedidosController {
    // Singleton ---------------------------------------------------------
    private static PedidosController instance;
    private PedidosController() {}
    public static PedidosController getInstance() {
        if (instance == null) {
            instance = new PedidosController();
        }
        return instance;
    }
    // -------------------------------------------------------------------

    // Methods pedidos ---------------------------------------------------
    List<Pedido> getPedido() throws PedidoNoEncontradoException{
        return DAOFactory.getInstance().getPedidosDAO().getPedido();
    }
    void addPedido(Cliente cliente,Vendedor vendedor) throws PedidoNoEncontradoException{
        Pedido pedido = new Pedido(cliente, vendedor);
        DAOFactory.getInstance().getPedidosDAO().addPedido(pedido);
    }
    Pedido filtrarPedidoPorId(int id) throws PedidoNoEncontradoException{
        return DAOFactory.getInstance().getPedidosDAO().filtrarPedidoPorId(id);
    }
    List<Pedido> filtrarPorNombreCliente(String nombre) throws PedidoNoEncontradoException{
        return DAOFactory.getInstance().getPedidosDAO().filtrarPorNombreCliente(nombre);
    }
    List<Pedido> filtrarPorIdCliente(int id) throws PedidoNoEncontradoException{
        return DAOFactory.getInstance().getPedidosDAO().filtrarPorIdCliente(id);
    }
    void eliminarPedido(int id) throws PedidoNoEncontradoException{
        DAOFactory.getInstance().getPedidosDAO().eliminarPedido(id);
    }
    List<Pedido> filtrarPedidoPorVendedor(String vendedor) throws PedidoNoEncontradoException{
        return DAOFactory.getInstance().getPedidosDAO().filtrarPedidoPorVendedor(vendedor);
    }
    // --------------------------------------------------------------------

    // Methods itemPedido --------------------------------------------------
    public void addItemPedido(ItemMenu itemMenu, Pedido pedido) {
        ItemPedido itemPedido = new ItemPedido(itemMenu, pedido);
        DAOFactory.getInstance().getItemsPedidoDAO().addItemPedido(itemPedido);
    }
    public ItemPedido getItemPedido(int id) throws ItemPedidoNoEncontradoException{
        return DAOFactory.getInstance().getItemsPedidoDAO().getItemPedido(id);
    }
    public List<ItemPedido> filtrarPorCliente(String nombreCliente) throws ItemPedidoNoEncontradoException{
        return DAOFactory.getInstance().getItemsPedidoDAO().filtrarPorCliente(nombreCliente);
    }
    public List<ItemPedido> filtrarPorNombreVendedor(String nombreVendedor) throws ItemPedidoNoEncontradoException{
        return DAOFactory.getInstance().getItemsPedidoDAO().filtrarPorNombreVendedor(nombreVendedor);
    }
    public List<ItemPedido> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemPedidoNoEncontradoException{
        return DAOFactory.getInstance().getItemsPedidoDAO().filtrarPorRangoPrecio(precioMin, precioMax);
    }
    public List<ItemPedido> filtrarPorIdVendedor(int idVendedor) throws ItemPedidoNoEncontradoException{
        return DAOFactory.getInstance().getItemsPedidoDAO().filtrarPorIdVendedor(idVendedor);
    }
    public List<ItemPedido> filtrarPorPedido(Pedido pedido){
        return DAOFactory.getInstance().getItemsPedidoDAO().filtrarPorPedido(pedido);
    }
    public List<ItemPedido> getitemsPedidos() throws ItemPedidoNoEncontradoException{
        return DAOFactory.getInstance().getItemsPedidoDAO().getitemsPedidos();
    }
    public void remove(ItemPedido itemPedido) throws ItemPedidoNoEncontradoException{
        DAOFactory.getInstance().getItemsPedidoDAO().remove(itemPedido);
    }
    // --------------------------------------------------------------------

    // Methods Pago--------------------------------------------------------
    public void addPago(PagoStrategy ps, double precioBase) {
        double precio = ps.precio(precioBase);
        Pago pago = new Pago(ps, precio);
        DAOFactory.getInstance().getPagoDAO().addPago(pago);
        
    }
    // --------------------------------------------------------------------
    
}
