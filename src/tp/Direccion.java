package tp;

import exceptions.cliente.direccion.DireccionNoCreadaException;

public class Direccion {
    //atributos
    private String calle;
    private int altura;
    private String ciudad;
    private String pais;

    //constructor
    public Direccion(String calle, int altura, String ciudad, String pais) {
        setCalle(calle);
        setAltura(altura);
        setCiudad(ciudad);
        setPais(pais);
    }

    //setters-getters
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }
    public String getPais() {
        return pais;
    }
    public int getAltura() {
        return altura;
    }
    public String getCalle() {
        return calle;
    }
}

