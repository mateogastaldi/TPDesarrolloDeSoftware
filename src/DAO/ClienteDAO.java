
package DAO;

import java.util.List;
import exceptions.cliente.ClienteNoEncontradoException;
import model.Cliente;
import model.Coordenada;
import model.Direccion;

public interface ClienteDAO {



    void addCliente(Cliente cliente) throws ClienteNoEncontradoException;
    List<Cliente> filtrarClientePorNombre(String nombre) throws ClienteNoEncontradoException;
    Cliente filtrarClientePorId(int id) throws ClienteNoEncontradoException;
    List<Cliente> getClientes() throws ClienteNoEncontradoException;
    void eliminarCliente(int id) throws ClienteNoEncontradoException;
    void modificarCliente(int id, String nombre, String cuit, String email, Direccion direccion, Coordenada coordenadas) throws ClienteNoEncontradoException;
}