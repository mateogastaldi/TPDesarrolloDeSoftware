
package DAO;

import java.sql.SQLException;
import java.util.List;
import model.Pedido;
import exceptions.Pedido.PedidoNoEncontradoException;

public interface PedidosDAO {
    List<Pedido> getPedido() throws PedidoNoEncontradoException,SQLException;
    void addPedido(Pedido pedido) throws PedidoNoEncontradoException,SQLException;
    Pedido filtrarPedidoPorId(int id) throws PedidoNoEncontradoException,SQLException;
    List<Pedido> filtrarPorNombreCliente(String nombre) throws PedidoNoEncontradoException,SQLException;
    List<Pedido> filtrarPorIdCliente(int id) throws PedidoNoEncontradoException,SQLException;
    void eliminarPedido(int id) throws PedidoNoEncontradoException,SQLException;
    List<Pedido> filtrarPedidoPorVendedor(String vendedor) throws PedidoNoEncontradoException,SQLException;
    void cambioEstado(Pedido pedido) throws PedidoNoEncontradoException,SQLException;
}
