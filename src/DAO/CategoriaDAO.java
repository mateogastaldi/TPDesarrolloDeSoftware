package DAO;

import exceptions.itemMenu.categoria.CategoriaNoEncontradaException;
import memory.CategoriaMemory;
import tp.Categoria;

import java.util.List;

public interface CategoriaDAO {
    List<Categoria> getCategorias() throws CategoriaNoEncontradaException;
}
