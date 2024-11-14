package mySQL;
import DAO.CategoriaDAO;
import DAO.VendedorDAO;
import exceptions.itemMenu.categoria.CategoriaNoCreadaException;
import exceptions.vendedor.VendedorNoEncontradoException;
import model.Bebida;
import model.Categoria;
import model.Coordenada;
import model.Direccion;
import model.ItemMenu;
import model.Plato;
import model.Vendedor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VendedorMySQL implements VendedorDAO{
    // Singleton --------------------------------------------------------------
    private static VendedorMySQL VENDEDORMYSQL_INSTANCE;
    private VendedorMySQL() {}
    public static VendedorMySQL getInstance() {
        if (VENDEDORMYSQL_INSTANCE == null) {
            VENDEDORMYSQL_INSTANCE = new VendedorMySQL();
        }
        return VENDEDORMYSQL_INSTANCE;
    }
    // -------------------------------------------------------------------------
     // Método de ejemplo para obtener todos los vendedores de la base de datos
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
                Direccion direccion = new Direccion(rs.getString("calle"), rs.getInt("altura"), rs.getString("ciudad"),rs.getString("pais"));
                Coordenada coordenada = new Coordenada(rs.getDouble("latitud"), rs.getDouble("longitud"));
                Vendedor vendedor = new Vendedor(id, nombre, direccion, coordenada);
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
        try (
            PreparedStatement pstmt = MySql.prepareStatement("INSERT INTO vendedor (id, nombre, altura, calle, ciudad, pais, lat, lng) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");) {
            pstmt.setInt(1, vendedor.getId());
            pstmt.setString(2, vendedor.getNombre());
            pstmt.setInt(3, vendedor.getDireccion().getAltura());
            pstmt.setString(4, vendedor.getDireccion().getCalle());
            pstmt.setString(5, vendedor.getDireccion().getCiudad());
            pstmt.setString(6, vendedor.getDireccion().getPais());
            pstmt.setDouble(7, vendedor.getCoordenadas().getLat());
            pstmt.setDouble(8, vendedor.getCoordenadas().getLng());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        } finally {
            ConexionMySQL.cerrarConexion();
        }
    }

	@Override
	public List<Vendedor> filtrarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'filtrarVendedorPorNombre'");
	}
	@Override
	public Vendedor filtrarVendedorPorId(int id) throws VendedorNoEncontradoException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'filtrarVendedorPorId'");
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
    public void modificarVendedor(int id, String nombre, Direccion direccion,Coordenada coordenadas) throws ClienteNoEncontradoException {

        Connection con = ConexionMySQL.conectar();

        try (PreparedStatement pstmt = con.prepareStatement("UPDATE vendedor SET nombre = ?, altura = ?, ciudad = ?,ciudad = ?,pais = ?, lat = ?, lng = ? WHERE id = "+ id)) {
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

}