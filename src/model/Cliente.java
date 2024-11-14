package model;
import javax.swing.*;

public class Cliente implements EventListener {

    //Atributos---------------------------------------------------------------------------------------
    private int id;
    private String nombre;
    private long cuit;
    private String email;
    private Direccion direccion;
    private Coordenada coordenadas;
    // -----------------------------------------------------------------------------------------------

    // Constructor -----------------------------------------------------------------------------------
    public Cliente(String nombre, long cuit, String email, Direccion direccion, Coordenada coordenadas) {
        setNombre(nombre);
        setCuit(cuit);
        setEmail(email);
        setDireccion(direccion);
        setCoordenadas(coordenadas);
    }
    // -----------------------------------------------------------------------------------------------

    // Getters ---------------------------------------------------------------------------------------
    
    public int getId() {return id;}
    public long getCuit() {return cuit;}
    public String getEmail() {return email;}
    public Direccion getDireccion() {return direccion;}
    public Coordenada getCoordenadas() {return coordenadas;}
    public String getNombre() {return nombre;}
    // -----------------------------------------------------------------------------------------------

    // Setters ---------------------------------------------------------------------------------------
    public void setId(int id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setCuit(long cuit) {this.cuit = cuit;}
    public void setEmail(String email) {this.email = email;}
    public void setDireccion(Direccion direccion) {this.direccion = direccion;}
    public void setCoordenadas(Coordenada coordenadas) {this.coordenadas = coordenadas;}
    // -----------------------------------------------------------------------------------------------

    // Metodos ---------------------------------------------------------------------------------------
    public void printAll(){
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Cuit: " + getCuit());
        System.out.println("Email: " + getEmail());
        System.out.println("Direccion(" + "Calle:" + this.direccion.getCalle() + ", Altura:" + this.direccion.getAltura() + ", Ciudad:" + this.direccion.getCiudad() + ", Pais:" + this.direccion.getPais()  +")");
        System.out.println("Coordenadas(" + "Lat:" + this.coordenadas.getLat() + ", Lng:" + this.coordenadas.getLng()+")");

    }
    public boolean nombreIgual(String nom){return this.getNombre().equalsIgnoreCase(nom);}
    public boolean cuitIgual(long cuit){return this.getCuit() == cuit;}
    public boolean idIgual(int id){return this.getId() == id;}
    public void modificarAtributos(String nombre, long cuit, Direccion direccion,String email,Coordenada coordenadas){

        if(!nombre.equals("")) setNombre(nombre);
        if(cuit != -1) setCuit(cuit);
        setDireccion(direccion);
        setCoordenadas(coordenadas);
    }

    @Override
    public void update(Pedido p) {
        if(p.getEstado().equals(TipoEstado.ENVIADO)){
            JOptionPane.showMessageDialog(null, "El pedido se ha actualizado.\n Estado del Pedido:" + p.getEstado().stringEstado() + "\nPago generado");
        }
        else{
            JOptionPane.showMessageDialog(null, "El pedido se ha actualizado.\n Estado del Pedido:" + p.getEstado().stringEstado());
        }
    }

    public void modificarCliente(String nombre, String cuit, String email, Direccion direccion, Coordenada coordenadas){
        setNombre(nombre);
        setCuit(Long.parseLong(cuit));
        setEmail(email);
        setDireccion(direccion);
        setCoordenadas(coordenadas);
    }
    // -----------------------------------------------------------------------------------------------
}
