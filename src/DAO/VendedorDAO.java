
package DAO;

import exceptions.vendedor.VendedorNoEncontradoException;
import tp.Vendedor;

import java.util.List;

public interface VendedorDAO {
    List<Vendedor> getVendedores() throws VendedorNoEncontradoException;
    void addVendedor(Vendedor vendedor) throws VendedorNoEncontradoException;
    List<Vendedor> filtrarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException;
    Vendedor filtrarVendedorPorId(int id) throws VendedorNoEncontradoException;
    void eliminarVendedor(int id) throws VendedorNoEncontradoException;

}
