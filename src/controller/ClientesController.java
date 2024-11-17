package controller;
import DAO.FACTORY.DAOFactory;
import exceptions.cliente.ClienteNoCreadoException;
import exceptions.cliente.ClienteNoEncontradoException;
import java.sql.SQLException;
import java.util.List;
import model.*;


public class ClientesController {

    // Singleton --------------------------------------------------------------------------------------
    private static ClientesController instance;
    private ClientesController() {}
    public static ClientesController getInstance() {
        if (instance == null) {
            instance = new ClientesController();
        }
        return instance;
    }
    // ------------------------------------------------------------------------------------------------

    // Metodos ----------------------------------------------------------------------------------------
    public void addCliente(String nombre,long cuit,String email,String calle,int altura,String ciudad,String pais,double latitud,double longitud) throws ClienteNoCreadoException{
        if(DAOFactory.getInstance().getClienteDAO().existeCliente(cuit,email)){
            throw new ClienteNoCreadoException("Cuit o Email repetidos en el sistema, ingreselos nuevamente");
        }else{
            Coordenada coord = new Coordenada(latitud,longitud);
            Direccion direccion = new Direccion(calle, altura, ciudad, pais);
            Cliente cliente = new Cliente(nombre,cuit,email,direccion,coord);
            DAOFactory.getInstance().getClienteDAO().addCliente(cliente);
        }

    }
    public List<Cliente> filtrarClientePorNombre(String nombre) throws ClienteNoEncontradoException , SQLException{
        return DAOFactory.getInstance().getClienteDAO().filtrarClientePorNombre(nombre);
    }
    public Cliente filtrarClientePorId(int id) throws ClienteNoEncontradoException,SQLException {
        return DAOFactory.getInstance().getClienteDAO().filtrarClientePorId(id);
    }
    public void eliminarCliente(int id) throws ClienteNoEncontradoException, SQLException {
        DAOFactory.getInstance().getClienteDAO().eliminarCliente(id);
    }
    public List<Cliente> getClientes() throws ClienteNoEncontradoException, SQLException {
        return DAOFactory.getInstance().getClienteDAO().getClientes();
    }
    public void modificarCliente(int id, String nombre, Long cuit, String email, String calle, int altura, String ciudad, String pais, double lat, double lng) throws ClienteNoEncontradoException {
        Direccion direccion = new Direccion(calle, altura, ciudad, pais);
        Coordenada coordenadas = new Coordenada(lat, lng);
        DAOFactory.getInstance().getClienteDAO().modificarCliente(id, nombre, cuit, email, direccion, coordenadas);
    }
    // ------------------------------------------------------------------------------------------------
}