package controller;

import java.util.List;

import exceptions.itemMenu.ItemMenuNoEncontradoException;
import exceptions.itemMenu.categoria.CategoriaNoEncontradaException;
import DAO.FACTORY.DAOFactory;
import model.*;

public class ItemMenusController<T extends ItemMenu> {
    
    // Singleton -------------------------------------------------------------
    private static ItemMenusController instance;
    private ItemMenusController() {}
    public static ItemMenusController getInstance() {
        if (instance == null) {
            instance = new ItemMenusController();
        }
        return instance;
    }
    // -----------------------------------------------------------------------

    // Methods ItemMenu ------------------------------------------------------
    public List<ItemMenu> getItemMenus() throws ItemMenuNoEncontradoException{
        return DAOFactory.getInstance().getItemsMenuDAO().getItemMenus();
    }
    public void addBebida(String nombre,String descripcion,double precioIngresado,boolean aptoVegano,boolean aptoCelaico, Categoria categoria,Vendedor vendedor,double gradAlcoholica,double tamanioIngresad) throws ItemMenuNoEncontradoException{
        ItemMenu itemMenu = new Bebida(nombre,descripcion,precioIngresado,aptoVegano,aptoCelaico,categoria,vendedor,gradAlcoholica,tamanioIngresad);
        DAOFactory.getInstance().getItemsMenuDAO().addItemMenu(itemMenu);
    }
    public void addPlato(String nombre,String descripcion,double precioIngresado,boolean aptoVegano, Categoria categoria,Vendedor vendedor,double calorias, boolean aptoCeliaco, double peso){
        ItemMenu item = new Plato(nombre,descripcion,precioIngresado,aptoVegano,categoria,vendedor,calorias,aptoCeliaco,peso);
        DAOFactory.getInstance().getItemsMenuDAO().addItemMenu(item);
    }
    public List<ItemMenu> filtrarItemMenuPorNombre(String nombre) throws ItemMenuNoEncontradoException{
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarItemMenuPorNombre(nombre);
    }
    public ItemMenu filtrarItemMenuPorId(int id) throws ItemMenuNoEncontradoException{
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarItemMenuPorId(id);
    }
    public List<ItemMenu> filtrarPorNombreVendedor(String nombreVendedor) throws ItemMenuNoEncontradoException{
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarPorNombreVendedor(nombreVendedor);
    }
    public List<ItemMenu> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemMenuNoEncontradoException{
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarPorRangoPrecio(precioMin, precioMax);
    }
    public List<ItemMenu> filtrarPorIdVendedor(int idVendedor) throws ItemMenuNoEncontradoException{
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarPorIdVendedor(idVendedor);
    }
    public List<ItemMenu> filtrarPorCategoria(int categoria) throws ItemMenuNoEncontradoException{
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarPorCategoria(categoria);
    }
    public List<ItemMenu> filtrarAptoVeganos() throws ItemMenuNoEncontradoException{
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarAptoVeganos();
    }
    public List<ItemMenu> filtrarAptoCeliacos() throws ItemMenuNoEncontradoException{
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarAptoCeliacos();
    }
    public void eliminarItemMenu(int id) throws ItemMenuNoEncontradoException{
        DAOFactory.getInstance().getItemsMenuDAO().eliminarItemMenu(id);
    }
    public <T extends ItemMenu> List<Categoria<T>> filtrarPorTipoItem(Class<? extends ItemMenu> bebida){
        return DAOFactory.getInstance().getCategoriaDAO().filtrarPorTipoItem(bebida);
    }
    public void editarItemMenu(int id, String nombre, String descripcion, double precio, boolean aptoVegano, boolean aptoCeliaco, Categoria categoria, Vendedor vendedor, Double especifico1, Double especifico2) throws ItemMenuNoEncontradoException{
        DAOFactory.getInstance().getItemsMenuDAO().editarItemMenu(id, nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, especifico1, especifico2);
    }
    // -----------------------------------------------------------------------
    // Methods Categoria ------------------------------------------------------
    public List<Categoria> getCategorias(){
        return DAOFactory.getInstance().getCategoriaDAO().getCategorias();
    }
    public Categoria filtrarCategoriaId(int id) throws CategoriaNoEncontradaException{
        return DAOFactory.getInstance().getCategoriaDAO().filtrarCategoriaId(id);
    }
    public void eliminarCategoria(int id) throws CategoriaNoEncontradaException{
        DAOFactory.getInstance().getCategoriaDAO().eliminarCategoria(id);
    }
    public List<Categoria> filtrarCategoriaPorNombre (String nombre) throws CategoriaNoEncontradaException{
        return DAOFactory.getInstance().getCategoriaDAO().filtrarCategoriaPorNombre(nombre);
    }
    public void addCategoria(String descripcion,Class<T> tipoItem){
        Categoria categoria = new Categoria(descripcion,tipoItem);
        DAOFactory.getInstance().getCategoriaDAO().addCategoria(categoria);
    }
    // -----------------------------------------------------------------------
}
