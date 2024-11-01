
package DAO;

import java.util.List;
import tp.Pedido;
import exceptions.PedidoNoEncontradoException;

public interface PedidosDAO {
    List<Pedido> getPedido() throws PedidoNoEncontradoException;
    void addPedido(Pedido pedido) throws PedidoNoEncontradoException;
    List<Pedido> filtrarPedidoPorId(int id) throws PedidoNoEncontradoException;
    List<Pedido> filtrarPorNombreCliente(String nombreVendedor) throws PedidoNoEncontradoException;
    List<Pedido> filtrarPorIdCliente(int idVendedor) throws PedidoNoEncontradoException;
}
