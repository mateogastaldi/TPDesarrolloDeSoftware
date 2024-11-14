
package DAO;

import exceptions.vendedor.VendedorNoEncontradoException;
import model.Vendedor;
import model.Coordenada;
import model.Direccion;

import java.sql.SQLException;
import java.util.List;

public interface VendedorDAO {
    List<Vendedor> getVendedores() throws VendedorNoEncontradoException, SQLException;
    void addVendedor(Vendedor vendedor) throws VendedorNoEncontradoException, SQLException;
    List<Vendedor> filtrarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException, SQLException;
    Vendedor filtrarVendedorPorId(int id) throws VendedorNoEncontradoException, SQLException;
    void eliminarVendedor(int id) throws VendedorNoEncontradoException, SQLException;
    void modificarVendedor(int id, String nombre, Direccion direccion, Coordenada coordenada) throws VendedorNoEncontradoException,SQLException;
}
