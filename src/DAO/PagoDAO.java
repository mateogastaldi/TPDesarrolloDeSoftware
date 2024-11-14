package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Pago;

public interface PagoDAO {
    void addPago(Pago p) throws SQLException;
    List<Pago> getPagos() throws SQLException;
    Pago filtrarPagoPorId(int id) throws SQLException;
}
