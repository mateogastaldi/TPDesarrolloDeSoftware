package mySQL;
import DAO.ItemPedidoDAO;
import exceptions.itemPedido.ItemPedidoNoEncontradoException;
import controller.*;
import model.*;
import java.sql.*;
import java.util.*;

public class ItemPedidoMySQL implements ItemPedidoDAO {

    // Singleton  ------------------------------------------------------------------------------------
    private static ItemPedidoMySQL ITEMPEDIDO_INSTANCE;
    private ItemPedidoMySQL() {}
    public static ItemPedidoMySQL getInstance() {
        if (ITEMPEDIDO_INSTANCE == null) {
            ITEMPEDIDO_INSTANCE = new ItemPedidoMySQL();
        }
        return ITEMPEDIDO_INSTANCE;
    }
    // -----------------------------------------------------------------------------------------------

    // Metodos ---------------------------------------------------------------------------------------
    @Override
    public void addItemPedido(ItemPedido itemPedido) {
        Connection MySql = ConexionMySQL.conectar();
        String sql = "INSERT INTO itempedido (id_pedido, id_item_menu) VALUES (?, ?)";
        try (PreparedStatement pstmt = MySql.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, itemPedido.getPedido().getId());
            pstmt.setInt(2, itemPedido.getItemMenu().getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        itemPedido.setId(generatedId); // Asigna la clave generada al itemPedido
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar itemPedido: " + e.getMessage());
        } finally {
            ConexionMySQL.cerrarConexion();
        }
    }


    @Override
    public List<ItemPedido> getItemsPedidos() throws SQLException {
        List<ItemPedido> itemsPedidos = new ArrayList<>();
        Connection MySql = ConexionMySQL.conectar();
        String query = "SELECT * FROM itempedido";
        try (PreparedStatement stmt = MySql.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido pedido = PedidosController.getInstance().filtrarPedidoPorId(rs.getInt("pedido"));
                ItemMenu itemMenu = ItemMenusController.getInstance().filtrarItemMenuPorId(rs.getInt("itemMenu"));    
                ItemPedido itemPedido = new ItemPedido(itemMenu, pedido);
                itemPedido.setId(rs.getInt("id"));
                itemsPedidos.add(itemPedido);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener itemsPedidos: " + e.getMessage());
            throw e;
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemsPedidos;
    }

    public ItemPedido getItemPedido(int idAux) throws SQLException {
        Connection MySql = ConexionMySQL.conectar();
        ItemPedido itemPedido = null;
        String query = "SELECT * FROM itempedido WHERE id = "+ idAux;
        try (PreparedStatement stmt = MySql.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pedido pedido = PedidosController.getInstance().filtrarPedidoPorId(rs.getInt("pedido"));
                ItemMenu itemMenu = ItemMenusController.getInstance().filtrarItemMenuPorId(rs.getInt("itemMenu"));    
                itemPedido = new ItemPedido(itemMenu, pedido);
                itemPedido.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener itemPedido: " + e.getMessage());
            throw e;
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemPedido;
    }

    public List<ItemPedido> filtrarPorPedido(Pedido pedido) throws SQLException {
        Connection MySql = ConexionMySQL.conectar();
        List<ItemPedido> itemsPedidos = new ArrayList<>();
        String query = "SELECT * FROM itempedido WHERE pedido = " + pedido.getId();
        try (PreparedStatement stmt = MySql.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItemMenu itemMenu = ItemMenusController.getInstance().filtrarItemMenuPorId(rs.getInt("itemMenu"));    
                ItemPedido itemPedido = new ItemPedido(itemMenu, pedido);
                itemPedido.setId(rs.getInt("id"));
                itemsPedidos.add(itemPedido);
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar itemsPedidos por pedido: " + e.getMessage());
            throw e;
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemsPedidos;
    }

    public void remove(ItemPedido itemPedido) throws ItemPedidoNoEncontradoException, SQLException {
        Connection MySql = ConexionMySQL.conectar();
        String query = "DELETE FROM itempedido WHERE id = " + itemPedido.getId();
        try (
            PreparedStatement pstmt = MySql.prepareStatement(query)) {
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new ItemPedidoNoEncontradoException("No se encontro el itemPedido a eliminar");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar itemPedido: " + e.getMessage());
            throw e;
        } finally {
            ConexionMySQL.cerrarConexion();       
        }
    }
    // -----------------------------------------------------------------------------------------------
}
