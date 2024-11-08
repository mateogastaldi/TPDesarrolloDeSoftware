package memory;

import DAO.CategoriaDAO;
import exceptions.itemMenu.categoria.CategoriaNoEncontradaException;
import tp.Categoria;
import tp.ItemMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Categoria filtrarCategoriaId(int id) throws CategoriaNoEncontradaException {
        Categoria categoria = this.categorias.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if(categoria == null){
            throw new CategoriaNoEncontradaException("No existen categoria con el id" + id);
        }
        return categoria;
    }

    public void eliminarCategoria(int id) throws CategoriaNoEncontradaException {
        Categoria categoria = filtrarCategoriaId(id);
        if(categoria == null){
            throw new CategoriaNoEncontradaException("No existen categoria");
        }
        this.categorias.remove(categoria);
    }

    public List<Categoria> filtrarCategoriaPorNombre(String nombre) throws CategoriaNoEncontradaException {
        List<Categoria> categorias = this.categorias.stream()
                .filter(cat-> cat.getDescripcion().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        if(categorias.isEmpty()){
            throw new CategoriaNoEncontradaException("No existen categoria");
        }
        return categorias;
    }

    public void addCategoria(Categoria cat){
        this.categorias.add(cat);
    }
}
