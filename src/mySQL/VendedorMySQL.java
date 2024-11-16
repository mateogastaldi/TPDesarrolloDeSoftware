package mySQL;
import DAO.VendedorDAO;
import exceptions.vendedor.VendedorNoEncontradoException;
import model.*;
import java.sql.*;
import java.util.*;

public class VendedorMySQL implements VendedorDAO {
    // Singleton --------------------------------------------------------------------------------------
    private static VendedorMySQL VENDEDORMYSQL_INSTANCE;
    private VendedorMySQL() {}
    public static VendedorMySQL getInstance() {
        if (VENDEDORMYSQL_INSTANCE == null) {
            VENDEDORMYSQL_INSTANCE = new VendedorMySQL();
        }
        return VENDEDORMYSQL_INSTANCE;
    }
    // ------------------------------------------------------------------------------------------------

    // Metodos ----------------------------------------------------------------------------------------
    @Override
    public List<Vendedor> getVendedores() throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        Connection MySql = ConexionMySQL.conectar();
        try (
                PreparedStatement pstmt = MySql.prepareStatement("SELECT * FROM vendedor");
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Direccion direccion = new Direccion(rs.getString("calle"), rs.getInt("altura"), rs.getString("ciudad"), rs.getString("pais"));
                Coordenada coordenada = new Coordenada(rs.getDouble("lat"), rs.getDouble("lng"));
                Vendedor vendedor = new Vendedor(nombre, direccion, coordenada);
                vendedor.setId(id);
                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un cliente: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return vendedores;
    }

    @Override
    public void addVendedor(Vendedor vendedor) {
        Connection MySql = ConexionMySQL.conectar();
        String query = "INSERT INTO vendedor(nombre, altura, calle, ciudad, pais, lat, lng) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try (
            PreparedStatement pstmt = MySql.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, vendedor.getNombre());
            pstmt.setInt(2, vendedor.getDireccion().getAltura());
            pstmt.setString(3, vendedor.getDireccion().getCalle());
            pstmt.setString(4, vendedor.getDireccion().getCiudad());
            pstmt.setString(5, vendedor.getDireccion().getPais());
            pstmt.setDouble(6, vendedor.getCoordenadas().getLat());
            pstmt.setDouble(7, vendedor.getCoordenadas().getLng());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        vendedor.setId(generatedId);
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
    public List<Vendedor> filtrarVendedorPorNombre(String nombreAUX) throws VendedorNoEncontradoException {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        Connection MySql = ConexionMySQL.conectar();
        try (
            PreparedStatement pstmt = MySql.prepareStatement("SELECT * FROM vendedor WHERE nombre = ?");
        ) {
            pstmt.setString(1, nombreAUX);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombreVendedor = rs.getString("nombre");
                    Direccion direccion = new Direccion(rs.getString("calle"), rs.getInt("altura"), rs.getString("ciudad"), rs.getString("pais"));
                    Coordenada coordenada = new Coordenada(rs.getDouble("lat"), rs.getDouble("lng"));
                    Vendedor vendedor = new Vendedor(nombreVendedor, direccion, coordenada);
                    vendedor.setId(id);
                    vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un vendedor con el nombre seleccionado: " + e.getMessage());
            throw new VendedorNoEncontradoException("Error al obtener un vendedor con el nombre seleccionado: " + e.getMessage());
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return vendedores;
    }

    @Override
    public Vendedor filtrarVendedorPorId(int id) throws VendedorNoEncontradoException {
        Vendedor vendedor = null;
        Connection MySql = ConexionMySQL.conectar();
        try (
            PreparedStatement pstmt = MySql.prepareStatement("SELECT * FROM vendedor WHERE id = ?")
        ) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                Direccion direccion = new Direccion(rs.getString("calle"), rs.getInt("altura"), rs.getString("ciudad"),rs.getString("pais"));
                Coordenada coordenada = new Coordenada(rs.getDouble("lat"), rs.getDouble("lng"));
                vendedor = new Vendedor(nombre, direccion, coordenada);
                vendedor.setId(id);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un vendedor con el id seleccionado: " + e.getMessage());
            throw new VendedorNoEncontradoException("Error al obtener un vendedor con el id seleccionado: " + e.getMessage());
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return vendedor;
    }

    @Override
    public void eliminarVendedor(int id) throws SQLException {
        Connection conex = ConexionMySQL.conectar();
        try (
                PreparedStatement pstmt = conex.prepareStatement("DELETE FROM vendedor WHERE id = " + id);) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar vendedor: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }
    }

    @Override
    public void modificarVendedor(int id, String nombre, Direccion direccion, Coordenada coordenadas) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        String query = "UPDATE vendedor SET nombre = ?, altura = ?, calle = ?, ciudad = ?, pais = ?, lat = ?, lng = ? WHERE id = " + id;
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, direccion.getAltura());
            pstmt.setString(3, direccion.getCalle());
            pstmt.setString(4, direccion.getCiudad());
            pstmt.setString(5, direccion.getPais());
            pstmt.setDouble(6, coordenadas.getLat());
            pstmt.setDouble(7, coordenadas.getLng());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al modificar vendedor: " + e.getMessage());
        } finally {
            ConexionMySQL.cerrarConexion();
        }
    }
    // ------------------------------------------------------------------------------------------------

}