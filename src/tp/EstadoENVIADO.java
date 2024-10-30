package tp;

public class EstadoENVIADO extends Estado {

    //constructores
    public EstadoENVIADO() {super();}

    //metodos
    public TipoEstado getEstado() {
        return TipoEstado.ENVIADO;
    }
    public Estado siguiente() {
        return this;
    }

    @Override
    public String stringEstado() {
        return "ENVIADO";
    }
}
