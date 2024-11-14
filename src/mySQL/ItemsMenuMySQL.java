// ItemsMenuMemory.java
package mySQL;

import DAO.ItemsMenuDAO;
import exceptions.itemMenu.ItemMenuNoEncontradoException;
import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Override
    public void getItemMenu() {
        
    }




}