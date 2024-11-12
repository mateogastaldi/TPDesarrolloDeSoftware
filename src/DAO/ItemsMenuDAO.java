// ItemsMenuDAO.java
package DAO;

import exceptions.itemMenu.ItemMenuNoEncontradoException;
import java.util.List;
import tp.Categoria;
import tp.ItemMenu;
import tp.Vendedor;

public interface ItemsMenuDAO{
    List<ItemMenu> getItemMenus() throws ItemMenuNoEncontradoException;
    void addItemMenu(ItemMenu itemMenu) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarItemMenuPorNombre(String nombre) throws ItemMenuNoEncontradoException;
    ItemMenu filtrarItemMenuPorId(int id) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarPorNombreVendedor(String nombreVendedor) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarPorIdVendedor(int idVendedor) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarPorCategoria(int categoria) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarAptoVeganos() throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarAptoCeliacos() throws ItemMenuNoEncontradoException;
    void eliminarItemMenu(int id) throws ItemMenuNoEncontradoException;
    void editarItemMenu(int id, String nombre, String descripcion, double precio, boolean aptoVegano, boolean aptoCeliaco, Categoria categoria, Vendedor vendedor, Double especifico1, Double especifico2) throws ItemMenuNoEncontradoException;
}
