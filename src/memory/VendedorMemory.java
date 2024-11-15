
// package memory;

// import DAO.VendedorDAO;
// import model.Coordenada;
// import model.Direccion;
// import model.Vendedor;
// import exceptions.vendedor.VendedorNoEncontradoException;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// public class VendedorMemory implements VendedorDAO {
//     //UTILIZAMOS PATRON SINGLETON PARA LA CREACION DEL MEMORY
//     private static VendedorMemory VENDEDORMEMORY_INTANCE;
//     private List<Vendedor> vendedores;

//     //constructores
//     private VendedorMemory(){
//         vendedores = new ArrayList<>();
//     }
//     private VendedorMemory(List<Vendedor> vendedores){
//         setVendedores(vendedores);
//     }

//     //getters
//     @Override
//     public List<Vendedor> getVendedores(){
//         return this.vendedores;
//     }
//     public static VendedorMemory getInstance(){
//         if(VENDEDORMEMORY_INTANCE == null){
//             VENDEDORMEMORY_INTANCE = new VendedorMemory();
//         }
//         return VENDEDORMEMORY_INTANCE;
//     }

//     //setters
//     private void setVendedores(List<Vendedor> vendedores){
//         this.vendedores = vendedores;
//     }

//     //metodos
//     @Override
//     public void addVendedor(Vendedor v){
//         this.vendedores.add(v);
//     }

//     @Override
//     public List<Vendedor> filtrarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException {

//         List<Vendedor> vendedoresFiltrados = vendedores.stream()
//                 .filter(v -> v.getNombre().equalsIgnoreCase(nombre))
//                 .collect(Collectors.toList());

//         if(vendedoresFiltrados.isEmpty()){
//             throw new VendedorNoEncontradoException("No se encontraron vendedores con nombre:" + nombre);
//         }
//         return vendedoresFiltrados;
//     }
//     @Override
//     public Vendedor filtrarVendedorPorId(int id) throws VendedorNoEncontradoException{

//         Vendedor vendedoresFiltrados = vendedores.stream()
//                 .filter(v -> v.getId() == id)
//                 .findFirst().orElse(null);

//         if(vendedoresFiltrados == null){
//             throw new VendedorNoEncontradoException("No se encontraron vendedores con id:" + id);
//         }

//         return vendedoresFiltrados;
//     }

//     public void eliminarVendedor(int id) throws VendedorNoEncontradoException{
//         Vendedor vendedor = filtrarVendedorPorId(id);
//         if(vendedor == null){
//             throw new VendedorNoEncontradoException("No se encontraron vendedores con id:" + id);
//         }
//         this.vendedores.remove(vendedor);
//     }

//     public void modificarVendedor(int id, String nombre, Direccion direccion, Coordenada coordenada) throws VendedorNoEncontradoException{
//         Vendedor vendedor = filtrarVendedorPorId(id);
//         if(vendedor == null){
//             throw new VendedorNoEncontradoException("No se encontraron vendedores con id:" + id);
//         }
//         vendedor.modificarVendedor(nombre, direccion, coordenada);
//     }

// }
