package controller;

import java.sql.SQLException;
import java.util.List;
import exceptions.itemMenu.ItemMenuNoEncontradoException;
import exceptions.itemMenu.categoria.CategoriaNoEncontradaException;
import DAO.FACTORY.DAOFactory;
import model.ItemMenu;
import model.Bebida;
import model.Plato;
import model.Categoria;
import model.Vendedor;

public class ItemMenusController {

    // Singleton -------------------------------------------------------------
    private static ItemMenusController instance;

    private ItemMenusController() {
    }

    public static ItemMenusController getInstance() {
        if (instance == null) {
            instance = new ItemMenusController();
        }
        return instance;
    }
    // -----------------------------------------------------------------------

    // Methods ItemMenu ------------------------------------------------------
    public List<ItemMenu> getItemMenus() throws ItemMenuNoEncontradoException, SQLException {
        return DAOFactory.getInstance().getItemsMenuDAO().getItemMenus();
    }

    public void addBebida(String nombre, String descripcion, double precioIngresado, boolean aptoVegano,
            boolean aptoCelaico, Categoria categoria, Vendedor vendedor, double gradAlcoholica, double tamanioIngresad)
            throws ItemMenuNoEncontradoException, SQLException {
        ItemMenu itemMenu = new Bebida(nombre, descripcion, precioIngresado, aptoVegano, aptoCelaico, categoria,
                vendedor, gradAlcoholica, tamanioIngresad);
        DAOFactory.getInstance().getItemsMenuDAO().addItemMenu(itemMenu);
    }

    public void addPlato(String nombre, String descripcion, double precioIngresado, boolean aptoVegano,
            Categoria categoria, Vendedor vendedor, double calorias, boolean aptoCeliaco, double peso)
            throws SQLException {
        ItemMenu item = new Plato(nombre, descripcion, precioIngresado, aptoVegano, aptoCeliaco, categoria, vendedor,
                calorias, peso);
        DAOFactory.getInstance().getItemsMenuDAO().addItemMenu(item);
    }

    public List<ItemMenu> filtrarItemMenuPorNombre(String nombre) throws ItemMenuNoEncontradoException, SQLException {
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarItemMenuPorNombre(nombre);
    }

    public ItemMenu filtrarItemMenuPorId(int id) throws ItemMenuNoEncontradoException, SQLException {
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarItemMenuPorId(id);
    }

    public List<ItemMenu> filtrarPorIdVendedor(int idVendedor) throws ItemMenuNoEncontradoException, SQLException {
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarPorIdVendedor(idVendedor);
    }

    public List<ItemMenu> filtrarPorCategoria(int categoria) throws ItemMenuNoEncontradoException, SQLException {
        return DAOFactory.getInstance().getItemsMenuDAO().filtrarPorCategoria(categoria);
    }

    public void eliminarItemMenu(int id) throws ItemMenuNoEncontradoException, SQLException {
        DAOFactory.getInstance().getItemsMenuDAO().eliminarItemMenu(id);
    }

    public <T extends ItemMenu> List<Categoria> filtrarPorTipoItem(Class<? extends ItemMenu> bebida)
            throws SQLException {
        return DAOFactory.getInstance().getCategoriaDAO().filtrarPorTipoItem(bebida);
    }

    public void editarItemMenu(int id, String nombre, String descripcion, double precio, boolean aptoVegano,
            boolean aptoCeliaco, Categoria categoria, Vendedor vendedor, Double especifico1, Double especifico2)
            throws ItemMenuNoEncontradoException, SQLException {
        ItemMenu item = filtrarItemMenuPorId(id);
        item.editarItem(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor);
        DAOFactory.getInstance().getItemsMenuDAO().editarItemMenu(id, nombre, descripcion, precio, aptoVegano,
                aptoCeliaco, categoria, vendedor, especifico1, especifico2);
    }

    // -----------------------------------------------------------------------
    // Methods Categoria ------------------------------------------------------
    public List<Categoria> getCategorias() throws CategoriaNoEncontradaException, SQLException {
        return DAOFactory.getInstance().getCategoriaDAO().getCategorias();
    }

    public Categoria filtrarCategoriaId(int id) throws CategoriaNoEncontradaException, SQLException {
        return DAOFactory.getInstance().getCategoriaDAO().filtrarCategoriaId(id);
    }

    public void eliminarCategoria(int id) throws CategoriaNoEncontradaException, SQLException {
        DAOFactory.getInstance().getCategoriaDAO().eliminarCategoria(id);
    }

    public List<Categoria> filtrarCategoriaPorNombre(String nombre)
            throws CategoriaNoEncontradaException, SQLException {
        return DAOFactory.getInstance().getCategoriaDAO().filtrarCategoriaPorNombre(nombre);
    }

    public void addCategoria(String descripcion, Class<? extends ItemMenu> tipoItem) throws SQLException {
        Categoria categoria = new Categoria(descripcion, tipoItem);
        DAOFactory.getInstance().getCategoriaDAO().addCategoria(categoria);
    }
    // -----------------------------------------------------------------------
}
