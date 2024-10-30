package tp;

public class Categoria<T extends ItemMenu> {
    private static int contadorId=0;
    private int id;
    private String descripcion;
    private Class<T> tipoItem;

    //constructor
    public Categoria(String descripcion, Class<T> tipoItem) {
        setDescripcion(descripcion);
        setId();
        setTipoItem(tipoItem);
    }

    //getters-setters
    private void setId() {this.id = ++contadorId;}
    private void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    private void setTipoItem(Class<T> tipoItem) {this.tipoItem = tipoItem;}

    public int getId() {return id;}
    public String getDescripcion() {return descripcion;}
    public Class<T> getTipoItem() {return tipoItem;}
}
