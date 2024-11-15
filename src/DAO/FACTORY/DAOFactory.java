package DAO.FACTORY;

import DAO.*;
import memory.*;
import mySQL.*;
public class DAOFactory {
    private static DAOFactory instance;
    private CategoriaDAO categoriaDAO;
    private ClienteDAO clienteDAO;
    private ItemsMenuDAO itemsMenuDAO;
    private ItemPedidoDAO itemsPedidoDAO;
    private PedidosDAO pedidosDAO;
    private VendedorDAO vendedorDAO;
    private PagoDAO pagoDAO;

    private DAOFactory() {
        this.categoriaDAO = CategoriaMySQL.getInstance();
        this.clienteDAO = ClienteMySQL.getInstance();
        this.itemsMenuDAO = ItemMenuMySQL.getInstance();
        this.itemsPedidoDAO = ItemPedidoMySQL.getInstance();
        this.pedidosDAO = PedidosMySQL.getInstance();
        this.vendedorDAO = VendedorMySQL.getInstance();
        this.pagoDAO = PagoMySQL.getInstance();

    }
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }
    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }
    public ItemsMenuDAO getItemsMenuDAO() {
        return itemsMenuDAO;
    }
    public ItemPedidoDAO getItemsPedidoDAO() {
        return itemsPedidoDAO;
    }
    public PedidosDAO getPedidosDAO() {
        return pedidosDAO;
    }
    public VendedorDAO getVendedorDAO() {
        return vendedorDAO;
    }
    public PagoDAO getPagoDAO() {
        return pagoDAO;
    }
}
