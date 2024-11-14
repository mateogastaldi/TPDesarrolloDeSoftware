// Categoria.java
package model;
// exceptions
import exceptions.itemMenu.categoria.CategoriaNoCreadaException;

public class Categoria{
    private int id;
    private String descripcion;
    private Class<? extends ItemMenu> tipoItem;

    public Categoria(int id, String descripcion, Class<? extends ItemMenu> tipoItem) throws CategoriaNoCreadaException {
        if (descripcion == null || tipoItem == null)
            throw new CategoriaNoCreadaException("No se pudo crear la categoria");
        else {
            setDescripcion(descripcion);
            setTipoItem(tipoItem);
            setId(id);
        }
    }

    // getters-setters

    private void setId(int id) {
        this.id = id;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private void setTipoItem(Class<? extends ItemMenu> tipoItem2) {
            this.tipoItem = tipoItem2;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Class<? extends ItemMenu> getTipoItem() {
        return tipoItem;
    }

}
