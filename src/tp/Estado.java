package tp;

public abstract class Estado {

    //constructores
    public Estado(){super();}

    //metodos
    public abstract TipoEstado getEstado();
    public abstract Estado siguiente();
    public abstract String stringEstado();

}
