package tp;


public class Plato extends ItemMenu{
    private double calorias;
    private double peso;

    //constructor
    public Plato(String nombre, String descripcion, double precio,boolean aptoVegano, Categoria categoria,Vendedor vendedor, double calorias, boolean aptoCeliaco, double peso){
        super(nombre,descripcion,precio, aptoVegano,aptoCeliaco,categoria,vendedor);
        setCalorias(calorias);
        setPeso(peso);
        
    }
    
    //getters-setters
    public double getCalorias() {
        return calorias;
    }
    public double getPeso() {return peso;}

    private void setCalorias(double calorias) {
        this.calorias = calorias;
    }
    private void setPeso(double peso) {
        this.peso = peso;
    }
    
    //metodos
    @Override
    public double peso(){
        return peso * 1.1;
    }
    @Override
    public boolean esComida(){
        return true;
    }
    @Override
    public boolean esBebida(){
        return false;
    }
    @Override
    public boolean aptoVegano(){return getAptoVegano();}
    @Override
    public boolean aptoCeliaco(){return getAptoCeliaco();}
   
}
