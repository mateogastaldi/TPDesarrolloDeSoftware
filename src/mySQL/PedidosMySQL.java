/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package mySQL;

import DAO.PedidosDAO;
import controller.ClientesController;
import controller.VendedoresController;
import model.*;
import java.sql.*;
import java.util.*;

public class PedidosMySQL implements PedidosDAO {
    // UTILIZAMOS PATRON SINGLETON PARA LA CREACION DEL MEMORY
    private static PedidosMySQL instance;

    // constructores
    private PedidosMySQL() {
    }

    public static PedidosMySQL getInstance() {
        if (instance == null) {
            instance = new PedidosMySQL();
        }
        return instance;
    }

    // metodos
    public List<Pedido> getPedido() throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pedido> pedidos = new ArrayList<>();
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido");
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idvendedor = rs.getInt("vendedor");
                int idCliente = rs.getInt("idCliente");
                Cliente cliente = ClientesController.getInstance().filtrarClientePorId(idCliente);
                Vendedor vendedor = VendedoresController.getInstance().filtrarVendedorPorId(idvendedor);
                Estado estado = null;
                if (rs.getString("estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                int idpago = rs.getInt("pago");
                Pago pago = PagoMySQL.getInstance().filtrarPagoPorId(idpago);
                Pedido pedido = new Pedido( cliente, vendedor, pago, estado);
                pedido.setId(id);
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            throw new SQLException("Error al obtener pedidos: " + e.getMessage());
        }
        ConexionMySQL.cerrarConexion();
        return pedidos;

    }

    public void addPedido(Pedido pedido) throws SQLException{
        Connection con = ConexionMySQL.conectar();
        try (PreparedStatement prst = con
                .prepareStatement("INSERT INTO pedido (cliente, estado, pago, vendedor) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {

            prst.setInt(1, pedido.getCliente().getId());
            prst.setString(2, pedido.getEstado().stringEstado());
            prst.setInt(3, pedido.getPago().getId());
            prst.setInt(4, pedido.getVendedor().getId());
            int affectedRows=prst.executeUpdate();
            if(affectedRows>0){
                try(ResultSet generatedKeys = prst.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int generatedId = generatedKeys.getInt(1);
                        pedido.setId(generatedId);
                    }
                }
            }
        } catch (Exception e) {
            throw new SQLException("Error al agregar pedido: " + e.getMessage());
        }
        finally {
            ConexionMySQL.cerrarConexion();
        }
    }

    public Pedido filtrarPedidoPorId(int id) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        Pedido pedido = null;
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido WHERE id = " + id);
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int idvendedor = rs.getInt("vendedor");
                int idCliente = rs.getInt("idCliente");
                Cliente cliente = ClientesController.getInstance().filtrarClientePorId(idCliente);
                Vendedor vendedor = VendedoresController.getInstance().filtrarVendedorPorId(idvendedor);
                Estado estado = null;
                if (rs.getString("estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                int idpago = rs.getInt("pago");
                Pago pago = PagoMySQL.getInstance().filtrarPagoPorId(idpago);
                pedido = new Pedido( cliente, vendedor, pago, estado);
                pedido.setId(id);

            }
        } catch (Exception e) {
            throw new SQLException("Error al obtener pedido: " + e.getMessage());
        }
        ConexionMySQL.cerrarConexion();
        return pedido;
    }

    public List<Pedido> filtrarPorNombreCliente(String nombre) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pedido> pedidos = new ArrayList<>();
        Cliente c = ClientesController.getInstance().filtrarClientePorNombre(nombre).getFirst();
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido WHERE cliente = " + c.getId());
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idvendedor = rs.getInt("vendedor");
                int idCliente = rs.getInt("idCliente");
                Cliente cliente = ClientesController.getInstance().filtrarClientePorId(idCliente);
                Vendedor vendedor = VendedoresController.getInstance().filtrarVendedorPorId(idvendedor);
                Estado estado = null;
                if (rs.getString("estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                int idpago = rs.getInt("pago");
                Pago pago = PagoMySQL.getInstance().filtrarPagoPorId(idpago);
                Pedido pedido = new Pedido(cliente, vendedor, pago, estado);
                pedido.setId(id);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener pedidos: " + e.getMessage());
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return pedidos;
    }

    public List<Pedido> filtrarPorIdCliente(int id) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pedido> pedidos = new ArrayList<>();
        Cliente c = ClientesController.getInstance().filtrarClientePorId(id);
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido WHERE cliente = " + c.getId());
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int idPedido = rs.getInt("id");
                int idvendedor = rs.getInt("vendedor");
                int idCliente = rs.getInt("idCliente");
                Cliente cliente = ClientesController.getInstance().filtrarClientePorId(idCliente);
                Vendedor vendedor = VendedoresController.getInstance().filtrarVendedorPorId(idvendedor);
                Estado estado = null;
                if (rs.getString("estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                int idpago = rs.getInt("pago");
                Pago pago = PagoMySQL.getInstance().filtrarPagoPorId(idpago);
                Pedido pedido = new Pedido( cliente, vendedor, pago, estado);
                pedido.setId(idPedido);
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            throw new SQLException("Error al obtener pedidos: " + e.getMessage());
        }finally {
            ConexionMySQL.cerrarConexion();
        }
        return pedidos;
    }

    public void eliminarPedido(int id) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        try (PreparedStatement prst = con.prepareStatement("DELETE FROM pedido WHERE id = " + id)) {
            prst.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("Error al eliminar pedido: " + e.getMessage());
        }
        ConexionMySQL.cerrarConexion();
    }

    public List<Pedido> filtrarPedidoPorVendedor(String vendedor) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pedido> pedidos = new ArrayList<>();
        Vendedor v = VendedoresController.getInstance().filtrarVendedorPorNombre(vendedor).getFirst();
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido WHERE vendedor = " + v.getId());
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idvendedor = rs.getInt("vendedor");
                int idCliente = rs.getInt("idCliente");
                Cliente cliente = ClientesController.getInstance().filtrarClientePorId(idCliente);
                Vendedor vend = VendedoresController.getInstance().filtrarVendedorPorId(idvendedor);
                Estado estado = null;
                if (rs.getString("estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                int idpago = rs.getInt("pago");
                Pago pago = PagoMySQL.getInstance().filtrarPagoPorId(idpago);
                Pedido pedido = new Pedido( cliente, vend, pago, estado);
                pedido.setId(id);
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            throw new SQLException("Error al obtener pedidos: " + e.getMessage());
        }finally {
            ConexionMySQL.cerrarConexion();
        }
        return pedidos;
    }

}
