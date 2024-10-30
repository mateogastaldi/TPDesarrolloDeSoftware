package tp;
public class Bebida extends ItemMenu{
    private double graduacionAlcoholica;
    private double tamanio;
    
    //constructores
    public Bebida(String nombre, String descripcion, double precio,boolean aptoVegano,boolean aptoCeliaco, Categoria categoria,Vendedor vendedor, double graduacionAlcoholica, double tamanio){
        super(nombre,descripcion,precio, aptoVegano,aptoCeliaco,categoria,vendedor);
        setGraduacionAlcoholica(graduacionAlcoholica);
        setTamanio(tamanio);
    }

    //getters-setters
    public double getGraduacionAlcoholica(){
        return graduacionAlcoholica;
    }
    public double getTamanio() {return tamanio;}

    private void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }
    private void setTamanio(double tamanio) {this.tamanio = tamanio;}

    //metodos
    @Override
    public double peso() {
        if(this.getGraduacionAlcoholica()>0){
            return (this.getTamanio() * 0.99) * 1.2;}
        else{
            return (this.getTamanio() * 1.04) * 1.2;}
    }
    @Override
    public boolean esComida(){
        return false;
    }
    @Override
    public boolean esBebida(){
        return true;
    }
    @Override
    public boolean aptoVegano(){
        return getAptoVegano();
    }
    @Override
    public boolean aptoCeliaco(){return getAptoCeliaco();}
}
