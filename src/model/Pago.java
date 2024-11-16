package model;

import controller.PedidosController;
import gui.pedido.pago.InterfazPagoMercadoPago;

public class Pago {
    int id;
    PagoStrategy metodoDePago;
    double monto;
    boolean pagado;
    String cbu;
    long cuit;
    String alias;


    //constructor-------------------------------------------------------------------------------------
    
    public Pago(PagoStrategy m, double precioBase, boolean pagado, String cbu, long cuit, String alias ) {
        setMetodoPago(m);
        setMonto(m.precio(precioBase));
        setPagado(pagado);
        setCbu(cbu);
        setCuit(cuit);
        setAlias(alias);
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
    public String getCbu(){return this.cbu;}
    public long getCuit(){return this.cuit;}
    public String getAlias(){return this.alias;}
    public boolean isPagado(){return this.pagado;}
    //-------------------------------------------------------------------------------------------------

    //setters
    public void setMetodoPago(PagoStrategy m){
        metodoDePago = m;
    }
    public void setMonto(double precio){
        this.monto  = precio;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setCbu(String cbu){
        this.cbu = cbu;
    }
    public void setCuit(long cuit){
        this.cuit = cuit;
    }
    public void setAlias(String alias){
        this.alias = alias;
    }
    public void setPagado(boolean pagado){
        this.pagado = pagado;
    }
    //-------------------------------------------------------------------------------------------------

    //metodos------------------------------------------------------------------------------------------
    public void pagar(){
        metodoDePago.obtenerInformacion(this);


    }
    //-------------------------------------------------------------------------------------------------


}
