package model;

public interface PagoStrategy {

    public MediosDePagos getMedioDePago();
    public double precio(double precio);
    public void obtenerInformacion(Pago pago);

}
