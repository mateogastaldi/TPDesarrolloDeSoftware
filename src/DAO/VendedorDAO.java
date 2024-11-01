
package DAO;

import exceptions.VendedorNoEncontradoException;
import tp.Vendedor;

import java.util.List;

public interface VendedorDAO {
    List<Vendedor> getVendedores() throws VendedorNoEncontradoException;
    void addVendedor(Vendedor vendedor) throws VendedorNoEncontradoException;
    List<Vendedor> filtrarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException;
    List<Vendedor> filtrarVendedorPorId(int id) throws VendedorNoEncontradoException;
}
