package DAO.FACTORY;

import DAO.*;
import memory.*;

public class DAOFactory {
    private static DAOFactory instance;
    private CategoriaDAO categoriaDAO;
    private ClienteDAO clienteDAO;
    private ItemsMenuDAO itemsMenuDAO;
    private ItemPedidoDAO itemsPedidoDAO;
    private PedidosDAO pedidosDAO;
    private VendedorDAO vendedorDAO;

    private DAOFactory() {
        this.categoriaDAO = CategoriaMemory.getInstance();
        this.clienteDAO = ClienteMemory.getInstance();
        this.itemsMenuDAO = ItemsMenuMemory.getInstance();
        this.itemsPedidoDAO = ItemPedidoMemory.getInstance();
        this.pedidosDAO = PedidosMemory.getInstance();
        this.vendedorDAO = VendedorMemory.getInstance();
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
}
