package tp;

import java.util.Scanner;

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
    public void obtenerInformacion() {
        System.out.println("Vas a pagar con: " + this.getMedioDePago());
    }
}
