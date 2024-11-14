package mySQL;

import DAO.ClienteDAO;
import exceptions.cliente.ClienteNoEncontradoException;
import model.Cliente;
import model.Coordenada;
import model.Direccion;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteMySQL implements ClienteDAO {
    // Singleton --------------------------------------------------------
    private static ClienteMySQL CLIENTEMYSQL_INSTANCE;

    private ClienteMySQL() {
    }

    public static ClienteMySQL getInstance() {
        if (CLIENTEMYSQL_INSTANCE == null) {
            CLIENTEMYSQL_INSTANCE = new ClienteMySQL();
        }
        return CLIENTEMYSQL_INSTANCE;
    }
    // ------------------------------------------------------------------

    // Methods ClienteDao -----------------------------------------------
    @Override
    public void addCliente(Cliente cliente) {
        Connection MySql = ConexionMySQL.conectar();
        try (PreparedStatement pstmt = MySql.prepareStatement("INSERT INTO cliente (id, nombre, cuit, email, calle, altura, ciudad, pais, lat, lng) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");) {
            pstmt.setInt(1, cliente.getId());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setLong(3, cliente.getCuit());
            pstmt.setString(4, cliente.getEmail());
            pstmt.setInt(5, cliente.getDireccion().getAltura());
            pstmt.setString(6, cliente.getDireccion().getCalle());
            pstmt.setString(7, cliente.getDireccion().getCiudad());
            pstmt.setString(8, cliente.getDireccion().getPais());
            pstmt.setDouble(9, cliente.getCoordenadas().getLat());
            pstmt.setDouble(10, cliente.getCoordenadas().getLng());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        } finally {
            ConexionMySQL.cerrarConexion();
        }
    }

    @Override
    public List<Cliente> filtrarClientePorNombre(String nombreAUX) throws SQLException {

        List<Cliente> clientes = new ArrayList<>();
        Connection MySql = ConexionMySQL.conectar();

        try (
                PreparedStatement pstmt = MySql.prepareStatement("SELECT * FROM cliente WHERE nombre = " + nombreAUX);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                long cuit = rs.getLong("cuit");
                String email = rs.getString("email");
                Direccion direccion = new Direccion(rs.getString("calle"), rs.getInt("altura"), rs.getString("ciudad"),
                        rs.getString("pais"));
                Coordenada coordenada = new Coordenada(rs.getDouble("lat"), rs.getDouble("lng"));
                Cliente cliente = new Cliente(id, nombre, cuit, email, direccion, coordenada);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un cliente con el nombre seleccionado: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }

        return clientes;

    }

    @Override
    public Cliente filtrarClientePorId(int idAUX) throws SQLException {

        Cliente cliente = null;
        Connection MySql = ConexionMySQL.conectar();

        try (
                PreparedStatement pstmt = MySql.prepareStatement("SELECT * FROM cliente WHERE nombre = " + idAUX);
                ResultSet rs = pstmt.executeQuery()) {

            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            long cuit = rs.getLong("cuit");
            String email = rs.getString("email");
            String pais = rs.getString("pais");
            String calle = rs.getString("calle");
            int altura = rs.getInt("altura");
            String ciudad = rs.getString("ciudad");
            double latitud = rs.getDouble("latitud");
            double longitud = rs.getDouble("longitud");
            Direccion direccion = new Direccion(calle, altura, ciudad, pais);
            Coordenada coordenada = new Coordenada(latitud, longitud);
            cliente = new Cliente(id, nombre, cuit, email, direccion, coordenada);

        } catch (SQLException e) {
            System.out.println("Error al obtener un cliente: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }

        return cliente;

    }

    @Override
    public List<Cliente> getClientes() throws SQLException {

        List<Cliente> clientes = new ArrayList<>();
        Connection MySql = ConexionMySQL.conectar();

        try (
                PreparedStatement pstmt = MySql.prepareStatement("SELECT * FROM cliente");
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                long cuit = rs.getLong("cuit");
                String email = rs.getString("email");
                Direccion direccion = new Direccion(rs.getString("calle"), rs.getInt("altura"), rs.getString("ciudad"),rs.getString("pais"));
                Coordenada coordenada = new Coordenada(rs.getDouble("latitud"), rs.getDouble("longitud"));
                Cliente cliente = new Cliente(id, nombre, cuit, email, direccion, coordenada);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener un cliente: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }

        return clientes;

    }

    @Override
    public void eliminarCliente(int id) throws SQLException {

        Connection conex = ConexionMySQL.conectar();

        try (
                PreparedStatement pstmt = conex.prepareStatement("DELETE FROM cliente WHERE id = " + id);) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }

    }

    @Override
    public void modificarCliente(int id, String nombre, String cuit, String email, Direccion direccion,Coordenada coordenadas) throws ClienteNoEncontradoException {

        Connection con = ConexionMySQL.conectar();

        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE cliente SET nombre = ?, cuit = ?, email = ?, altura = ?, ciudad = ?,ciudad = ?,pais = ?, lat = ?, lng = ? WHERE id = "
                        + id)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, cuit);
            pstmt.setString(3, email);
            pstmt.setInt(4, direccion.getAltura());
            pstmt.setString(5, direccion.getCalle());
            pstmt.setString(6, direccion.getCiudad());
            pstmt.setString(7, direccion.getPais());
            pstmt.setDouble(8, coordenadas.getLat());
            pstmt.setDouble(9, coordenadas.getLng());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        } finally {
            ConexionMySQL.cerrarConexion();
        }

    }

}
