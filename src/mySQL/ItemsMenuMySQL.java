// ItemsMenuMemory.java
package mySQL;

import DAO.ItemsMenuDAO;
import exceptions.itemMenu.ItemMenuNoEncontradoException;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemsMenuMemory implements ItemsMenuDAO {
    //UTILIZAMOS PATRON SINGLETON PARA LA CREACION DEL MEMORY
    private static ItemsMenuMemory ITEMMENU_INSTANCE;
    private List<ItemMenu> itemMenus;

    //constructores
    private ItemsMenuMemory(){
        itemMenus = new ArrayList<>();
    }
    private ItemsMenuMemory(List<ItemMenu> itemMenus) {
        setItemMenu(itemMenus);
    }

    //getters
    public List<ItemMenu> getItemMenus(){
        return this.itemMenus;
    }
    public static ItemsMenuMemory getInstance() {
        if(ITEMMENU_INSTANCE == null) {
            ITEMMENU_INSTANCE = new ItemsMenuMemory();
        }
        return ITEMMENU_INSTANCE;
    }
    //setters
    private void setItemMenu(List<ItemMenu> clientes){
        this.itemMenus = clientes;
    }

    //metodos
    public void addItemMenu(ItemMenu i){
        this.itemMenus.add(i);
    }

    @Override
    public List<ItemMenu> filtrarItemMenuPorNombre(String nombre) throws ItemMenuNoEncontradoException {

        List<ItemMenu> itemsFiltrados = itemMenus.stream()
                .filter(i -> i.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        if(itemsFiltrados.isEmpty()){
            throw new ItemMenuNoEncontradoException("No se encontraron items con nombre:" + nombre);
        }
        return itemsFiltrados;
    }
    @Override
    public ItemMenu filtrarItemMenuPorId(int id) throws ItemMenuNoEncontradoException{

        ItemMenu itemsFiltrados = itemMenus.stream()
                .filter(i -> i.getId() == id)
                .findFirst().orElse(null);

        if(itemsFiltrados == null){
            throw new ItemMenuNoEncontradoException("No se encontraron items con id:" + id);
        }

        return itemsFiltrados;
    }
    @Override
    public List<ItemMenu> filtrarPorNombreVendedor(String nombreVendedor) throws ItemMenuNoEncontradoException {
        List<ItemMenu> resultado = itemMenus.stream()
                .filter(i -> i.getVendedor().getNombre().equalsIgnoreCase(nombreVendedor))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new ItemMenuNoEncontradoException("No se encontraron ítems para el vendedor: " + nombreVendedor);
        }

        return resultado;
    }
    @Override
    public List<ItemMenu> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemMenuNoEncontradoException {
        List<ItemMenu> resultado = itemMenus.stream()
                .filter(i -> i.getPrecio() >= precioMin && i.getPrecio() <= precioMax)
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new ItemMenuNoEncontradoException("No se encontraron ítems en el rango de precios: " + precioMin + " - " + precioMax);
        }

        return resultado;
    }
    @Override
    public List<ItemMenu> filtrarPorIdVendedor(int idVendedor) throws ItemMenuNoEncontradoException {
        List<ItemMenu> itemsFiltrados =  itemMenus.stream()
                .filter(i -> i.getVendedor().getId() == idVendedor)
                .collect(Collectors.toList());

        if (itemsFiltrados.isEmpty()) {
            throw new ItemMenuNoEncontradoException("No se encontraron ítems del restaurante: " + idVendedor);
        }

        return itemsFiltrados;
    }
    @Override
    public List<ItemMenu> filtrarPorCategoria(int categoria) throws ItemMenuNoEncontradoException {
        List<ItemMenu> itemFiltrados = itemMenus.stream()
                .filter(i -> i.getCategoria().getId() == categoria)
                .collect(Collectors.toList());

        if(itemFiltrados.isEmpty()){
            throw new ItemMenuNoEncontradoException("No se encontraron ítems de la categoria: " + categoria);
        }

        return itemFiltrados;
    }
    @Override
    public List<ItemMenu> filtrarAptoVeganos() throws ItemMenuNoEncontradoException {
        List<ItemMenu> itemFiltrados = itemMenus.stream()
                .filter(i -> i.getAptoVegano())
                .collect(Collectors.toList());

        if(itemFiltrados.isEmpty()){
            throw new ItemMenuNoEncontradoException("No se encontraron ítems aptos para veganos");
        }

        return itemFiltrados;
    }
    @Override
    public List<ItemMenu> filtrarAptoCeliacos() throws ItemMenuNoEncontradoException{
        List<ItemMenu> itemFiltrados = itemMenus.stream()
                .filter(i -> i.getAptoCeliaco())
                .collect(Collectors.toList());

        if(itemFiltrados.isEmpty()){
            throw new ItemMenuNoEncontradoException("No se encontraron ítems aptos para celiacos");
        }

        return itemFiltrados;
    }

    @Override
    public void eliminarItemMenu(int id) throws ItemMenuNoEncontradoException{
        ItemMenu itemMenu = itemMenus.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if(itemMenu == null){
            ItemMenuNoEncontradoException exc = new ItemMenuNoEncontradoException("Item no encontrado");
        }
        else {
            itemMenus.remove(itemMenu);
        }
    }
    
    // ItemsMenuMemory.java
    @Override
    public void editarItemMenu(int id, String nombre, String descripcion, double precio, boolean aptoVegano, boolean aptoCeliaco, Categoria categoria, Vendedor vendedor, Double especifico1, Double especifico2) throws ItemMenuNoEncontradoException {
        ItemMenu item = filtrarItemMenuPorId(id); // Usa el método de filtro existente para obtener el ítem
        switch (item) {
            case Plato plato -> {
                double calorias = (especifico1 != null) ? especifico1 : plato.getCalorias();
                double peso = (especifico2 != null) ? especifico2 : plato.getPeso();
                plato.editarItem(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, calorias, peso);
            }
            case Bebida bebida -> {
                double graduacionAlcoholica = (especifico1 != null) ? especifico1 : bebida.getGraduacionAlcoholica();
                double tamanio = (especifico2 != null) ? especifico2 : bebida.getTamanio();
                bebida.editarItem(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, graduacionAlcoholica, tamanio);
            }
            default -> throw new ItemMenuNoEncontradoException("El ítem con ID " + id + " no es válido para la edición");
        }
    }

}
