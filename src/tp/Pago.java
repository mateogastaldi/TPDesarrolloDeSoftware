package tp;

public class Pago {
    PagoStrategy metodoDePago;
    double monto;

    //constructor
    public Pago(PagoStrategy m, double precioBase) {
        setMetodoPago(m);
        setMonto(m.precio(precioBase));
    }
    //getters
    public PagoStrategy getMetodoDePago(){
        return this.metodoDePago;
    }
    public double getMonto(){
        return this.monto;
    }

    //setters
    public void setMetodoPago(PagoStrategy m){
        metodoDePago = m;
    }
    public void setMonto(double precio){
        this.monto  = precio;
    }

    //metodos
    public void pagar(){

    }


}
