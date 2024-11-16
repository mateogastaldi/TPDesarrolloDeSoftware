package model;

import controller.PedidosController;

public class Efectivo implements PagoStrategy{

    @Override
    public MediosDePagos getMedioDePago() {
        return MediosDePagos.EFECTIVO;
    }
    @Override
    public double precio(double precio) {
        return precio;
    }
    @Override
    public void obtenerInformacion(Pago pago) {
        System.out.println("Vas a pagar con: " + this.getMedioDePago());
        pago.setPagado(true);
        try{
            PedidosController.getInstance().addInformacionPago(pago);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
