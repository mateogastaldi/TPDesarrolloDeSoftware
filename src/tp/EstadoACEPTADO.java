package tp;

public class EstadoACEPTADO extends Estado{

    //constructores
    public EstadoACEPTADO() {super();}

    //metodos
    @Override
    public Estado siguiente() {
        return new EstadoPREPARADO();
    }
    @Override
    public TipoEstado getEstado() {
        return TipoEstado.ACEPTADO;
    }

    @Override
    public String stringEstado() {
        return "ACEPTADO";
    }
}
