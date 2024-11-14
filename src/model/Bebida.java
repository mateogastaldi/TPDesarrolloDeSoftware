// Bebida.java
package model;
public class Bebida extends ItemMenu{

    // Atributos --------------------------------------------------------------------------------------
    private double graduacionAlcoholica;
    private double tamanio;
    // ------------------------------------------------------------------------------------------------

    // Constructor ------------------------------------------------------------------------------------
    public Bebida(String nombre, String descripcion, double precio,boolean aptoVegano,boolean aptoCeliaco, Categoria categoria,Vendedor vendedor, double graduacionAlcoholica, double tamanio){
        super(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor); // ItemMenu
        setGraduacionAlcoholica(graduacionAlcoholica);
        setTamanio(tamanio);
    }
    // ------------------------------------------------------------------------------------------------

    // Getters ----------------------------------------------------------------------------------------
    public double getGraduacionAlcoholica(){return graduacionAlcoholica;}
    public double getTamanio() {return tamanio;}
    // ------------------------------------------------------------------------------------------------

    // Setters ----------------------------------------------------------------------------------------
    private void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }
    private void setTamanio(double tamanio) {this.tamanio = tamanio;}
    // ------------------------------------------------------------------------------------------------

    // Metodos ----------------------------------------------------------------------------------------
    @Override
    public double peso() {
        if(this.getGraduacionAlcoholica()>0){
            return (this.getTamanio() * 0.99) * 1.2;}
        else{
            return (this.getTamanio() * 1.04) * 1.2;}
    }

    @Override
    public boolean esComida(){return false;}

    @Override
    public boolean esBebida(){return true;}

    @Override
    public boolean aptoVegano(){return getAptoVegano();}

    @Override
    public boolean aptoCeliaco(){return getAptoCeliaco();}

    public void editarItem(String nombre, String descripcion, double precio, boolean aptoVegano, boolean aptoCeliaco, Categoria categoria, Vendedor vendedor, double graduacionAlcoholica, double tamanio) {
        super.editarItem(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor);
        setGraduacionAlcoholica(graduacionAlcoholica);
        setTamanio(tamanio);
    }
    // ------------------------------------------------------------------------------------------------
}
