
package DAO;

import java.util.List;
import exceptions.ClienteNoEncontradoException;
import tp.Cliente;

public interface ClienteDAO {

    void addCliente(Cliente cliente) throws ClienteNoEncontradoException;
    List<Cliente> filtrarClientePorNombre(String nombre) throws ClienteNoEncontradoException;
    Cliente filtrarClientePorId(int id) throws ClienteNoEncontradoException;
    List<Cliente> getClientes() throws ClienteNoEncontradoException;
    void eliminarCliente(int id) throws ClienteNoEncontradoException;
}
