package mySQL;
import DAO.ItemPedidoDAO;
import exceptions.itemPedido.ItemPedidoNoEncontradoException;

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
        String sql = "INSERT INTO itempedido (pedido, itemMenu) VALUES (?, ?)";
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

    // -----------------------------------------------------------------------------------------------

    @Override
    public List<ItemPedido> getItemsPedidos() throws SQLException {
        List<ItemPedido> itemsPedidos = new ArrayList<>();
        Connection mySQL = ConexionMySQL.conectar();
        try(
            PreparedStatement pstmt = mySQL.prepareStatement("SELECT * FROM itempedido");
        ){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Pedido pedidoItemPedido = null;
                ItemMenu itemMenuDeItemPedido = null;
                // Obtengo los datos del itemPedido
                int idItemPedido = rs.getInt("id");
                int ItemMenuId = rs.getInt("itemmenu");
                int PedidoId = rs.getInt("pedido");
                // Obtengo el itemMenu del item pedido -------------------------------------------------------------------------
                PreparedStatement pstmtItemMenu = mySQL.prepareStatement("SELECT * FROM itemmenu WHERE id = " + ItemMenuId);
                ResultSet rsItemMenu = pstmtItemMenu.executeQuery();
                if(rsItemMenu.next()){
                    // Obtengo los datos del itemMenu
                    int idItemMenu = rsItemMenu.getInt("id");
                    String nombre = rsItemMenu.getString("nombre");
                    String descripcion = rsItemMenu.getString("descripcion");
                    double precio = rsItemMenu.getDouble("precio");
                    boolean aptoVegano = rsItemMenu.getBoolean("aptovegano");
                    boolean aptoCeliaco = rsItemMenu.getBoolean("aptoceliaco");
                    int idCategoria = rsItemMenu.getInt("categoria");
                    int idVendedor = rsItemMenu.getInt("vendedor");
                    double calorias = rs.getDouble("calorias");
                    double peso = rs.getDouble("peso");
                    double gradAlcoholica = rs.getDouble("gradAlcoholica");
                    double tamanio = rs.getDouble("tamanio");
                    String tipo = rs.getString("tipo");
                    // Obtengo los datos del vendedor del itemMenu
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
                            plato.setId(idItemMenu);
                            itemMenuDeItemPedido = plato;
                        } else if (tipo.equals("Bebida")) {
                            Categoria categoria = new Categoria(descripcionCategoria, Bebida.class);
                            categoria.setId(idCategoria);
                            Bebida bebida = new Bebida(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, gradAlcoholica, tamanio);
                            bebida.setId(idItemMenu);
                            itemMenuDeItemPedido = bebida;
                        }
                    }

                }
                // -------------------------------------------------------------------------------------------------------------
                
                // Obtengo el pedido del item pedido ---------------------------------------------------------------------------
                PreparedStatement pstmtPedido = mySQL.prepareStatement("SELECT * FROM pedido WHERE id = " + PedidoId);
                ResultSet rsPedido = pstmtPedido.executeQuery();
                if(rsPedido.next()){
                    // Obtengo los datos del pedido
                    int idPedido = rsPedido.getInt("id");
                    int idCliente = rsPedido.getInt("cliente"); 
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
                    int pago = rsPedido.getInt("pago");
                    int idVendedor = rsPedido.getInt("vendedor");
                    // Obtengo los datos del cliente del pedido
                    Cliente cliente = null;
                    PreparedStatement pstmtCliente = mySQL.prepareStatement("SELECT * FROM cliente WHERE id = " + idCliente);
                    ResultSet rsCliente = pstmtCliente.executeQuery();
                    if(rsCliente.next()){
                        int idCliente2  = rsCliente.getInt("id");
                        String nombreCliente = rsCliente.getString("nombre");
                        long cuit = rsCliente.getLong("cuit");
                        String email = rsCliente.getString("email");
                        String calle = rsCliente.getString("calle");
                        int altura = rsCliente.getInt("altura");
                        String ciudad = rsCliente.getString("ciudad");
                        String pais = rsCliente.getString("pais");
                        double latitud = rsCliente.getDouble("lat");
                        double longitud = rsCliente.getDouble("lng");
                        // Creo el cliente
                        Coordenada coordenadaCliente = new Coordenada(latitud, longitud);
                        Direccion direccionCliente = new Direccion(calle, altura, ciudad, pais);
                        cliente = new Cliente(nombreCliente , cuit, email, direccionCliente, coordenadaCliente);
                        cliente.setId(idCliente2);
                    }
                    // Obtengo los datos del vendedor del pedido
                    Vendedor vendedorPedido = null;
                    PreparedStatement pstmtVendedor = mySQL.prepareStatement("SELECT * FROM vendedor WHERE id = " + idVendedor);
                    ResultSet rsVendedor = pstmtVendedor.executeQuery();
                    if(rsVendedor.next()){
                        int idVendedor2 = rsVendedor.getInt("id");
                        String nombreVendedor = rsVendedor.getString("nombre");
                        String calle = rsVendedor.getString("calle");
                        int altura = rsVendedor.getInt("altura");
                        String ciudad = rsVendedor.getString("ciudad");
                        String pais = rsVendedor.getString("pais");
                        double latitud = rsVendedor.getDouble("lat");
                        double longitud = rsVendedor.getDouble("lng");
                        // Creo el vendedor
                        Coordenada coordenadaVendedor = new Coordenada(latitud, longitud);
                        Direccion direccionVendedor = new Direccion(calle, altura, ciudad, pais);
                        vendedorPedido = new Vendedor(nombreVendedor, direccionVendedor, coordenadaVendedor);
                        vendedorPedido.setId(idVendedor2);
                    }
                    // Obtenego los datos del pago del pedido
                    Pago pagoPedido = null;
                    PreparedStatement pstmtPago = mySQL.prepareStatement("SELECT * FROM pago WHERE id = " + pago);
                    ResultSet rsPago = pstmtPago.executeQuery();
                    if(rsPago.next()){
                        int idPago = rs.getInt("pago.id");
                        double monto = rs.getDouble("pago.monto");	
                        PagoStrategy metodoDePago = null;
                        if (rs.getString("pago.metodoDePago").equalsIgnoreCase("EFECTIVO")) {
                            metodoDePago = new Efectivo();
                        } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("MERCADO PAGO")) {
                            metodoDePago = new MercadoPago();
                        } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("TRANSFERENCIA")) {
                            metodoDePago = new Transferencia();
                        }
                        String alias = rs.getString("alias");
                        String cbu = rs.getString("cbu");
                        long cuit = rs.getLong("cuit");
                        boolean pagado = rs.getBoolean("pagado");

                        // Creo el pago
                        pagoPedido = new Pago( metodoDePago,monto,pagado,cbu,cuit,alias);
                        pagoPedido.setId(idPago);
                        
                    }

                    // Creo el pedido
                    pedidoItemPedido = new Pedido(cliente, vendedorPedido, pagoPedido, estado);
                    pedidoItemPedido.setId(idPedido);   
                }
                // ---------------------------------------------------------------------------------------------------
                    
                // Creo el itemPedido y lo agrego a la lista
                ItemPedido itemPedido = new ItemPedido(itemMenuDeItemPedido, pedidoItemPedido);
                itemPedido.setId(idItemPedido);
                itemsPedidos.add(itemPedido);
                // ---------------------------------------------------------------------------------------------------
            }
        }
        catch (SQLException e) {
            System.out.println("Error al obtener itemsPedidos: " + e.getMessage());
            throw e;
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemsPedidos;
    }

    // -----------------------------------------------------------------------------------------------

    public ItemPedido getItemPedido(int idAux) throws SQLException {
        ItemPedido itemPedido = null;
        Connection mySQL = ConexionMySQL.conectar();
        try(
            PreparedStatement pstmt = mySQL.prepareStatement("SELECT * FROM itempedido WHERE id = " + idAux);
        ){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Pedido pedidoItemPedido = null;
                ItemMenu itemMenuDeItemPedido = null;
                // Obtengo los datos del itemPedido
                int idItemPedido = rs.getInt("id");
                int ItemMenuId = rs.getInt("itemmenu");
                int PedidoId = rs.getInt("pedido");

                // Obtengo el itemMenu del item pedido -------------------------------------------------------------------------
                PreparedStatement pstmtItemMenu = mySQL.prepareStatement("SELECT * FROM itemmenu WHERE id = " + ItemMenuId);
                ResultSet rsItemMenu = pstmtItemMenu.executeQuery();
                if(rsItemMenu.next()){
                    // Obtengo los datos del itemMenu
                    int idItemMenu = rsItemMenu.getInt("id");
                    String nombre = rsItemMenu.getString("nombre");
                    String descripcion = rsItemMenu.getString("descripcion");
                    double precio = rsItemMenu.getDouble("precio");
                    boolean aptoVegano = rsItemMenu.getBoolean("aptovegano");
                    boolean aptoCeliaco = rsItemMenu.getBoolean("aptoceliaco");
                    int idCategoria = rsItemMenu.getInt("categoria");
                    int idVendedor = rsItemMenu.getInt("vendedor");
                    double calorias = rs.getDouble("calorias");
                    double peso = rs.getDouble("peso");
                    double gradAlcoholica = rs.getDouble("gradAlcoholica");
                    double tamanio = rs.getDouble("tamanio");
                    String tipo = rs.getString("tipo");
                    // Obtengo los datos del vendedor del itemMenu
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
                            plato.setId(idItemMenu);
                            itemMenuDeItemPedido = plato;
                        } else if (tipo.equals("Bebida")) {
                            Categoria categoria = new Categoria(descripcionCategoria, Bebida.class);
                            categoria.setId(idCategoria);
                            Bebida bebida = new Bebida(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, gradAlcoholica, tamanio);
                            bebida.setId(idItemMenu);
                            itemMenuDeItemPedido = bebida;
                        }
                    }

                }
                // -------------------------------------------------------------------------------------------------------------
                // Obtengo el pedido del item pedido ---------------------------------------------------------------------------
                PreparedStatement pstmtPedido = mySQL.prepareStatement("SELECT * FROM pedido WHERE id = " + PedidoId);
                ResultSet rsPedido = pstmtPedido.executeQuery();
                if(rsPedido.next()){
                    // Obtengo los datos del pedido
                    int idPedido = rsPedido.getInt("id");
                    int idCliente = rsPedido.getInt("cliente"); 
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
                    int pago = rsPedido.getInt("pago");
                    int idVendedor = rsPedido.getInt("vendedor");
                    // Obtengo los datos del cliente del pedido
                    Cliente cliente = null;
                    PreparedStatement pstmtCliente = mySQL.prepareStatement("SELECT * FROM cliente WHERE id = " + idCliente);
                    ResultSet rsCliente = pstmtCliente.executeQuery();
                    if(rsCliente.next()){
                        int idCliente2  = rsCliente.getInt("id");
                        String nombreCliente = rsCliente.getString("nombre");
                        long cuit = rsCliente.getLong("cuit");
                        String email = rsCliente.getString("email");
                        String calle = rsCliente.getString("calle");
                        int altura = rsCliente.getInt("altura");
                        String ciudad = rsCliente.getString("ciudad");
                        String pais = rsCliente.getString("pais");
                        double latitud = rsCliente.getDouble("lat");
                        double longitud = rsCliente.getDouble("lng");
                        // Creo el cliente
                        Coordenada coordenadaCliente = new Coordenada(latitud, longitud);
                        Direccion direccionCliente = new Direccion(calle, altura, ciudad, pais);
                        cliente = new Cliente(nombreCliente , cuit, email, direccionCliente, coordenadaCliente);
                        cliente.setId(idCliente2);
                    }
                    // Obtengo los datos del vendedor del pedido
                    Vendedor vendedorPedido = null;
                    PreparedStatement pstmtVendedor = mySQL.prepareStatement("SELECT * FROM vendedor WHERE id = " + idVendedor);
                    ResultSet rsVendedor = pstmtVendedor.executeQuery();
                    if(rsVendedor.next()){
                        int idVendedor2 = rsVendedor.getInt("id");
                        String nombreVendedor = rsVendedor.getString("nombre");
                        String calle = rsVendedor.getString("calle");
                        int altura = rsVendedor.getInt("altura");
                        String ciudad = rsVendedor.getString("ciudad");
                        String pais = rsVendedor.getString("pais");
                        double latitud = rsVendedor.getDouble("lat");
                        double longitud = rsVendedor.getDouble("lng");
                        // Creo el vendedor
                        Coordenada coordenadaVendedor = new Coordenada(latitud, longitud);
                        Direccion direccionVendedor = new Direccion(calle, altura, ciudad, pais);
                        vendedorPedido = new Vendedor(nombreVendedor, direccionVendedor, coordenadaVendedor);
                        vendedorPedido.setId(idVendedor2);
                    }
                    // Obtenego los datos del pago del pedido
                    Pago pagoPedido = null;
                    PreparedStatement pstmtPago = mySQL.prepareStatement("SELECT * FROM pago WHERE id = " + pago);
                    ResultSet rsPago = pstmtPago.executeQuery();
                    if(rsPago.next()){
                        int idPago = rs.getInt("pago.id");
                        double monto = rs.getDouble("pago.monto");	
                        PagoStrategy metodoDePago = null;
                        if (rs.getString("pago.metodoDePago").equalsIgnoreCase("EFECTIVO")) {
                            metodoDePago = new Efectivo();
                        } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("MERCADO PAGO")) {
                            metodoDePago = new MercadoPago();
                        } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("TRANSFERENCIA")) {
                            metodoDePago = new Transferencia();
                        }
                        String alias = rs.getString("alias");
                        String cbu = rs.getString("cbu");
                        long cuit = rs.getLong("cuit");
                        boolean pagado = rs.getBoolean("pagado");

                        // Creo el pago
                        pagoPedido = new Pago( metodoDePago,monto,pagado,cbu,cuit,alias);
                        pagoPedido.setId(idPago);
                        
                    }

                    // Creo el pedido
                    pedidoItemPedido = new Pedido(cliente, vendedorPedido, pagoPedido, estado);
                    pedidoItemPedido.setId(idPedido);   
                }
    // ---------------------------------------------------------------------------------------------------

    // Creo el itemPedido y lo agrego a la lista
                itemPedido = new ItemPedido(itemMenuDeItemPedido, pedidoItemPedido);
                itemPedido.setId(idItemPedido);
                
    // ---------------------------------------------------------------------------------------------------
        }
            
        }
        catch (SQLException e) {
            System.out.println("Error al obtener itemsPedidos: " + e.getMessage());
            throw e;
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemPedido;
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

    // -----------------------------------------------------------------------------------------------
    public List<ItemPedido> filtrarPorPedido(Pedido pedido) throws ItemPedidoNoEncontradoException, SQLException{
        List<ItemPedido> itemsPedidos = new ArrayList<>();
        Connection mySQL = ConexionMySQL.conectar();
        try(
            PreparedStatement pstmt = mySQL.prepareStatement("SELECT * FROM itempedido WHERE pedido = " + pedido.getId());
        ){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Pedido pedidoItemPedido = null;
                ItemMenu itemMenuDeItemPedido = null;
                // Obtengo los datos del itemPedido
                int idItemPedido = rs.getInt("id");
                int ItemMenuId = rs.getInt("itemmenu");
                int PedidoId = rs.getInt("pedido");
                // Obtengo el itemMenu del item pedido -------------------------------------------------------------------------
                PreparedStatement pstmtItemMenu = mySQL.prepareStatement("SELECT * FROM itemmenu WHERE id = " + ItemMenuId);
                ResultSet rsItemMenu = pstmtItemMenu.executeQuery();
                if(rsItemMenu.next()){
                    // Obtengo los datos del itemMenu
                    int idItemMenu = rsItemMenu.getInt("id");
                    String nombre = rsItemMenu.getString("nombre");
                    String descripcion = rsItemMenu.getString("descripcion");
                    double precio = rsItemMenu.getDouble("precio");
                    boolean aptoVegano = rsItemMenu.getBoolean("aptovegano");
                    boolean aptoCeliaco = rsItemMenu.getBoolean("aptoceliaco");
                    int idCategoria = rsItemMenu.getInt("categoria");
                    int idVendedor = rsItemMenu.getInt("vendedor");
                    double calorias = rs.getDouble("calorias");
                    double peso = rs.getDouble("peso");
                    double gradAlcoholica = rs.getDouble("gradAlcoholica");
                    double tamanio = rs.getDouble("tamanio");
                    String tipo = rs.getString("tipo");
                    // Obtengo los datos del vendedor del itemMenu
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
                            plato.setId(idItemMenu);
                            itemMenuDeItemPedido = plato;
                        } else if (tipo.equals("Bebida")) {
                            Categoria categoria = new Categoria(descripcionCategoria, Bebida.class);
                            categoria.setId(idCategoria);
                            Bebida bebida = new Bebida(nombre, descripcion, precio, aptoVegano, aptoCeliaco, categoria, vendedor, gradAlcoholica, tamanio);
                            bebida.setId(idItemMenu);
                            itemMenuDeItemPedido = bebida;
                        }
                    }

                }
                // -------------------------------------------------------------------------------------------------------------
                
                // Obtengo el pedido del item pedido ---------------------------------------------------------------------------
                PreparedStatement pstmtPedido = mySQL.prepareStatement("SELECT * FROM pedido WHERE id = " + PedidoId);
                ResultSet rsPedido = pstmtPedido.executeQuery();
                if(rsPedido.next()){
                    // Obtengo los datos del pedido
                    int idPedido = rsPedido.getInt("id");
                    int idCliente = rsPedido.getInt("cliente"); 
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
                    int pago = rsPedido.getInt("pago");
                    int idVendedor = rsPedido.getInt("vendedor");
                    // Obtengo los datos del cliente del pedido
                    Cliente cliente = null;
                    PreparedStatement pstmtCliente = mySQL.prepareStatement("SELECT * FROM cliente WHERE id = " + idCliente);
                    ResultSet rsCliente = pstmtCliente.executeQuery();
                    if(rsCliente.next()){
                        int idCliente2  = rsCliente.getInt("id");
                        String nombreCliente = rsCliente.getString("nombre");
                        long cuit = rsCliente.getLong("cuit");
                        String email = rsCliente.getString("email");
                        String calle = rsCliente.getString("calle");
                        int altura = rsCliente.getInt("altura");
                        String ciudad = rsCliente.getString("ciudad");
                        String pais = rsCliente.getString("pais");
                        double latitud = rsCliente.getDouble("lat");
                        double longitud = rsCliente.getDouble("lng");
                        // Creo el cliente
                        Coordenada coordenadaCliente = new Coordenada(latitud, longitud);
                        Direccion direccionCliente = new Direccion(calle, altura, ciudad, pais);
                        cliente = new Cliente(nombreCliente , cuit, email, direccionCliente, coordenadaCliente);
                        cliente.setId(idCliente2);
                    }
                    // Obtengo los datos del vendedor del pedido
                    Vendedor vendedorPedido = null;
                    PreparedStatement pstmtVendedor = mySQL.prepareStatement("SELECT * FROM vendedor WHERE id = " + idVendedor);
                    ResultSet rsVendedor = pstmtVendedor.executeQuery();
                    if(rsVendedor.next()){
                        int idVendedor2 = rsVendedor.getInt("id");
                        String nombreVendedor = rsVendedor.getString("nombre");
                        String calle = rsVendedor.getString("calle");
                        int altura = rsVendedor.getInt("altura");
                        String ciudad = rsVendedor.getString("ciudad");
                        String pais = rsVendedor.getString("pais");
                        double latitud = rsVendedor.getDouble("lat");
                        double longitud = rsVendedor.getDouble("lng");
                        // Creo el vendedor
                        Coordenada coordenadaVendedor = new Coordenada(latitud, longitud);
                        Direccion direccionVendedor = new Direccion(calle, altura, ciudad, pais);
                        vendedorPedido = new Vendedor(nombreVendedor, direccionVendedor, coordenadaVendedor);
                        vendedorPedido.setId(idVendedor2);
                    }
                    // Obtenego los datos del pago del pedido
                    Pago pagoPedido = null;
                    PreparedStatement pstmtPago = mySQL.prepareStatement("SELECT * FROM pago WHERE id = " + pago);
                    ResultSet rsPago = pstmtPago.executeQuery();
                    if(rsPago.next()){
                        int idPago = rs.getInt("pago.id");
                        double monto = rs.getDouble("pago.monto");	
                        PagoStrategy metodoDePago = null;
                        if (rs.getString("pago.metodoDePago").equalsIgnoreCase("EFECTIVO")) {
                            metodoDePago = new Efectivo();
                        } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("MERCADO PAGO")) {
                            metodoDePago = new MercadoPago();
                        } else if (rs.getString("pago.metodoDePago").equalsIgnoreCase("TRANSFERENCIA")) {
                            metodoDePago = new Transferencia();
                        }
                        String alias = rs.getString("alias");
                        String cbu = rs.getString("cbu");
                        long cuit = rs.getLong("cuit");
                        boolean pagado = rs.getBoolean("pagado");

                        // Creo el pago
                        pagoPedido = new Pago( metodoDePago,monto,pagado,cbu,cuit,alias);
                        pagoPedido.setId(idPago);
                        
                    }

                    // Creo el pedido
                    pedidoItemPedido = new Pedido(cliente, vendedorPedido, pagoPedido, estado);
                    pedidoItemPedido.setId(idPedido);   
                }
                // ---------------------------------------------------------------------------------------------------
                    
                // Creo el itemPedido y lo agrego a la lista
                ItemPedido itemPedido = new ItemPedido(itemMenuDeItemPedido, pedidoItemPedido);
                itemPedido.setId(idItemPedido);
                itemsPedidos.add(itemPedido);
                // ---------------------------------------------------------------------------------------------------
            }
        }
        catch (SQLException e) {
            System.out.println("Error al obtener itemsPedidos: " + e.getMessage());
            throw e;
        } finally {
            ConexionMySQL.cerrarConexion();
        }
        return itemsPedidos;
    }
}
