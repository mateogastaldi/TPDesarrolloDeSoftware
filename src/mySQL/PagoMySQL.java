package mySQL;

import DAO.PagoDAO;
import model.Efectivo;
import model.MercadoPago;
import model.Pago;
import model.PagoStrategy;
import model.Transferencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoMySQL implements PagoDAO {
   //UTILIZAMOS PATRON SINGLETON PARA LA CREACION DEL MEMORY
   private static PagoMySQL PAGO_INSTANCE;


   //constructores
   private PagoMySQL() {
   }

   //getters
  
   public static PagoMySQL getInstance() {
       if (PAGO_INSTANCE == null) {
           PAGO_INSTANCE = new PagoMySQL();
       }
       return PAGO_INSTANCE;
   }

   


   //metodos
   @Override
   public List<Pago> getPagos() throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pago> pagos = new ArrayList<>();
        try(PreparedStatement prst = con.prepareStatement("SELECT * FROM pagos"); ResultSet rs = prst.executeQuery()){
            while(rs.next()){
                int id = rs.getInt("id");
                double monto = rs.getDouble("monto");
                PagoStrategy metodo = null;
                if(rs.getString("metodo").equalsIgnoreCase("EFECTIVO")){
                    metodo = new Efectivo();
                }else if(rs.getString("metodo").equalsIgnoreCase("MERCADOPAGO")){
                    metodo = new MercadoPago();
                }else if(rs.getString("metodo").equalsIgnoreCase("TARJETA")){
                    metodo = new Transferencia();
                }
                Pago p = new Pago(id,metodo,monto);

                pagos.add(p);
            }
        }
        catch (Exception e){
            throw new SQLException("Error al obtener los pagos");
        }
        ConexionMySQL.cerrarConexion();
        return pagos;

            
   }
   public void addPago(Pago p) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        try(PreparedStatement prst = con.prepareStatement("INSERT INTO pagos (monto,metodo) VALUES (?,?)")){
            prst.setDouble(1,p.getMonto());
            if(p.getMetodoDePago() instanceof Efectivo){
                prst.setString(2,"EFECTIVO");
            }else if(p.getMetodoDePago() instanceof MercadoPago){
                prst.setString(2,"MERCADOPAGO");
            }else{
                prst.setString(2,"TRANSFERENCIA");
            }
            prst.executeUpdate();
        }
        catch (Exception e){
            throw new SQLException("Error al agregar el pago");
        }
        ConexionMySQL.cerrarConexion();
   }

   public Pago filtrarPagoPorId(int id) throws SQLException{
        Connection con = ConexionMySQL.conectar();
        Pago p = null;
        try(PreparedStatement prst = con.prepareStatement("SELECT * FROM pagos WHERE id = ?")){
            prst.setInt(1,id);
            ResultSet rs = prst.executeQuery();
            if(rs.next()){
                double monto = rs.getDouble("monto");
                PagoStrategy metodo = null;
                if(rs.getString("metodo").equalsIgnoreCase("EFECTIVO")){
                    metodo = new Efectivo();
                }else if(rs.getString("metodo").equalsIgnoreCase("MERCADOPAGO")){
                    metodo = new MercadoPago();
                }else if(rs.getString("metodo").equalsIgnoreCase("TARJETA")){
                    metodo = new Transferencia();
                }
                p = new Pago(id,metodo,monto);
            }
        }
        catch (Exception e){
            throw new SQLException("Error al filtrar el pago");
        }
        ConexionMySQL.cerrarConexion();
        return p;
   }
}
