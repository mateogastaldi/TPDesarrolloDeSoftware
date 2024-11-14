// ItemsMenuMemory.java
package mySQL;
import DAO.ItemsMenuDAO;
import exceptions.itemMenu.ItemMenuNoEncontradoException;
import model.*;
import java.util.*;

public class ItemsMenuMySQL implements ItemsMenuDAO{
   // Singleton -------------------------------------------------------------
    private static ItemsMenuMySQL ITEMMENUMYSQL_INSTANCE;
    private ItemsMenuMySQL() {}
    public static ItemsMenuMySQL getInstance() {
        if (ITEMMENUMYSQL_INSTANCE == null) {
            ITEMMENUMYSQL_INSTANCE = new ItemsMenuMySQL();
        }
        return ITEMMENUMYSQL_INSTANCE;
    }
    // -----------------------------------------------------------------------

    // Methods ItemMenuDAO ---------------------------------------------------
    public List<ItemMenu> getItemMenus() throws ItemMenuNoEncontradoException {return null;}
    public void addItemMenu(ItemMenu itemMenu) throws ItemMenuNoEncontradoException {
        d 

    }




}


    // List<ItemMenu> getItemMenus() throws ItemMenuNoEncontradoException;
    // void addItemMenu(ItemMenu itemMenu) throws ItemMenuNoEncontradoException;
    // List<ItemMenu> filtrarItemMenuPorNombre(String nombre) throws ItemMenuNoEncontradoException;
    // ItemMenu filtrarItemMenuPorId(int id) throws ItemMenuNoEncontradoException;
    // List<ItemMenu> filtrarPorNombreVendedor(String nombreVendedor) throws ItemMenuNoEncontradoException;
    // List<ItemMenu> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemMenuNoEncontradoException;
    // List<ItemMenu> filtrarPorIdVendedor(int idVendedor) throws ItemMenuNoEncontradoException;
    // List<ItemMenu> filtrarPorCategoria(int categoria) throws ItemMenuNoEncontradoException;
    // List<ItemMenu> filtrarAptoVeganos() throws ItemMenuNoEncontradoException;
    // List<ItemMenu> filtrarAptoCeliacos() throws ItemMenuNoEncontradoException;
    // void eliminarItemMenu(int id) throws ItemMenuNoEncontradoException;
    // void editarItemMenu(int id, String nombre, String descripcion, double precio, boolean aptoVegano, boolean aptoCeliaco, Categoria categoria, Vendedor vendedor, Double especifico1, Double especifico2) throws ItemMenuNoEncontradoException;

