package controller;

import java.sql.SQLException;
import java.util.List;

import DAO.FACTORY.DAOFactory;
import exceptions.Pedido.PedidoNoEncontradoException;
import exceptions.itemPedido.ItemPedidoNoEncontradoException;
import model.*;

public class PedidosController {
    // Singleton --------------------------------------------------------------------------------------
    private static PedidosController instance;
    private PedidosController() {}
    public static PedidosController getInstance() {
        if (instance == null) {
            instance = new PedidosController();
        }
        return instance;
    }
    // ------------------------------------------------------------------------------------------------

    // Metodos ----------------------------------------------------------------------------------------
    public List<Pedido> getPedido() throws PedidoNoEncontradoException,SQLException{
        return DAOFactory.getInstance().getPedidosDAO().getPedido();
    }
    public Pedido addPedido(Cliente cliente,Vendedor vendedor,Pago pago) throws PedidoNoEncontradoException,SQLException{
        Pedido pedido = new Pedido(cliente, vendedor,pago,null);
        DAOFactory.getInstance().getPedidosDAO().addPedido(pedido);
        return pedido;
    }
    public void addPedido(Cliente cliente,Vendedor vendedor,List<ItemPedido> itemsPedido,Pago pago) throws PedidoNoEncontradoException,SQLException{
        Pedido pedido = new Pedido(cliente,itemsPedido, vendedor,pago);
        DAOFactory.getInstance().getPedidosDAO().addPedido(pedido);
    }

    public Pedido filtrarPedidoPorId(int id) throws PedidoNoEncontradoException,SQLException{
        return DAOFactory.getInstance().getPedidosDAO().filtrarPedidoPorId(id);
    }
    public List<Pedido> filtrarPorNombreCliente(String nombre) throws PedidoNoEncontradoException,SQLException{
        return DAOFactory.getInstance().getPedidosDAO().filtrarPorNombreCliente(nombre);
    }
    public List<Pedido> filtrarPorIdCliente(int id) throws PedidoNoEncontradoException,SQLException{
        return DAOFactory.getInstance().getPedidosDAO().filtrarPorIdCliente(id);
    }
    public void eliminarPedido(int id) throws PedidoNoEncontradoException,SQLException{
        DAOFactory.getInstance().getPedidosDAO().eliminarPedido(id);
    }
    public List<Pedido> filtrarPedidoPorVendedor(String vendedor) throws PedidoNoEncontradoException,SQLException{
        return DAOFactory.getInstance().getPedidosDAO().filtrarPedidoPorVendedor(vendedor);
    }
    // --------------------------------------------------------------------

    // Methods itemPedido --------------------------------------------------
    public void addItemPedido(ItemMenu itemMenu, Pedido pedido) throws ItemPedidoNoEncontradoException,SQLException{
        ItemPedido itemPedido = new ItemPedido(itemMenu, pedido);
        DAOFactory.getInstance().getItemsPedidoDAO().addItemPedido(itemPedido);
    }
    public ItemPedido getItemPedido(int id) throws ItemPedidoNoEncontradoException,SQLException{
        return DAOFactory.getInstance().getItemsPedidoDAO().getItemPedido(id);
    }
    public List<ItemPedido> filtrarPorPedido(Pedido pedido) throws ItemPedidoNoEncontradoException,SQLException{
        return DAOFactory.getInstance().getItemsPedidoDAO().filtrarPorPedido(pedido);
    }
    public List<ItemPedido> getitemsPedidos() throws ItemPedidoNoEncontradoException,SQLException{
        return DAOFactory.getInstance().getItemsPedidoDAO().getItemsPedidos();
    }
    public void remove(ItemPedido itemPedido) throws ItemPedidoNoEncontradoException,SQLException{
        DAOFactory.getInstance().getItemsPedidoDAO().remove(itemPedido);
    }
    // --------------------------------------------------------------------

    // Methods Pago--------------------------------------------------------
    public Pago addPago(PagoStrategy ps, double precioBase) throws SQLException{
        double precio = ps.precio(precioBase);
        Pago pago = new Pago(ps, precio,false,null,0,null);
        DAOFactory.getInstance().getPagoDAO().addPago(pago);
        return pago;
        
    }

    public void addInformacionPago(Pago p ) throws SQLException{
        DAOFactory.getInstance().getPagoDAO().addInfo(p);
    }
    public void cambioEstado(Pedido p) throws SQLException{
        DAOFactory.getInstance().getPedidosDAO().cambioEstado(p);
    }
    // --------------------------------------------------------------------
    
}
