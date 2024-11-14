
package DAO;

import java.sql.SQLException;
import java.util.List;
import exceptions.itemPedido.ItemPedidoNoEncontradoException;
import model.ItemPedido;
import model.Pedido;

public interface ItemPedidoDAO {

    void addItemPedido(ItemPedido itemPedido);
    ItemPedido getItemPedido(int id) throws ItemPedidoNoEncontradoException, SQLException;
    List<ItemPedido> filtrarPorPedido(Pedido pedido) throws ItemPedidoNoEncontradoException, SQLException;
    List<ItemPedido> getItemsPedidos() throws ItemPedidoNoEncontradoException, SQLException;
    void remove(ItemPedido itemPedido) throws ItemPedidoNoEncontradoException, SQLException;
    
}
