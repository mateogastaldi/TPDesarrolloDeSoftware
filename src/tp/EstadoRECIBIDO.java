package tp;

public class EstadoRECIBIDO extends Estado {

    //constructores
    public EstadoRECIBIDO() {super();}

    //metodos
    public TipoEstado getEstado(){
        return TipoEstado.RECIBIDO;
    }
    public Estado siguiente(){
        return new EstadoACEPTADO();
    }

    @Override
    public String stringEstado() {
        return "RECIBIDO";
    }
}
