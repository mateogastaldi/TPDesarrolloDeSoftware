package DAO;

import exceptions.itemMenu.categoria.CategoriaNoEncontradaException;
import model.Categoria;
import model.ItemMenu;

import java.util.List;

public interface CategoriaDAO {
    List<Categoria> getCategorias();
    Categoria filtrarCategoriaId(int id) throws CategoriaNoEncontradaException;
    void eliminarCategoria(int id) throws CategoriaNoEncontradaException;
    List<Categoria> filtrarCategoriaPorNombre (String nombre) throws CategoriaNoEncontradaException;
    void addCategoria(Categoria categoria);
    <T extends ItemMenu> List<Categoria<T>> filtrarPorTipoItem(Class<? extends ItemMenu> tipoClase);
}
