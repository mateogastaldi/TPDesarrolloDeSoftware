// ItemsMenuMemory.java
package mySQL;

import DAO.ItemsMenuDAO;
import exceptions.itemMenu.ItemMenuNoEncontradoException;
import model.*;
import java.util.*;
import java.sql.*;

public class ItemMenuMySQL implements ItemsMenuDAO {
    // Singleton -------------------------------------------------------------------------------------------------
    private static ItemMenuMySQL ITEMMENUMYSQL_INSTANCE;

    private ItemMenuMySQL() {
    }

    public static ItemMenuMySQL getInstance() {
        if (ITEMMENUMYSQL_INSTANCE == null) {
            ITEMMENUMYSQL_INSTANCE = new ItemMenuMySQL();
        }
        return ITEMMENUMYSQL_INSTANCE;
    }
    // -----------------------------------------------------------------------------------------------------------

    // Metodos ---------------------------------------------------------------------------------------------------
    public List<ItemMenu> getItemMenus() throws ItemMenuNoEncontradoException {
        ArrayList<ItemMenu> itemMenus = new ArrayList<ItemMenu>();
        Connection mySQL = ConexionMySQL.conectar();
        System.out.println("Entro a items menu");
        try (
            PreparedStatement pstmt = mySQL.prepareStatement("SELECT * FROM itemmenu");
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Obtengo los datos del itemMenu
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                boolean aptoVegano = rs.getBoolean("aptoVegano");
                boolean aptoCeliaco = rs.getBoolean("aptoCeliaco");
                int idCategoria = rs.getInt("id_categoria");
                int idVendedor = rs.getInt("id_vendedor");
                double calorias = rs.getDouble("calorias");
                double peso = rs.getDouble("peso");
                double gradAlcoholica = rs.getDouble("gradAlcoholica");
                double tamanio = rs.getDouble("tamanio");
                String tipo = rs.getString("tipo");
                // Obtengo los datos vendedor del itemMenu
                Vendedor vendedor = null;
                PreparedStatement pstm2 = mySQL.prepareStatement("SELECT * FROM vendedor WHERE id = " + idVendedor);
                ResultSet rs1 = pstm2.executeQuery();
                if (rs1.next()) {
                    String nombreVendedor = rs1.getString("nombre");
                    String calle = rs1.getString("calle");
                    int altura = rs1.getInt("altura");
                    String ciudad = rs1.getString("ciudad");
                    String pais = rs1.getString("pais");
                    double latitud = rs1.getDouble("lat");
                    double longitud = rs1.getDouble("lng");
                    // Creo el vendedor
                    Direccion direccion = new Direccion(calle, altura, ciudad, pais);
                    Coordenada coordenadas = new Coordenada(latitud, longitud);
                    vendedor = new Vendedor(nombreVendedor, direccion, coordenadas);
                    vendedor.setId(idVendedor);
                }
                // Obtengo los datos de la categoria de itemMenu
                PreparedStatement pstm3 = mySQL.prepareStatement("SELECT * FROM categoria WHERE id = " + idCategoria);
                ResultSet rs2 = pstm3.executeQuery();
                if (rs2.next()) {
                    String descripcionCategoria = rs2.getString("descripcion");
                    // Creo la categoria y agrego el itemMenu a la lista
                    if (tipo.equals("Plato")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Plato.class);
                        categoria.setId(idCategoria);
                        Plato plato = new Plato(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, calorias, peso);
                        plato.setId(id);
                        itemMenus.add(plato);
                    } else if (tipo.equals("Bebida")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Bebida.class);
                        categoria.setId(idCategoria);
                        Bebida bebida = new Bebida(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, gradAlcoholica, tamanio);
                        bebida.setId(id);
                        itemMenus.add(bebida);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un itemMenu: " + e.getMessage());
            throw new ItemMenuNoEncontradoException("No se encontraron itemns de menu");
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        System.out.println("Salio de items menu" + itemMenus.size());
        return itemMenus;
    }

    // -----------------------------------------------------------------------------------------------------------

    public void addItemMenu(ItemMenu itemMenu) throws SQLException {
        Connection mySQL = ConexionMySQL.conectar();
        String query = "INSERT INTO itemmenu (nombre, descripcion, precio, aptoVegano, aptoCeliaco, id_categoria, id_vendedor,calorias,peso,gradAlcoholica,tamanio,tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
        try (
                PreparedStatement pstmt = mySQL.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, itemMenu.getNombre());
            pstmt.setString(2, itemMenu.getDescripcion());
            pstmt.setDouble(3, itemMenu.getPrecio());
            pstmt.setBoolean(4, itemMenu.aptoVegano());
            pstmt.setBoolean(5, itemMenu.aptoCeliaco());
            pstmt.setInt(6, itemMenu.getCategoria().getId());
            pstmt.setInt(7, itemMenu.getVendedor().getId());
            if (itemMenu instanceof Bebida) {
                pstmt.setNull(8, Types.DOUBLE);
                pstmt.setNull(9, Types.DOUBLE);
                pstmt.setDouble(10, ((Bebida) itemMenu).getGraduacionAlcoholica());
                pstmt.setDouble(11, ((Bebida) itemMenu).getTamanio());
                pstmt.setString(12, "Bebida");
            } else if (itemMenu instanceof Plato) {
                pstmt.setDouble(8, ((Plato) itemMenu).getCalorias());
                pstmt.setDouble(9, ((Plato) itemMenu).getPeso());
                pstmt.setNull(10, Types.DOUBLE);
                pstmt.setNull(11, Types.DOUBLE);
                pstmt.setString(12, "Plato");
            }
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        itemMenu.setId(generatedId);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar un itemMenu: " + e.getMessage());
            throw e;
        } finally {
            ConexionMySQL.cerrarConexion();
        }
    }

    // -----------------------------------------------------------------------------------------------------------

    public List<ItemMenu> filtrarItemMenuPorNombre(String nombre) throws SQLException {
        List<ItemMenu> itemMenus = new ArrayList<>();
        Connection mySQL = ConexionMySQL.conectar();
        try (
                PreparedStatement pstmt = mySQL.prepareStatement("SELECT * FROM itemmenu WHERE nombre = " + nombre);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreItem = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                boolean aptoVegano = rs.getBoolean("aptoVegano");
                boolean aptoCeliaco = rs.getBoolean("aptoCeliaco");
                int idCategoria = rs.getInt("id_categoria");
                int idVendedor = rs.getInt("id_vendedor");
                double calorias = rs.getDouble("calorias");
                double peso = rs.getDouble("peso");
                double gradAlcoholica = rs.getDouble("gradAlcoholica");
                double tamanio = rs.getDouble("tamanio");
                String tipo = rs.getString("tipo");
                // Obtengo los datos vendedor del itemMenu
                Vendedor vendedor = null;
                PreparedStatement pstm2 = mySQL.prepareStatement("SELECT * FROM vendedor WHERE id = " + idVendedor);
                ResultSet rs1 = pstm2.executeQuery();
                if (rs1.next()) {
                    String nombreVendedor = rs1.getString("nombre");
                    String calle = rs1.getString("calle");
                    int altura = rs1.getInt("altura");
                    String ciudad = rs1.getString("ciudad");
                    String pais = rs1.getString("pais");
                    double latitud = rs1.getDouble("lat");
                    double longitud = rs1.getDouble("lng");
                    // Creo el vendedor
                    Direccion direccion = new Direccion(calle, altura, ciudad, pais);
                    Coordenada coordenadas = new Coordenada(latitud, longitud);
                    vendedor = new Vendedor(nombreVendedor, direccion, coordenadas);
                    vendedor.setId(idVendedor);
                }
                // Obtengo los datos de la categoria de itemMenu
                PreparedStatement pstm3 = mySQL.prepareStatement("SELECT * FROM categoria WHERE id = " + idCategoria);
                ResultSet rs2 = pstm3.executeQuery();
                if (rs2.next()) {
                    String descripcionCategoria = rs2.getString("descripcion");
                    if (tipo.equals("Plato")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Plato.class);
                        categoria.setId(idCategoria);
                        Plato plato = new Plato(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, calorias, peso);
                        plato.setId(id);
                        itemMenus.add(plato);
                    } else if (tipo.equals("Bebida")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Bebida.class);
                        categoria.setId(idCategoria);
                        Bebida bebida = new Bebida(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, gradAlcoholica, tamanio);
                        bebida.setId(id);
                        itemMenus.add(bebida);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un itemMenu con el nombre seleccionado: " + e.getMessage());
            throw new SQLException("No se encontraron itemns de menu con el nombre seleccionado");
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemMenus;
    }

    // -----------------------------------------------------------------------------------------------------------

    public ItemMenu filtrarItemMenuPorId(int idAUX) throws SQLException {
        ItemMenu itemMenu = null;
        Connection mySQL = ConexionMySQL.conectar();
        try (
            PreparedStatement pstmt = mySQL.prepareStatement("SELECT * FROM itemmenu WHERE id = " + idAUX);
            ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombreItem = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                boolean aptoVegano = rs.getBoolean("aptoVegano");
                boolean aptoCeliaco = rs.getBoolean("aptoCeliaco");
                int idCategoria = rs.getInt("id_categoria");
                int idVendedor = rs.getInt("id_vendedor");
                double calorias = rs.getDouble("calorias");
                double peso = rs.getDouble("peso");
                double gradAlcoholica = rs.getDouble("gradAlcoholica");
                double tamanio = rs.getDouble("tamanio");
                String tipo = rs.getString("tipo");
                // Obtengo los datos vendedor del itemMenu
                Vendedor vendedor = null;
                PreparedStatement pstm2 = mySQL.prepareStatement("SELECT * FROM vendedor WHERE id = " + idVendedor);
                ResultSet rs1 = pstm2.executeQuery();
                if (rs1.next()) {
                    String nombreVendedor = rs1.getString("nombre");
                    String calle = rs1.getString("calle");
                    int altura = rs1.getInt("altura");
                    String ciudad = rs1.getString("ciudad");
                    String pais = rs1.getString("pais");
                    double latitud = rs1.getDouble("lat");
                    double longitud = rs1.getDouble("lng");
                    // Creo el vendedor
                    Direccion direccion = new Direccion(calle, altura, ciudad, pais);
                    Coordenada coordenadas = new Coordenada(latitud, longitud);
                    vendedor = new Vendedor(nombreVendedor, direccion, coordenadas);
                    vendedor.setId(idVendedor);
                }
                // Obtengo los datos de la categoria de itemMenu
                PreparedStatement pstm3 = mySQL.prepareStatement("SELECT * FROM categoria WHERE id = " + idCategoria);
                ResultSet rs2 = pstm3.executeQuery();
                if (rs2.next()) {
                    String descripcionCategoria = rs2.getString("descripcion");
                    if (tipo.equals("Plato")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Plato.class);
                        categoria.setId(idCategoria);
                        Plato plato = new Plato(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, calorias, peso);
                        plato.setId(id);
                        itemMenu = plato;
                        
                    } else if (tipo.equals("Bebida")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Bebida.class);
                        categoria.setId(idCategoria);
                        Bebida bebida = new Bebida(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, gradAlcoholica, tamanio);
                        bebida.setId(id);
                        itemMenu = bebida;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un itemMenu con el id seleccionado: " + e.getMessage());
            throw new ItemMenuNoEncontradoException("No se encontraron itemns de menu con el id seleccionado");
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemMenu;
    }

    // -----------------------------------------------------------------------------------------------------------

    public List<ItemMenu> filtrarPorNombreVendedor(String nombreAUX) throws SQLException {
        List<ItemMenu> itemMenus = new ArrayList<>();
        Connection mySQL = ConexionMySQL.conectar();
        String query = "SELECT * FROM itemmenu, vendedor WHERE itemmenu.id_vendedor = vendedor.id AND vendedor.nombre = " + nombreAUX;
        try (
            PreparedStatement pstmt = mySQL.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("itemmenu.id");
                String nombreItem = rs.getString("itemmenu.nombre");
                String descripcion = rs.getString("itemmenu.descripcion");
                double precio = rs.getDouble("itemmenu.precio");
                boolean aptoVegano = rs.getBoolean("itemmenu.aptoVegano");
                boolean aptoCeliaco = rs.getBoolean("itemmenu.aptoCeliaco");
                int idCategoria = rs.getInt("itemmenu.id_categoria");
                int idVendedor = rs.getInt("itemmenu.id_vendedor");
                double calorias = rs.getDouble("itemmenu.calorias");
                double peso = rs.getDouble("itemmenu.peso");
                double gradAlcoholica = rs.getDouble("itemmenu.gradAlcoholica");
                double tamanio = rs.getDouble("itemmenu.tamanio");
                String tipo = rs.getString("itemmenu.tipo");

                
                String nombreVendedor = rs.getString("vendedor.nombre");
                String calle = rs.getString("vendedor.calle");
                int altura = rs.getInt("vendedor.altura");
                String ciudad = rs.getString("vendedor.ciudad");
                String pais = rs.getString("vendedor.pais");
                double latitud = rs.getDouble("vendedor.lat");
                double longitud = rs.getDouble("vendedor.lng");
                // Creo el vendedor
                Direccion direccion = new Direccion(calle, altura, ciudad, pais);
                Coordenada coordenadas = new Coordenada(latitud, longitud);
                Vendedor vendedor = new Vendedor(nombreVendedor, direccion, coordenadas);
                vendedor.setId(idVendedor);
                
                // Obtengo los datos de la categoria de itemMenu
                PreparedStatement pstm3 = mySQL.prepareStatement("SELECT * FROM categoria WHERE id = " + idCategoria);
                ResultSet rs2 = pstm3.executeQuery();
                if (rs2.next()) {
                    String descripcionCategoria = rs2.getString("descripcion");
                    if (tipo.equals("Plato")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Plato.class);
                        categoria.setId(idCategoria);
                        Plato plato = new Plato(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, calorias, peso);
                        plato.setId(id);
                        itemMenus.add(plato);
                        
                    } else if (tipo.equals("Bebida")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Bebida.class);
                        categoria.setId(idCategoria);
                        Bebida bebida = new Bebida(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, gradAlcoholica, tamanio);
                        bebida.setId(id);
                        itemMenus.add(bebida); 
                    }
                }
            }
        } catch (SQLException e) {
            System.out
                    .println("Error al obtener un itemMenu con el nombre del vendedor seleccionado: " + e.getMessage());
            throw new SQLException("No se encontraron itemns de menu con el nombre del vendedor seleccionado");
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemMenus;
    }

    // -----------------------------------------------------------------------------------------------------------

    public List<ItemMenu> filtrarPorIdVendedor(int idVendedorAUX) throws SQLException {
        List<ItemMenu> itemMenus = new ArrayList<>();
        Connection mySQL = ConexionMySQL.conectar();
        try (
                PreparedStatement pstmt = mySQL
                        .prepareStatement("SELECT * FROM itemmenu, vendedor, categoria WHERE  itemmenu.id_vendedor = vendedor.id AND categoria.id = itemmenu.id_categoria AND itemmenu.id_vendedor = " + idVendedorAUX);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("itemmenu.id");
                String nombreItem = rs.getString("itemmenu.nombre");
                String descripcion = rs.getString("itemmenu.descripcion");
                double precio = rs.getDouble("itemmenu.precio");
                boolean aptoVegano = rs.getBoolean("itemmenu.aptoVegano");
                boolean aptoCeliaco = rs.getBoolean("itemmenu.aptoCeliaco");
                int idCategoria = rs.getInt("itemmenu.id_categoria");
                int idVendedor = rs.getInt("itemmenu.id_vendedor");
                double calorias = rs.getDouble("itemmenu.calorias");
                double peso = rs.getDouble("itemmenu.peso");
                double gradAlcoholica = rs.getDouble("itemmenu.gradAlcoholica");
                double tamanio = rs.getDouble("itemmenu.tamanio");
                String tipo = rs.getString("itemmenu.tipo");

                
                String nombreVendedor = rs.getString("vendedor.nombre");
                String calle = rs.getString("vendedor.calle");
                int altura = rs.getInt("vendedor.altura");
                String ciudad = rs.getString("vendedor.ciudad");
                String pais = rs.getString("vendedor.pais");
                double latitud = rs.getDouble("vendedor.lat");
                double longitud = rs.getDouble("vendedor.lng");
                // Creo el vendedor
                Direccion direccion = new Direccion(calle, altura, ciudad, pais);
                Coordenada coordenadas = new Coordenada(latitud, longitud);
                Vendedor vendedor = new Vendedor(nombreVendedor, direccion, coordenadas);
                vendedor.setId(idVendedor);
                
                // Obtengo los datos de la categoria de itemMenu
                PreparedStatement pstm3 = mySQL.prepareStatement("SELECT * FROM categoria WHERE id = " + idCategoria);
                ResultSet rs2 = pstm3.executeQuery();
                if (rs2.next()) {
                    String descripcionCategoria = rs2.getString("descripcion");
                    if (tipo.equals("Plato")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Plato.class);
                        categoria.setId(idCategoria);
                        Plato plato = new Plato(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, calorias, peso);
                        plato.setId(id);
                        itemMenus.add(plato);
                        
                    } else if (tipo.equals("Bebida")) {
                        Categoria categoria = new Categoria(descripcionCategoria, Bebida.class);
                        categoria.setId(idCategoria);
                        Bebida bebida = new Bebida(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, gradAlcoholica, tamanio);
                        bebida.setId(id);
                        itemMenus.add(bebida); 
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un itemMenu con el id del vendedor seleccionado: " + e.getMessage());
            throw new SQLException("No se encontraron itemns de menu con el id del vendedor seleccionado");
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemMenus;
    }

    // -----------------------------------------------------------------------------------------------------------

    public List<ItemMenu> filtrarPorCategoria(int categoriaAUX) throws SQLException {
        List<ItemMenu> itemMenus = new ArrayList<>();
        Connection mySQL = ConexionMySQL.conectar();
        try (
                PreparedStatement pstmt = mySQL
                        .prepareStatement("SELECT * FROM itemmenu, vendedor, categoria WHERE itemmenu.id_vendedor = vendedor.id AND categoria.id = itemmenu.id_categoria AND itemmenu.id_categoria = "  + categoriaAUX);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreItem = rs.getString("itemmenu.nombre");
                String descripcion = rs.getString("itemmenu.descripcion");
                double precio = rs.getDouble("itemmenu.precio");
                boolean aptoVegano = rs.getBoolean("itemmenu.aptoVegano");
                boolean aptoCeliaco = rs.getBoolean("itemmenu.aptoCeliaco");
                int idCategoria = rs.getInt("itemmenu.id_categoria");
                int idVendedor = rs.getInt("itemmenu.id_vendedor");
                double calorias = rs.getDouble("itemmenu.calorias");
                double peso = rs.getDouble("itemmenu.peso");
                double gradAlcoholica = rs.getDouble("itemmenu.gradAlcoholica");
                double tamanio = rs.getDouble("itemmenu.tamanio");
                String tipo = rs.getString("itemmenu.tipo");

                
                String nombreVendedor = rs.getString("vendedor.nombre");
                String calle = rs.getString("vendedor.calle");
                int altura = rs.getInt("vendedor.altura");
                String ciudad = rs.getString("vendedor.ciudad");
                String pais = rs.getString("vendedor.pais");
                double latitud = rs.getDouble("vendedor.lat");
                double longitud = rs.getDouble("vendedor.lng");
                // Creo el vendedor
                Direccion direccion = new Direccion(calle, altura, ciudad, pais);
                Coordenada coordenadas = new Coordenada(latitud, longitud);
                Vendedor vendedor = new Vendedor(nombreVendedor, direccion, coordenadas);
                vendedor.setId(idVendedor);
                
                // Obtengo los datos de la categoria de itemMenu

                String descripcionCategoria = rs.getString("categoria.descripcion");
                if (tipo.equals("Plato")) {
                    Categoria categoria = new Categoria(descripcionCategoria, Plato.class);
                    categoria.setId(idCategoria);
                    Plato plato = new Plato(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, calorias, peso);
                    plato.setId(id);
                    itemMenus.add(plato);
                    
                } else if (tipo.equals("Bebida")) {
                    Categoria categoria = new Categoria(descripcionCategoria, Bebida.class);
                    categoria.setId(idCategoria);
                    Bebida bebida = new Bebida(nombreItem, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, gradAlcoholica, tamanio);
                    bebida.setId(id);
                    itemMenus.add(bebida); 
                }
                
                
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener un itemMenu con la categoria seleccionada: " + e.getMessage());
            throw new SQLException("No se encontraron itemns de menu con la categoria seleccionada");
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemMenus;
    }

    // -----------------------------------------------------------------------------------------------------------

    public void eliminarItemMenu(int id) throws ItemMenuNoEncontradoException {
        Connection mySQL = ConexionMySQL.conectar();
        String query = "DELETE FROM itemmenu WHERE id = " + id;
        try (
                PreparedStatement pstmt = mySQL.prepareStatement(query)) {
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new ItemMenuNoEncontradoException("No se encontro el itemPedido a eliminar");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar itemMenu: " + e.getMessage());
            throw new ItemMenuNoEncontradoException("No se encontro el itemPedido a eliminar");
        } finally {
            ConexionMySQL.cerrarConexion();
        }
    }

    // -----------------------------------------------------------------------------------------------------------

    public void editarItemMenu(int id, String nombre, String descripcion, double precio, boolean aptoVegano,
            boolean aptoCeliaco, Categoria categoria, Vendedor vendedor, Double espe1, Double espe2)
            throws SQLException {
        Connection mySQL = ConexionMySQL.conectar();
        String query = "UPDATE itemmenu SET nombre = ?, descripcion = ?, precio = ?, aptoVegano = ?, aptoCeliaco = ?, id_categoria = ?, id_vendedor = ?, calorias = ?, peso = ?, gradAlcoholica = ?, tamanio = ?,tipo = ? WHERE id = "
                + id;
        try (
                PreparedStatement pstmt = mySQL.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, descripcion);
            pstmt.setDouble(3, precio);
            pstmt.setBoolean(4, aptoVegano);
            pstmt.setBoolean(5, aptoCeliaco);
            pstmt.setInt(6, categoria.getId());
            pstmt.setInt(7, vendedor.getId());
            if (categoria.getTipoItem().equals(Plato.class)) {
                pstmt.setDouble(8, espe1);
                pstmt.setDouble(9, espe2);
                pstmt.setNull(10, Types.DOUBLE);
                pstmt.setNull(11, Types.DOUBLE);
                pstmt.setString(12, "Plato");
            } else if (categoria.getTipoItem().equals(Bebida.class)) {
                pstmt.setNull(8, Types.DOUBLE);
                pstmt.setNull(9, Types.DOUBLE);
                pstmt.setDouble(10, espe1);
                pstmt.setDouble(11, espe2);
                pstmt.setString(12, "Bebida");
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al editar itemMenu: " + e.getMessage());
            throw new SQLException("No se encontro el itemPedido a editar");
        } finally {
            ConexionMySQL.cerrarConexion();
        }
    }

    // -----------------------------------------------------------------------------------------------
}