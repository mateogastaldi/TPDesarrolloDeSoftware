package model;

public class Coordenada {
    //atributos
    private double lat;
    private double lng;

    //constructor
    public Coordenada(double lat, double lng) {
        setLat(lat);
        setLng(lng);
    }

    //getters-setters
    public double getLat() {return lat;}
    public double getLng() {return lng;}

    private void setLat(double lat) {this.lat = lat;}
    private void setLng(double lng) {this.lng = lng;}
}

