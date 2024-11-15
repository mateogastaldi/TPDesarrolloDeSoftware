// ItemsMenuDAO.java
package DAO;

import exceptions.itemMenu.ItemMenuNoEncontradoException;

import java.sql.SQLException;
import java.util.List;
import model.Categoria;
import model.ItemMenu;
import model.Vendedor;

public interface ItemsMenuDAO{
    List<ItemMenu> getItemMenus() throws ItemMenuNoEncontradoException,SQLException;
    void addItemMenu(ItemMenu itemMenu) throws ItemMenuNoEncontradoException,SQLException;
    List<ItemMenu> filtrarItemMenuPorNombre(String nombre) throws ItemMenuNoEncontradoException,SQLException;
    ItemMenu filtrarItemMenuPorId(int id) throws ItemMenuNoEncontradoException,SQLException;
    List<ItemMenu> filtrarPorIdVendedor(int idVendedor) throws ItemMenuNoEncontradoException,SQLException;
    List<ItemMenu> filtrarPorCategoria(int categoria) throws ItemMenuNoEncontradoException,SQLException;
    void eliminarItemMenu(int id) throws ItemMenuNoEncontradoException,SQLException;
    void editarItemMenu(int id, String nombre, String descripcion, double precio, boolean aptoVegano, boolean aptoCeliaco, Categoria categoria, Vendedor vendedor, Double espe1, Double espe2) throws ItemMenuNoEncontradoException,SQLException;
}
