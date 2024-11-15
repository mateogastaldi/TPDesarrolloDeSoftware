
package mySQL;

import DAO.PedidosDAO;
import model.*;
import java.sql.*;
import java.util.*;

public class PedidosMySQL implements PedidosDAO {
    // Singleton --------------------------------------------------------------------------------------
    private static PedidosMySQL instance;
    private PedidosMySQL() {}
    public static PedidosMySQL getInstance() {
        if (instance == null) {
            instance = new PedidosMySQL();
        }
        return instance;
    }
    // ------------------------------------------------------------------------------------------------

    // Métodos ----------------------------------------------------------------------------------------
    public List<Pedido> getPedido() throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pedido> pedidos = new ArrayList<>();
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido,cliente,vendedor,pago WHERE pedido.cliente=cliente.id AND pedido.vendedor=vendedor.id AND pago.id = pedido.pago");
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("pedido.id");
                int idVendedor = rs.getInt("pedido.vendedor");
                int idCliente = rs.getInt("pedido.idCliente");
                Cliente cliente = null;
                Vendedor vendedor = null;
                Pago pago = null;
                
                Estado estado = null;
                if (rs.getString("pedido.estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("pedido.estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("pedido.estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("pedido.estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                //atributos pago
                int idpago = rs.getInt("pago.id");
                double monto = rs.getDouble("pago.monto");	
                PagoStrategy metodoDePago = null;
                if (rs.getString("pago.metodoDePago").equalsIgnoreCase("EFECTIVO")) {
                    metodoDePago = new Efectivo();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("MERCADO PAGO")) {
                    metodoDePago = new MercadoPago();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("TRANSFERENCIA")) {
                    metodoDePago = new Transferencia();
                }
                pago = new Pago(metodoDePago, monto);
                pago.setId(idpago);
                
                //Atributos cliente
                String nombreCliente = rs.getString("cliente.nombre");
                long cuitCliente = rs.getLong("cliente.cuit");
                String emailCliente = rs.getString("cliente.email");
                String calleCliente = rs.getString("cliente.calle");
                int alturaCliente = rs.getInt("cliente.altura");
                String ciudadCliente = rs.getString("cliente.ciudad");
                String paisCliente = rs.getString("cliente.pais");
                double latitudCliente = rs.getDouble("cliente.latitud");
                double longitudCliente = rs.getDouble("cliente.longitud");
                
                //Atributos vendedor
                String nombreVendedor = rs.getString("vendedor.nombre");
                double latitudVendedor = rs.getDouble("vendedor.latitud");
                double longitudVendedor = rs.getDouble("vendedor.longitud");
                String calleVendedor = rs.getString("vendedor.calle");
                int alturaVendedor = rs.getInt("vendedor.altura");
                String ciudadVendedor = rs.getString("vendedor.ciudad");
                String paisVendedor = rs.getString("vendedor.pais");

                Coordenada coordenadaVendedor = new Coordenada(latitudVendedor, longitudVendedor);
                Coordenada coordenadaCliente = new Coordenada(latitudCliente, longitudCliente);
                Direccion direccionVendedor = new Direccion(calleVendedor, alturaVendedor, ciudadVendedor, paisVendedor);
                Direccion direccionCliente = new Direccion(calleCliente, alturaCliente, ciudadCliente, paisCliente);
                
                cliente = new Cliente(nombreCliente, cuitCliente, emailCliente, direccionCliente, coordenadaCliente);
                vendedor = new Vendedor(nombreVendedor, direccionVendedor, coordenadaVendedor);
                
                cliente.setId(idCliente);
                vendedor.setId(idVendedor);

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

    public Pedido filtrarPedidoPorId(int idAUX) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        Pedido pedido = null;
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido, cliente, vendedor, pago WHERE pedido.id_cliente = cliente.id AND pedido.id_vendedor = vendedor.id AND pedido.pago = pago.id AND pedido.id = " + idAUX);
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("pedido.id");
                int idVendedor = rs.getInt("pedido.vendedor");
                int idCliente = rs.getInt("pedido.idCliente");
                Cliente cliente = null;
                Vendedor vendedor = null;
                Pago pago = null;
                
                Estado estado = null;
                if (rs.getString("pedido.estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("pedido.estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("pedido.estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("pedido.estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                //atributos pago
                int idpago = rs.getInt("pago.id");
                double monto = rs.getDouble("pago.monto");	
                PagoStrategy metodoDePago = null;
                if (rs.getString("pago.metodoDePago").equalsIgnoreCase("EFECTIVO")) {
                    metodoDePago = new Efectivo();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("MERCADO PAGO")) {
                    metodoDePago = new MercadoPago();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("TRANSFERENCIA")) {
                    metodoDePago = new Transferencia();
                }
                pago = new Pago(metodoDePago, monto);
                pago.setId(idpago);
                
                //Atributos cliente
                String nombreCliente = rs.getString("cliente.nombre");
                long cuitCliente = rs.getLong("cliente.cuit");
                String emailCliente = rs.getString("cliente.email");
                String calleCliente = rs.getString("cliente.calle");
                int alturaCliente = rs.getInt("cliente.altura");
                String ciudadCliente = rs.getString("cliente.ciudad");
                String paisCliente = rs.getString("cliente.pais");
                double latitudCliente = rs.getDouble("cliente.latitud");
                double longitudCliente = rs.getDouble("cliente.longitud");
                
                //Atributos vendedor
                String nombreVendedor = rs.getString("vendedor.nombre");
                double latitudVendedor = rs.getDouble("vendedor.latitud");
                double longitudVendedor = rs.getDouble("vendedor.longitud");
                String calleVendedor = rs.getString("vendedor.calle");
                int alturaVendedor = rs.getInt("vendedor.altura");
                String ciudadVendedor = rs.getString("vendedor.ciudad");
                String paisVendedor = rs.getString("vendedor.pais");

                Coordenada coordenadaVendedor = new Coordenada(latitudVendedor, longitudVendedor);
                Coordenada coordenadaCliente = new Coordenada(latitudCliente, longitudCliente);
                Direccion direccionVendedor = new Direccion(calleVendedor, alturaVendedor, ciudadVendedor, paisVendedor);
                Direccion direccionCliente = new Direccion(calleCliente, alturaCliente, ciudadCliente, paisCliente);
                
                cliente = new Cliente(nombreCliente, cuitCliente, emailCliente, direccionCliente, coordenadaCliente);
                vendedor = new Vendedor(nombreVendedor, direccionVendedor, coordenadaVendedor);
                
                cliente.setId(idCliente);
                vendedor.setId(idVendedor);

                pedido = new Pedido( cliente, vendedor, pago, estado);
                pedido.setId(id);
            }
        } catch (Exception e) {
            throw new SQLException("Error al obtener pedido: " + e.getMessage());
        }
        ConexionMySQL.cerrarConexion();
        return pedido;
    }

    public List<Pedido> filtrarPorNombreCliente(String nombreAUX) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pedido> pedidos = new ArrayList<>();
        
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido,cliente,vendedor,pago WHERE pedido.id_cliente=cliente.id AND pedido.id_vendedor = vendedor.id AND pedido.pago = pago.id AND cliente.nombre = " + nombreAUX);
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("pedido.id");
                int idVendedor = rs.getInt("pedido.vendedor");
                int idCliente = rs.getInt("pedido.idCliente");
                Cliente cliente = null;
                Vendedor vendedor = null;
                Pago pago = null;
                
                Estado estado = null;
                if (rs.getString("pedido.estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("pedido.estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("pedido.estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("pedido.estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                //atributos pago
                int idpago = rs.getInt("pago.id");
                double monto = rs.getDouble("pago.monto");	
                PagoStrategy metodoDePago = null;
                if (rs.getString("pago.metodoDePago").equalsIgnoreCase("EFECTIVO")) {
                    metodoDePago = new Efectivo();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("MERCADO PAGO")) {
                    metodoDePago = new MercadoPago();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("TRANSFERENCIA")) {
                    metodoDePago = new Transferencia();
                }
                pago = new Pago(metodoDePago, monto);
                pago.setId(idpago);
                
                //Atributos cliente
                String nombreCliente = rs.getString("cliente.nombre");
                long cuitCliente = rs.getLong("cliente.cuit");
                String emailCliente = rs.getString("cliente.email");
                String calleCliente = rs.getString("cliente.calle");
                int alturaCliente = rs.getInt("cliente.altura");
                String ciudadCliente = rs.getString("cliente.ciudad");
                String paisCliente = rs.getString("cliente.pais");
                double latitudCliente = rs.getDouble("cliente.latitud");
                double longitudCliente = rs.getDouble("cliente.longitud");
                
                //Atributos vendedor
                String nombreVendedor = rs.getString("vendedor.nombre");
                double latitudVendedor = rs.getDouble("vendedor.latitud");
                double longitudVendedor = rs.getDouble("vendedor.longitud");
                String calleVendedor = rs.getString("vendedor.calle");
                int alturaVendedor = rs.getInt("vendedor.altura");
                String ciudadVendedor = rs.getString("vendedor.ciudad");
                String paisVendedor = rs.getString("vendedor.pais");

                Coordenada coordenadaVendedor = new Coordenada(latitudVendedor, longitudVendedor);
                Coordenada coordenadaCliente = new Coordenada(latitudCliente, longitudCliente);
                Direccion direccionVendedor = new Direccion(calleVendedor, alturaVendedor, ciudadVendedor, paisVendedor);
                Direccion direccionCliente = new Direccion(calleCliente, alturaCliente, ciudadCliente, paisCliente);
                
                cliente = new Cliente(nombreCliente, cuitCliente, emailCliente, direccionCliente, coordenadaCliente);
                vendedor = new Vendedor(nombreVendedor, direccionVendedor, coordenadaVendedor);
                
                cliente.setId(idCliente);
                vendedor.setId(idVendedor);

                Pedido pedido = new Pedido( cliente, vendedor, pago, estado);
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

    public List<Pedido> filtrarPorIdCliente(int idAUX) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pedido> pedidos = new ArrayList<>();
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido, cliente, vendedor, pago WHERE pedido.id_cliente=cliente.id AND pedido.id_vendedor = vendedor.id AND pedido.pago = pago.id AND pedido.id_cliente = " + idAUX);
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("pedido.id");
                int idVendedor = rs.getInt("pedido.vendedor");
                int idCliente = rs.getInt("pedido.idCliente");
                Cliente cliente = null;
                Vendedor vendedor = null;
                Pago pago = null;
                
                Estado estado = null;
                if (rs.getString("pedido.estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("pedido.estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("pedido.estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("pedido.estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                //atributos pago
                int idpago = rs.getInt("pago.id");
                double monto = rs.getDouble("pago.monto");	
                PagoStrategy metodoDePago = null;
                if (rs.getString("pago.metodoDePago").equalsIgnoreCase("EFECTIVO")) {
                    metodoDePago = new Efectivo();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("MERCADO PAGO")) {
                    metodoDePago = new MercadoPago();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("TRANSFERENCIA")) {
                    metodoDePago = new Transferencia();
                }
                pago = new Pago(metodoDePago, monto);
                pago.setId(idpago);
                
                //Atributos cliente
                String nombreCliente = rs.getString("cliente.nombre");
                long cuitCliente = rs.getLong("cliente.cuit");
                String emailCliente = rs.getString("cliente.email");
                String calleCliente = rs.getString("cliente.calle");
                int alturaCliente = rs.getInt("cliente.altura");
                String ciudadCliente = rs.getString("cliente.ciudad");
                String paisCliente = rs.getString("cliente.pais");
                double latitudCliente = rs.getDouble("cliente.latitud");
                double longitudCliente = rs.getDouble("cliente.longitud");
                
                //Atributos vendedor
                String nombreVendedor = rs.getString("vendedor.nombre");
                double latitudVendedor = rs.getDouble("vendedor.latitud");
                double longitudVendedor = rs.getDouble("vendedor.longitud");
                String calleVendedor = rs.getString("vendedor.calle");
                int alturaVendedor = rs.getInt("vendedor.altura");
                String ciudadVendedor = rs.getString("vendedor.ciudad");
                String paisVendedor = rs.getString("vendedor.pais");

                Coordenada coordenadaVendedor = new Coordenada(latitudVendedor, longitudVendedor);
                Coordenada coordenadaCliente = new Coordenada(latitudCliente, longitudCliente);
                Direccion direccionVendedor = new Direccion(calleVendedor, alturaVendedor, ciudadVendedor, paisVendedor);
                Direccion direccionCliente = new Direccion(calleCliente, alturaCliente, ciudadCliente, paisCliente);
                
                cliente = new Cliente(nombreCliente, cuitCliente, emailCliente, direccionCliente, coordenadaCliente);
                vendedor = new Vendedor(nombreVendedor, direccionVendedor, coordenadaVendedor);
                
                cliente.setId(idCliente);
                vendedor.setId(idVendedor);

                Pedido pedido = new Pedido( cliente, vendedor, pago, estado);
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

    public void eliminarPedido(int id) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        try (PreparedStatement prst = con.prepareStatement("DELETE FROM pedido WHERE id = " + id)) {
            prst.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("Error al eliminar pedido: " + e.getMessage());
        }
        ConexionMySQL.cerrarConexion();
    }

    public List<Pedido> filtrarPedidoPorVendedor(String vendedorNombreAUX) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        List<Pedido> pedidos = new ArrayList<>();
        
        try (PreparedStatement prst = con.prepareStatement("SELECT * FROM pedido, cliente, vendedor, pago WHERE pedido.id_cliente=cliente.id AND pedido.id_vendedor = vendedor.id AND pedido.pago = pago.id vendedor.nombre = " + vendedorNombreAUX);
                ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("pedido.id");
                int idVendedor = rs.getInt("pedido.vendedor");
                int idCliente = rs.getInt("pedido.idCliente");
                Cliente cliente = null;
                Vendedor vendedor = null;
                Pago pago = null;
                
                Estado estado = null;
                if (rs.getString("pedido.estado").equals("RECIBIDO")) {
                    estado = new EstadoRECIBIDO();
                } else if (rs.getString("pedido.estado").equals("ENVIADO")) {
                    estado = new EstadoENVIADO();
                } else if (rs.getString("pedido.estado").equals("PREPARADO")) {
                    estado = new EstadoPREPARADO();
                } else if (rs.getString("pedido.estado").equals("ACEPTADO")) {
                    estado = new EstadoACEPTADO();
                }
                //atributos pago
                int idpago = rs.getInt("pago.id");
                double monto = rs.getDouble("pago.monto");	
                PagoStrategy metodoDePago = null;
                if (rs.getString("pago.metodoDePago").equalsIgnoreCase("EFECTIVO")) {
                    metodoDePago = new Efectivo();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("MERCADO PAGO")) {
                    metodoDePago = new MercadoPago();
                } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("TRANSFERENCIA")) {
                    metodoDePago = new Transferencia();
                }
                pago = new Pago(metodoDePago, monto);
                pago.setId(idpago);
                
                //Atributos cliente
                String nombreCliente = rs.getString("cliente.nombre");
                long cuitCliente = rs.getLong("cliente.cuit");
                String emailCliente = rs.getString("cliente.email");
                String calleCliente = rs.getString("cliente.calle");
                int alturaCliente = rs.getInt("cliente.altura");
                String ciudadCliente = rs.getString("cliente.ciudad");
                String paisCliente = rs.getString("cliente.pais");
                double latitudCliente = rs.getDouble("cliente.latitud");
                double longitudCliente = rs.getDouble("cliente.longitud");
                
                //Atributos vendedor
                String nombreVendedor = rs.getString("vendedor.nombre");
                double latitudVendedor = rs.getDouble("vendedor.latitud");
                double longitudVendedor = rs.getDouble("vendedor.longitud");
                String calleVendedor = rs.getString("vendedor.calle");
                int alturaVendedor = rs.getInt("vendedor.altura");
                String ciudadVendedor = rs.getString("vendedor.ciudad");
                String paisVendedor = rs.getString("vendedor.pais");

                Coordenada coordenadaVendedor = new Coordenada(latitudVendedor, longitudVendedor);
                Coordenada coordenadaCliente = new Coordenada(latitudCliente, longitudCliente);
                Direccion direccionVendedor = new Direccion(calleVendedor, alturaVendedor, ciudadVendedor, paisVendedor);
                Direccion direccionCliente = new Direccion(calleCliente, alturaCliente, ciudadCliente, paisCliente);
                
                cliente = new Cliente(nombreCliente, cuitCliente, emailCliente, direccionCliente, coordenadaCliente);
                vendedor = new Vendedor(nombreVendedor, direccionVendedor, coordenadaVendedor);
                
                cliente.setId(idCliente);
                vendedor.setId(idVendedor);

                Pedido pedido = new Pedido( cliente, vendedor, pago, estado);
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
    // ------------------------------------------------------------------------------------------------
}
