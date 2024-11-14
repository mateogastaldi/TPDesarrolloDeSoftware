// Plato.java
package model;

public class Plato extends ItemMenu{
    
    // Atributos --------------------------------------------------------------------------------------
    private double calorias;
    private double peso;
    // ------------------------------------------------------------------------------------------------

    // Constructor ------------------------------------------------------------------------------------
    public Plato(String nombre, String descripcion, double precio,boolean aptoVegano, Categoria categoria,Vendedor vendedor, double calorias, boolean aptoCeliaco, double peso){
        super(nombre,descripcion,precio, aptoVegano,aptoCeliaco,categoria,vendedor);
        setCalorias(calorias);
        setPeso(peso);
    }
    // ------------------------------------------------------------------------------------------------

    // Getters ----------------------------------------------------------------------------------------
    public double getCalorias() {return calorias;}
    public double getPeso() {return peso;}
    // ------------------------------------------------------------------------------------------------

    // Setters ----------------------------------------------------------------------------------------
    private void setCalorias(double calorias) {this.calorias = calorias;}
    private void setPeso(double peso) {this.peso = peso;}
    // ------------------------------------------------------------------------------------------------

    // Metodos ----------------------------------------------------------------------------------------
    @Override
    public double peso(){return peso * 1.1;}

    @Override
    public boolean esComida(){return true;}

    @Override
    public boolean esBebida(){return false;}

    @Override
    public boolean aptoVegano(){return getAptoVegano();}

    @Override
    public boolean aptoCeliaco(){return getAptoCeliaco();}

    public void editarItem(String nombre, String descripcion, double precio, boolean aptoVegano, boolean aptoCeliaco, Categoria categoria, Vendedor vendedor, double caloriasIngresadas, double peso) {
        super.editarItem(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor);
        setCalorias(caloriasIngresadas);
        setPeso(peso);
    }
    // ------------------------------------------------------------------------------------------------
}
