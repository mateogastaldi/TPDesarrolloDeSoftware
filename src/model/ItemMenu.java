// ItemMenu.java
package model;

public abstract class ItemMenu {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean aptoVegano;
    private boolean aptoCeliaco;
    private Categoria categoria;
    private Vendedor vendedor;

    // Constructor ------------------------------------------------------------------------------------
    public ItemMenu(int id, String nombre, String descripcion, double precio,boolean aptoVegano, boolean aptoCeliaco, Categoria categoria, Vendedor vendedor) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        setAptoVegano(aptoVegano);
        setAptoCeliaco(aptoCeliaco);
        setCategoria(categoria);
        setVendedor(vendedor);
    }
    // -----------------------------------------------------------------------------------------------

    // Getters ----------------------------------------------------------------------------------------
    public int getId(){return this.id;}
    public String getNombre(){return this.nombre;}
    public String getDescripcion(){return this.descripcion;}
    public double getPrecio(){return this.precio;}
    public Categoria getCategoria(){return this.categoria;}
    public Vendedor getVendedor(){return this.vendedor;}
    public boolean getAptoVegano(){return this.aptoVegano;}
    public boolean getAptoCeliaco(){return this.aptoCeliaco;}
    // -----------------------------------------------------------------------------------------------

    // Setters ----------------------------------------------------------------------------------------
    private void setId(int id){this.id = id;}
    private void setNombre(String nombre){this.nombre = nombre;}
    private void setDescripcion(String descripcion){this.descripcion = descripcion;}
    private void setPrecio(double precio){this.precio = precio;}
    private void setCategoria(Categoria categoria){this.categoria = categoria;}
    private void setVendedor(Vendedor vendedor){this.vendedor = vendedor;}
    private void setAptoVegano(boolean aptoVegano){this.aptoVegano = aptoVegano;}
    private void setAptoCeliaco(boolean aptoCeliaco){this.aptoCeliaco = aptoCeliaco;}
    // -----------------------------------------------------------------------------------------------

    // Metodos ----------------------------------------------------------------------------------------
    public abstract double peso();
    public abstract boolean esComida();
    public abstract boolean esBebida();
    public abstract boolean aptoVegano();
    public abstract boolean aptoCeliaco();
    public void printItemMenu(){System.out.println("ID: " + getId() + " | Nombre: " + getNombre() + " | Precio: " + getPrecio());}
    public void editarItem(String nombre, String descripcion, double precio, boolean aptoVegano, boolean aptoCeliaco, Categoria categoria, Vendedor vendedor) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        setAptoVegano(aptoVegano);
        setAptoCeliaco(aptoCeliaco);
        setCategoria(categoria);
        setVendedor(vendedor);
    }
    // -----------------------------------------------------------------------------------------------

}
