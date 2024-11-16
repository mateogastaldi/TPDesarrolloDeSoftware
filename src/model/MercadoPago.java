package model;

import gui.pedido.pago.InterfazPagoMercadoPago;

import java.util.Scanner;

public class MercadoPago implements PagoStrategy{
    @Override
    public MediosDePagos getMedioDePago() {
        return MediosDePagos.MERCADO_PAGO;
    }
    @Override
    public double precio(double precio) {
        return precio * 1.04;
    }
    @Override
    public void obtenerInformacion(Pago pago) {
        System.out.println("Vas a pagar con " + this.getMedioDePago());
        InterfazPagoMercadoPago interfazPago = new InterfazPagoMercadoPago(pago);
        interfazPago.setVisible(true);

    }

}
