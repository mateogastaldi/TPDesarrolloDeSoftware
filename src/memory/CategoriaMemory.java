package memory;

import DAO.CategoriaDAO;
import exceptions.itemMenu.categoria.CategoriaNoEncontradaException;
import tp.Categoria;
import tp.ItemMenu;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMemory implements CategoriaDAO {

    private static CategoriaMemory CATEGORIA_INSTANCE;
    private List<Categoria> categorias;

    //constructores
    private CategoriaMemory(){
        categorias = new ArrayList<>();
    }
    private CategoriaMemory(List<Categoria> c) {
        setItemMenu(c);
    }

    //getters
    public List<Categoria> getCategorias() throws CategoriaNoEncontradaException {
        if(categorias.isEmpty()){
            throw new CategoriaNoEncontradaException("No existen categorias");
        }
        return categorias;
    }
    public static CategoriaMemory getInstance() {
        if(CATEGORIA_INSTANCE == null) {
            CATEGORIA_INSTANCE = new CategoriaMemory();
        }
        return CATEGORIA_INSTANCE;
    }
    //setters
    private void setItemMenu(List<Categoria> cat){
        this.categorias = cat;
    }
}
