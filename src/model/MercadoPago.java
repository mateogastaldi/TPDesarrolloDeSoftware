package model;

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
    public void obtenerInformacion() {
        System.out.println("Vas a pagar con " + this.getMedioDePago() + " ingresar alias:");
        Scanner sc = new Scanner(System.in);
        String alias = sc.nextLine();

    }

}
