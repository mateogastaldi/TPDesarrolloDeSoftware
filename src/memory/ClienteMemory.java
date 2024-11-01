
package memory;

import java.util.ArrayList;
import java.util.List;
import tp.Cliente;
import java.util.stream.Collectors;
import exceptions.ClienteNoEncontradoException;
import DAO.ClienteDAO;

public class ClienteMemory implements ClienteDAO {
    //UTILIZAMOS PATRON SINGLETON PARA LA CREACION DEL MEMORY
    private static ClienteMemory CLIENTEMEMORY_INSTANCE;
    private List<Cliente> clientes;
    
     //constructores
    private ClienteMemory(){
        clientes = new ArrayList<>();
    }
    private ClienteMemory(List<Cliente> clientes) {
        setClientes(clientes);
    }
    
    //getters
    public List<Cliente> getClientes(){
        return this.clientes;
    }
    public static ClienteMemory getInstance() {
        if(CLIENTEMEMORY_INSTANCE == null) {
            CLIENTEMEMORY_INSTANCE = new ClienteMemory();
        }
        return CLIENTEMEMORY_INSTANCE;
    }
    //setters
    private void setClientes(List<Cliente> clientes){
        this.clientes = clientes;
    }
    
    //metodos
    public void addCliente(Cliente c){
        this.clientes.add(c);
    }
    
    @Override
    public List<Cliente> filtrarClientePorNombre(String nombre) throws ClienteNoEncontradoException{
        
        List<Cliente> clientesFiltrados = clientes.stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
        
        if(clientesFiltrados.isEmpty()){
            throw new ClienteNoEncontradoException("No se encontraron clientes con nombre:" + nombre);
        }
        return clientesFiltrados;
    }
    @Override
    public Cliente filtrarClientePorId(int id) throws ClienteNoEncontradoException{
        
        Cliente clienteFiltrados = clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElse(null);
        
        if(clienteFiltrados == null){
            throw new ClienteNoEncontradoException("No se encontraron clientes con id:" + id);
        }
        
        return clienteFiltrados;
    }
    
    
}
