package DAO;

import exceptions.itemMenu.categoria.CategoriaNoEncontradaException;
import model.Categoria;
import model.ItemMenu;

import java.sql.SQLException;
import java.util.List;

public interface CategoriaDAO {
    List<Categoria> getCategorias() throws SQLException;
    Categoria filtrarCategoriaId(int id) throws CategoriaNoEncontradaException, SQLException;
    void eliminarCategoria(int id) throws CategoriaNoEncontradaException, SQLException;
    List<Categoria> filtrarCategoriaPorNombre (String nombre) throws CategoriaNoEncontradaException, SQLException;
    void addCategoria(Categoria categoria);
    <T extends ItemMenu> List<Categoria> filtrarPorTipoItem(Class<T> tipoClase);
}
