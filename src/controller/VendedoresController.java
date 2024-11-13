package controller;

import java.util.List;

import DAO.FACTORY.DAOFactory;
import exceptions.vendedor.VendedorNoEncontradoException;
import model.Coordenada;
import model.Direccion;
import model.Vendedor;

public class VendedoresController {
    // Singleton -------------------------------------------------------------
    private static VendedoresController instance;
    private VendedoresController() {}
    public static VendedoresController getInstance() {
        if (instance == null) {
            instance = new VendedoresController();
        }
        return instance;
    }
    // ------------------------------------------------------------------------

    // Methods Vendedor ------------------------------------------------------
    public List<Vendedor> getVendedores() throws VendedorNoEncontradoException {
        return DAOFactory.getInstance().getVendedorDAO().getVendedores();
    }

    public void addVendedor(String nombre,String calle,int altura,String ciudad,String pais,double lat,double lng) throws VendedorNoEncontradoException {
        Direccion direccion = new Direccion(calle, altura, ciudad, pais);
        Coordenada coordenada = new Coordenada(lat, lng);
        Vendedor vendedor = new Vendedor(nombre, direccion, coordenada);
        DAOFactory.getInstance().getVendedorDAO().addVendedor(vendedor);
    }

    public List<Vendedor> filtrarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException {
        return DAOFactory.getInstance().getVendedorDAO().filtrarVendedorPorNombre(nombre);
    }

    public Vendedor filtrarVendedorPorId(int id) throws VendedorNoEncontradoException {
        return DAOFactory.getInstance().getVendedorDAO().filtrarVendedorPorId(id);
    }

    public void eliminarVendedor(int id) throws VendedorNoEncontradoException {
        DAOFactory.getInstance().getVendedorDAO().eliminarVendedor(id);
    }

    public void modificarVendedor(int id, String nombre, String calle, int altura, String ciudad, String pais, double lat, double lng) throws VendedorNoEncontradoException {
        Direccion direccion = new Direccion(calle, altura, ciudad, pais);
        Coordenada coordenada = new Coordenada(lat, lng);
        DAOFactory.getInstance().getVendedorDAO().modificarVendedor(id, nombre, direccion, coordenada);
    }
    // ------------------------------------------------------------------------
}