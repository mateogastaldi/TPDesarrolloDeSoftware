package model;

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
    public void obtenerInformacion() {
        System.out.println("Vas a pagar con: " + this.getMedioDePago() + ", ingrese su CUIT:");
        Scanner sc = new Scanner(System.in);
        String cuit = sc.nextLine();
        System.out.println("Ingrese el CBU:");
        String cbu = sc.nextLine();


    }
}
