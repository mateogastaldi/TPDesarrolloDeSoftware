package DAO;

import exceptions.itemMenu.categoria.CategoriaNoEncontradaException;
import memory.CategoriaMemory;
import tp.Categoria;

import java.util.List;

public interface CategoriaDAO {
    List<Categoria> getCategorias() throws CategoriaNoEncontradaException;
    Categoria filtrarCategoriaId(int id) throws CategoriaNoEncontradaException;
    void eliminarCategoria(int id) throws CategoriaNoEncontradaException;
    List<Categoria> filtrarCategoriaPorNombre (String nombre) throws CategoriaNoEncontradaException;
    void addCategoria(Categoria categoria);
}
