package mySQL;

import DAO.PagoDAO;
import model.Efectivo;
import model.MercadoPago;
import model.Pago;
import model.PagoStrategy;
import model.Transferencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoMySQL implements PagoDAO {
    // Singleton --------------------------------------------------------------------------------------
    private static PagoMySQL PAGO_INSTANCE;
    private PagoMySQL() {}
    public static PagoMySQL getInstance() {
        if (PAGO_INSTANCE == null) {
            PAGO_INSTANCE = new PagoMySQL();
        }
        return PAGO_INSTANCE;
    }
    // ------------------------------------------------------------------------------------------------

    // Métodos ----------------------------------------------------------------------------------------
    @Override
    public List<Pago> getPagos() throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pago> pagos = new ArrayList<>();
        try (
            PreparedStatement prst = con.prepareStatement("SELECT * FROM pagos"); 
            ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                double monto = rs.getDouble("monto");
                PagoStrategy metodo = null;
                if (rs.getString("metodo").equalsIgnoreCase("EFECTIVO")) {
                    metodo = new Efectivo();
                } else if (rs.getString("metodo").equalsIgnoreCase("MERCADOPAGO")) {
                    metodo = new MercadoPago();
                } else if (rs.getString("metodo").equalsIgnoreCase("TARJETA")) {
                    metodo = new Transferencia();
                }
                String alias = rs.getString("alias");
                String cbu = rs.getString("cbu");
                long cuit = rs.getLong("cuit");
                boolean pagado = rs.getBoolean("pagado");
                Pago p = new Pago( metodo, monto,pagado,cbu,cuit,alias);
                p.setId(id);
                pagos.add(p);
            }
        } catch (Exception e) {
            throw new SQLException("Error al obtener los pagos");
        }
        ConexionMySQL.cerrarConexion();
        return pagos;
    }

    public void addPago(Pago p) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        try (
            PreparedStatement prst = con.prepareStatement("INSERT INTO pago (monto,metodoDePago,alias,cbu,cuit,pagado) VALUES (?,?,null,null,0,false)",Statement.RETURN_GENERATED_KEYS)) {
            prst.setDouble(1, p.getMonto());
            if (p.getMetodoDePago() instanceof Efectivo) {
                prst.setString(2, "EFECTIVO");
            } else if (p.getMetodoDePago() instanceof MercadoPago) {
                prst.setString(2, "MERCADO PAGO");
            } else {
                prst.setString(2, "TRANSFERENCIA");
            }


            int affectedRows = prst.executeUpdate();

            if(affectedRows>0){
                try(ResultSet generatedKeys = prst.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int generatedId = generatedKeys.getInt(1);
                        p.setId(generatedId);
                    }
                }
            }
            System.out.println("No hubo error hasta nose");
        } catch (Exception e) {
            throw new SQLException("Error al agregar el pago");
        }
        finally {
            ConexionMySQL.cerrarConexion();
        }
    }

    public Pago filtrarPagoPorId(int id) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        Pago p = null;
        try (
            PreparedStatement prst = con.prepareStatement("SELECT * FROM pagos WHERE id = ?")) {
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                double monto = rs.getDouble("monto");
                PagoStrategy metodo = null;
                if (rs.getString("metodo").equalsIgnoreCase("EFECTIVO")) {
                    metodo = new Efectivo();
                } else if (rs.getString("metodo").equalsIgnoreCase("MERCADOPAGO")) {
                    metodo = new MercadoPago();
                } else if (rs.getString("metodo").equalsIgnoreCase("TARJETA")) {
                    metodo = new Transferencia();
                }
                String alias = rs.getString("alias");
                String cbu = rs.getString("cbu");
                long cuit = rs.getLong("cuit");
                boolean pagado = rs.getBoolean("pagado");
                p = new Pago( metodo, monto,pagado,cbu,cuit,alias);
                p.setId(id);
            }
        } catch (Exception e) {
            throw new SQLException("Error al filtrar el pago");
        }
        finally {
            ConexionMySQL.cerrarConexion();
        }
        return p;
    }

    public void addInfo(Pago p) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        String sql = "UPDATE pago SET pagado = ?, cuit = ?, cbu = ?, alias = ? WHERE id = ?";
        try (PreparedStatement prst = con.prepareStatement(sql)) {
            prst.setBoolean(1,p.isPagado());
            prst.setLong(2,p.getCuit());
            prst.setString(3,p.getCbu());
            prst.setString(4,p.getAlias());
            prst.setInt(5,p.getId());
            prst.executeUpdate();

        }
        catch (SQLException e) {
            throw new SQLException("Error info del pago");
        }
        finally {
            ConexionMySQL.cerrarConexion();
        }
    }



    // ------------------------------------------------------------------------------------------------
}
