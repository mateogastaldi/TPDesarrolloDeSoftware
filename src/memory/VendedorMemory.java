
package memory;

import DAO.VendedorDAO;
import tp.Vendedor;
import exceptions.VendedorNoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendedorMemory implements VendedorDAO {
    //UTILIZAMOS PATRON SINGLETON PARA LA CREACION DEL MEMORY
    private static VendedorMemory VENDEDORMEMORY_INTANCE;
    private List<Vendedor> vendedores;

    //constructores
    private VendedorMemory(){
        vendedores = new ArrayList<>();
    }
    private VendedorMemory(List<Vendedor> vendedores){
        setVendedores(vendedores);
    }

    //getters
    public List<Vendedor> getVendedores(){
        return this.vendedores;
    }
    public static VendedorMemory getInstance(){
        if(VENDEDORMEMORY_INTANCE == null){
            VENDEDORMEMORY_INTANCE = new VendedorMemory();
        }
        return VENDEDORMEMORY_INTANCE;
    }

    //setters
    private void setVendedores(List<Vendedor> vendedores){
        this.vendedores = vendedores;
    }

    //metodos
    public void addVendedor(Vendedor v){
        this.vendedores.add(v);
    }

    @Override
    public List<Vendedor> filtrarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException {

        List<Vendedor> vendedoresFiltrados = vendedores.stream()
                .filter(v -> v.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        if(vendedoresFiltrados.isEmpty()){
            throw new VendedorNoEncontradoException("No se encontraron vendedores con nombre:" + nombre);
        }
        return vendedoresFiltrados;
    }
    @Override
    public List<Vendedor> filtrarVendedorPorId(int id) throws VendedorNoEncontradoException{

        List<Vendedor> vendedoresFiltrados = vendedores.stream()
                .filter(v -> v.getId() == id)
                .collect(Collectors.toList());

        if(vendedoresFiltrados.isEmpty()){
            throw new VendedorNoEncontradoException("No se encontraron vendedores con id:" + id);
        }

        return vendedoresFiltrados;
    }
}
