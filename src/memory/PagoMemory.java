package memory;

import DAO.PagoDAO;
import model.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoMemory implements PagoDAO {
    //UTILIZAMOS PATRON SINGLETON PARA LA CREACION DEL MEMORY
    private static PagoMemory PAGO_INSTANCE;
    private List<Pago> pagos;

    //constructores
    private PagoMemory() {
        pagos = new ArrayList<>();
    }
    private PagoMemory(List<Pago> pagos) {
        setPagos(pagos);
    }

    //getters
    public List<Pago> getPagos() {
        return this.pagos;
    }
    public static PagoMemory getInstance() {
        if (PAGO_INSTANCE == null) {
            PAGO_INSTANCE = new PagoMemory();
        }
        return PAGO_INSTANCE;
    }

    //setters
    private void setPagos(List<Pago> p ){this.pagos = p;}


    //metodos
    @Override
    public void addPago(Pago p) {
        this.pagos.add(p);
    }
}
