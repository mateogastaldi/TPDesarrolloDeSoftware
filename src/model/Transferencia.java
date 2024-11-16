package model;

import gui.pedido.pago.InterfazPagoTRANSFERENCIA;

import java.util.Scanner;

public class Transferencia implements PagoStrategy{

    @Override
    public MediosDePagos getMedioDePago() {
        return MediosDePagos.TRANSFERENCIA;
    }
    @Override
    public double precio(double precio) {
        return precio * 1.02;
    }
    @Override
    public void obtenerInformacion(Pago pago) {
        System.out.println("Vas a pagar con: " + this.getMedioDePago() );
        InterfazPagoTRANSFERENCIA interfaz = new InterfazPagoTRANSFERENCIA(pago);
        interfaz.setVisible(true);


    }
}
