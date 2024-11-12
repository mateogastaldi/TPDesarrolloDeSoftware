
package DAO;

import java.util.List;
import exceptions.itemPedido.ItemPedidoNoEncontradoException;
import tp.ItemPedido;
import tp.Pedido;

public interface ItemPedidoDAO {

    void addItemPedido(ItemPedido itemPedido);
    ItemPedido getItemPedido(int id) throws ItemPedidoNoEncontradoException;
    List<ItemPedido> filtrarPorCliente(String nombreCliente) throws ItemPedidoNoEncontradoException;
    List<ItemPedido> filtrarPorNombreVendedor(String nombreVendedor) throws ItemPedidoNoEncontradoException;
    List<ItemPedido> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemPedidoNoEncontradoException;
    List<ItemPedido> filtrarPorIdVendedor(int idVendedor) throws ItemPedidoNoEncontradoException;
    List<ItemPedido> getitemsPedidos() throws ItemPedidoNoEncontradoException;
    List<ItemPedido> filtrarPorPedido(Pedido pedido);
    void remove(ItemPedido itemPedido) throws ItemPedidoNoEncontradoException;

}
