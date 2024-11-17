package mySQL;

import DAO.ClienteDAO;
import exceptions.cliente.ClienteNoEncontradoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Coordenada;
import model.Direccion;

public class ClienteMySQL implements ClienteDAO {

    // Singleton
    // -------------------------------------------------------------------------------------
    private static ClienteMySQL CLIENTEMYSQL_INSTANCE;

    private ClienteMySQL() {
    }

    public static ClienteMySQL getInstance() {
        if (CLIENTEMYSQL_INSTANCE == null) {
            CLIENTEMYSQL_INSTANCE = new ClienteMySQL();
        }
        return CLIENTEMYSQL_INSTANCE;
    }
    // -----------------------------------------------------------------------------------------------

    // Methods ClienteDao
    // ----------------------------------------------------------------------------
    @Override
    public void addCliente(Cliente cliente) {
        Connection MySql = ConexionMySQL.conectar();

        String sql = "INSERT INTO cliente (nombre, cuit, email, calle, altura, ciudad, pais, lat, lng) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                PreparedStatement pstmt = MySql.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setLong(2, cliente.getCuit());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getDireccion().getCalle());
            pstmt.setInt(5, cliente.getDireccion().getAltura());
            pstmt.setString(6, cliente.getDireccion().getCiudad());
            pstmt.setString(7, cliente.getDireccion().getPais());
            pstmt.setDouble(8, cliente.getCoordenadas().getLat());
            pstmt.setDouble(9, cliente.getCoordenadas().getLng());
            int affectedRows = pstmt.executeUpdate();
            System.out.println(
                    "Nombre: " + cliente.getNombre() + " Cuit: " + cliente.getCuit() + " Email: " + cliente.getEmail()
                            + " Direccion: " + cliente.getDireccion() + " Coordenadas: " + cliente.getCoordenadas());
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        cliente.setId(generatedId); // Asigna la clave generada al cliente
                    }
                }
            }
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
                PreparedStatement pstmt = MySql.prepareStatement("SELECT * FROM cliente WHERE nombre = ?")) {
            pstmt.setString(1, nombreAUX);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                long cuit = rs.getLong("cuit");
                String email = rs.getString("email");
                Direccion direccion = new Direccion(rs.getString("calle"), rs.getInt("altura"), rs.getString("ciudad"),
                        rs.getString("pais"));
                Coordenada coordenada = new Coordenada(rs.getDouble("lat"), rs.getDouble("lng"));
                Cliente cliente = new Cliente(nombre, cuit, email, direccion, coordenada);
                cliente.setId(id);
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
            PreparedStatement pstmt = MySql.prepareStatement("SELECT * FROM cliente WHERE id = ?");
            ) {
            pstmt.setInt(1, idAUX);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                long cuit = rs.getLong("cuit");
                String email = rs.getString("email");
                String pais = rs.getString("pais");
                String calle = rs.getString("calle");
                int altura = rs.getInt("altura");
                String ciudad = rs.getString("ciudad");
                double latitud = rs.getDouble("lat");
                double longitud = rs.getDouble("lng");
                Direccion direccion = new Direccion(calle, altura, ciudad, pais);
                Coordenada coordenada = new Coordenada(latitud, longitud);
                cliente = new Cliente(nombre, cuit, email, direccion, coordenada);
                cliente.setId(id);
            }

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
                String pais = rs.getString("pais");
                String calle = rs.getString("calle");
                int altura = rs.getInt("altura");
                String ciudad = rs.getString("ciudad");
                double latitud = rs.getDouble("lat");
                double longitud = rs.getDouble("lng");
                Direccion direccion = new Direccion(calle, altura, ciudad, pais);
                Coordenada coordenada = new Coordenada(latitud, longitud);
                Cliente cliente = new Cliente(nombre, cuit, email, direccion, coordenada);
                cliente.setId(id);
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
    public void eliminarCliente(int id) throws ClienteNoEncontradoException, SQLException {
        Connection MySql = ConexionMySQL.conectar();
        String query = "DELETE FROM cliente WHERE id = " + id;
        try (
                PreparedStatement pstmt = MySql.prepareStatement(query)) {
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new ClienteNoEncontradoException("No se encontro el itemPedido a eliminar");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }
    }

    @Override
    public void modificarCliente(int id, String nombre, long cuit, String email, Direccion direccion, Coordenada coordenadas) throws ClienteNoEncontradoException {
        Connection MySql = ConexionMySQL.conectar();
        String sql = "UPDATE cliente SET nombre = ?, cuit = ?, email = ?, altura = ?, ciudad = ?,ciudad = ?,pais = ?, lat = ?, lng = ? WHERE id = " + id;
        try (
                PreparedStatement pstmt = MySql.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setLong(2, cuit);
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

    public boolean existeCliente(long cuit,String email){
        Connection MySql = ConexionMySQL.conectar();
        String sql = "SELECT * FROM cliente WHERE email = ? OR cuit = ?";
        boolean retorno = false;
        try(PreparedStatement prst = MySql.prepareStatement(sql)){
            prst.setString(1, email);
            prst.setLong(2, cuit);
            ResultSet rs = prst.executeQuery();
            retorno = rs.next();
        }catch (SQLException e){
            System.out.println("Error al obtener el cliente: " + e.getMessage());
        }finally {
            ConexionMySQL.cerrarConexion();
        }
        return retorno;
    }
    // Methods
    // ClienteDao----------------------------------------------------------------------------

}
