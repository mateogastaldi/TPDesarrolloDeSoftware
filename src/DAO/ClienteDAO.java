
package DAO;

import java.sql.SQLException;
import java.util.List;
import exceptions.cliente.ClienteNoEncontradoException;
import model.Cliente;
import model.Coordenada;
import model.Direccion;

public interface ClienteDAO {



    void addCliente(Cliente cliente) throws ClienteNoEncontradoException;
    List<Cliente> filtrarClientePorNombre(String nombre) throws ClienteNoEncontradoException , SQLException;
    Cliente filtrarClientePorId(int id) throws ClienteNoEncontradoException , SQLException;
    List<Cliente> getClientes() throws ClienteNoEncontradoException , SQLException;
    void eliminarCliente(int id) throws ClienteNoEncontradoException, SQLException;
    void modificarCliente(int id, String nombre, String cuit, String email, Direccion direccion, Coordenada coordenadas) throws ClienteNoEncontradoException;
}