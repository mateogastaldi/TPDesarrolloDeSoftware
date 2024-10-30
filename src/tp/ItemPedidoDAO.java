package tp;

import java.util.List;

public interface ItemPedidoDAO {

    List<ItemPedido> filtrarPorCliente(String nombreCliente) throws ItemNoEncontradoException;
    List<ItemPedido> filtrarPorNombreVendedor(String nombreVendedor) throws ItemNoEncontradoException;
    List<ItemPedido> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemNoEncontradoException;
    List<ItemPedido> filtrarPorIdVendedor(int idVendedor) throws ItemNoEncontradoException;

}
