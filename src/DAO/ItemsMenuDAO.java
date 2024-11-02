
package DAO;

import java.util.List;

import tp.ItemMenu;
import exceptions.itemMenu.ItemMenuNoEncontradoException;

public interface ItemsMenuDAO{
    List<ItemMenu> getItemMenus() throws ItemMenuNoEncontradoException;
    void addItemMenu(ItemMenu itemMenu) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarItemMenuPorNombre(String nombre) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarItemMenuPorId(int id) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarPorNombreVendedor(String nombreVendedor) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarPorIdVendedor(int idVendedor) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarPorCategoria(int categoria) throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarAptoVeganos() throws ItemMenuNoEncontradoException;
    List<ItemMenu> filtrarAptoCeliacos() throws ItemMenuNoEncontradoException;
}
