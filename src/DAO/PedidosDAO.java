
package DAO;

import java.util.List;
import tp.Pedido;
import exceptions.Pedido.PedidoNoEncontradoException;

public interface PedidosDAO {
    List<Pedido> getPedido() throws PedidoNoEncontradoException;
    void addPedido(Pedido pedido) throws PedidoNoEncontradoException;
    Pedido filtrarPedidoPorId(int id) throws PedidoNoEncontradoException;
    List<Pedido> filtrarPorNombreCliente(String nombre) throws PedidoNoEncontradoException;
    List<Pedido> filtrarPorIdCliente(int id) throws PedidoNoEncontradoException;
    void eliminarPedido(int id) throws PedidoNoEncontradoException;
    List<Pedido> filtrarPedidoPorVendedor(String vendedor) throws PedidoNoEncontradoException;
}
