
package DAO;

import java.util.List;
import exceptions.ItemPedidoNoEncontradoException;
import tp.ItemPedido;

public interface ItemPedidoDAO {

    void addItemPedido(ItemPedido itemPedido);
    List<ItemPedido> filtrarPorCliente(String nombreCliente) throws ItemPedidoNoEncontradoException;
    List<ItemPedido> filtrarPorNombreVendedor(String nombreVendedor) throws ItemPedidoNoEncontradoException;
    List<ItemPedido> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemPedidoNoEncontradoException;
    List<ItemPedido> filtrarPorIdVendedor(int idVendedor) throws ItemPedidoNoEncontradoException;
    List<ItemPedido> allItemsPedidos() throws ItemPedidoNoEncontradoException;

}
