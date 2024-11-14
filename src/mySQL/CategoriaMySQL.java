package mySQL;

import DAO.CategoriaDAO;

import model.Bebida;
import model.Categoria;
import model.ItemMenu;
import model.Plato;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class CategoriaMySQL implements CategoriaDAO {

    // Singleton -------------------------------------------------------------------------------------
    private static CategoriaMySQL CATEGORIAMYSQL_INSTANCE;

    private CategoriaMySQL() {
    }

    public static CategoriaMySQL getInstance() {
        if (CATEGORIAMYSQL_INSTANCE == null) {
            CATEGORIAMYSQL_INSTANCE = new CategoriaMySQL();
        }
        return CATEGORIAMYSQL_INSTANCE;
    }
    // ------------------------------------------------------------------------------------------------

    // Methods Category --------------------------------------------------------------------------------
    public List<Categoria> getCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        Connection con = ConexionMySQL.conectar();

        try (
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM categoria");
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Supongamos que la tabla "categoria" tiene columnas "id" y "nombre"
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String tipo_item = rs.getString("tipo_item");

                if (tipo_item.equals("Plato")) {
                    Categoria categoria = new Categoria(nombre, Plato.class);
                    categoria.setId(id);
                    categorias.add(categoria);
                } else {
                    Categoria categoria = new Categoria(nombre, Bebida.class);
                    categoria.setId(id);
                    categorias.add(categoria);
                }

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener categorías: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }

        return categorias;
    }

    public Categoria filtrarCategoriaId(int idFiltrar) throws SQLException {
        Categoria categoria = null;
        Connection con = ConexionMySQL.conectar();
        try (
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM categoria WHERE id = " + idFiltrar);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Supongamos que la tabla "categoria" tiene columnas "id" y "nombre"
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String tipo_item = rs.getString("tipo_item");

                if (tipo_item.equals("Plato")) {
                    categoria = new Categoria(nombre, Plato.class);
                    categoria.setId(id);

                } else {
                    categoria = new Categoria(nombre, Bebida.class);
                    categoria.setId(id);

                }

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener categorías: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return categoria;

    }

    public void eliminarCategoria(int id) throws SQLException {
        Connection con = ConexionMySQL.conectar();
        try (
                PreparedStatement pstmt = con.prepareStatement("DELETE FROM categoria WHERE id = " + id);) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }

    }

    public List<Categoria> filtrarCategoriaPorNombre(String nombre) throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        Connection con = ConexionMySQL.conectar();
        try (
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM categoria WHERE descripcion = " + nombre);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Supongamos que la tabla "categoria" tiene columnas "id" y "nombre"
                int id = rs.getInt("id");
                String nombreCategoria = rs.getString("descripcion");
                String tipo_item = rs.getString("tipo_item");
                if (tipo_item.equals("Plato")) {
                    Categoria categoria = new Categoria(nombreCategoria, Plato.class);
                    categoria.setId(id);
                    categorias.add(categoria);
                } else {
                    Categoria categoria = new Categoria(nombreCategoria, Bebida.class);
                    categoria.setId(id);
                    categorias.add(categoria);
                }

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener categorías: " + e.getMessage());
            throw e; // Propagar la excepción si es necesario
        } finally {
            ConexionMySQL.cerrarConexion();
        }

        return categorias;
    }

    public void addCategoria(Categoria cat) {
        Connection con = ConexionMySQL.conectar();
        try (
                PreparedStatement pstmt = con.prepareStatement(
                        "INSERT INTO categoria (descripcion, tipo_item) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, cat.getDescripcion());
            pstmt.setString(2, cat.getTipoItem().getSimpleName());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cat.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar categoría: " + e.getMessage());
        } finally {
            ConexionMySQL.cerrarConexion();
        }

    }

    public <T extends ItemMenu> List<Categoria> filtrarPorTipoItem(Class<T> tipoClase) {
        List<Categoria> categorias = new ArrayList<>();
        Connection mySQL = ConexionMySQL.conectar();
        String tipoItem = tipoClase.getSimpleName();
        try (
                PreparedStatement pstmt = mySQL
                        .prepareStatement("SELECT * FROM categoria WHERE tipo_item = " + tipoItem);) {
            pstmt.setString(1, tipoClase.getSimpleName());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    if (tipoClase.equals(Plato.class)) {
                        Categoria c = new Categoria(nombre, Plato.class);
                        c.setId(id);
                        categorias.add(c);

                    } else if (tipoClase.equals(Bebida.class)) {
                        Categoria c = new Categoria(nombre, Bebida.class);
                        c.setId(id);
                        categorias.add(c);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar categorías por tipo de item: " + e.getMessage());
        }
        ConexionMySQL.cerrarConexion();
        return categorias;
    }
    // ------------------------------------------------------------------------------------------------
}
