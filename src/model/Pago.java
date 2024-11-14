package model;

public class Pago {
    int id;
    PagoStrategy metodoDePago;
    double monto;

    //constructor-------------------------------------------------------------------------------------
    
    public Pago(PagoStrategy m, double precioBase) {
        setMetodoPago(m);
        setMonto(m.precio(precioBase));
    }

    //-------------------------------------------------------------------------------------------------
    
    //getters------------------------------------------------------------------------------------------
    public PagoStrategy getMetodoDePago(){
        return this.metodoDePago;
    }
    public double getMonto(){
        return this.monto;
    }
    public int getId(){
        return this.id;
    }
    //-------------------------------------------------------------------------------------------------

    //setters
    public void setMetodoPago(PagoStrategy m){
        metodoDePago = m;
    }
    public void setMonto(double precio){
        this.monto  = precio;
    }
    public void generatedId(int id){
        this.id = id;
    }
    public void setId(int id){
        this.id = id;
    }
    //-------------------------------------------------------------------------------------------------

    //metodos------------------------------------------------------------------------------------------
    public void pagar(){

    }
    //-------------------------------------------------------------------------------------------------


}
