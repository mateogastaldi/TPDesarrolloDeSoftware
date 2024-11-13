package memory;

import DAO.ItemPedidoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import exceptions.itemPedido.ItemPedidoNoEncontradoException;
import model.ItemPedido;
import model.Pedido;

public class ItemPedidoMemory implements ItemPedidoDAO{

    //ESTO TIENE QUE SER UN SINGLETON
    private static ItemPedidoMemory ITEMPEDIDOMEMORY_INSTANCE;
    private List<ItemPedido> itemsPedidos;

    //constructores
    private ItemPedidoMemory(){
        itemsPedidos = new ArrayList<>();
    }
    private ItemPedidoMemory(List<ItemPedido> itemsPedidos) {
        setItemsPedidos(itemsPedidos);
    }

    //getters-setters
    private void setItemsPedidos(List<ItemPedido> itemsPedidos) {
        this.itemsPedidos = itemsPedidos;}
    public static ItemPedidoMemory getInstance() {
        if(ITEMPEDIDOMEMORY_INSTANCE == null) {
            ITEMPEDIDOMEMORY_INSTANCE = new ItemPedidoMemory();
        }
        return ITEMPEDIDOMEMORY_INSTANCE;
    }
    public List<ItemPedido> getItemsPedidos() {
        return itemsPedidos;
    }

    //metodos
    @Override
    public void addItemPedido(ItemPedido itemPedido){this.itemsPedidos.add(itemPedido);}
    @Override
    public List<ItemPedido> filtrarPorCliente(String nombreCliente) throws ItemPedidoNoEncontradoException {

        List<ItemPedido> itemsPedidosFiltrados = itemsPedidos.stream()
                .filter(item -> item.getPedido().getCliente().getNombre().equalsIgnoreCase(nombreCliente))
                .collect(Collectors.toList());

        if(itemsPedidosFiltrados.isEmpty()){
            throw new ItemPedidoNoEncontradoException("No se encontraron items para el cliente " + nombreCliente);
        }

        return itemsPedidosFiltrados;

    }
    @Override
    public List<ItemPedido> filtrarPorNombreVendedor(String nombreVendedor) throws ItemPedidoNoEncontradoException {
        List<ItemPedido> resultado = itemsPedidos.stream()
                .filter(item -> item.getItemMenu().getVendedor().getNombre().equalsIgnoreCase(nombreVendedor))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new ItemPedidoNoEncontradoException("No se encontraron ítems para el vendedor: " + nombreVendedor);
        }

        return resultado;
    }
    @Override
    public List<ItemPedido> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemPedidoNoEncontradoException {
        List<ItemPedido> resultado = itemsPedidos.stream()
                .filter(item -> item.getItemMenu().getPrecio() >= precioMin && item.getItemMenu().getPrecio() <= precioMax)
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new ItemPedidoNoEncontradoException("No se encontraron ítems en el rango de precios: " + precioMin + " - " + precioMax);
        }

        return resultado;
    }
    @Override
    public List<ItemPedido> filtrarPorIdVendedor(int idVendedor) throws ItemPedidoNoEncontradoException {
        List<ItemPedido> itemsFiltrados =  itemsPedidos.stream()
                .filter(item -> item.getItemMenu().getVendedor().getId() == idVendedor)
                .collect(Collectors.toList());

        if (itemsFiltrados.isEmpty()) {
            throw new ItemPedidoNoEncontradoException("No se encontraron ítems del restaurante: " + idVendedor);
        }

        return itemsFiltrados;
    }
    @Override
    public List<ItemPedido> getitemsPedidos() throws ItemPedidoNoEncontradoException {
        if (this.itemsPedidos.isEmpty()) {
            throw new ItemPedidoNoEncontradoException("No existen Items Pedidos");
        }
        return this.itemsPedidos;
    }

    public ItemPedido getItemPedido(int id) throws ItemPedidoNoEncontradoException {
        ItemPedido iP = this.itemsPedidos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (iP == null) {
            throw new ItemPedidoNoEncontradoException("No existen Items Pedidos");
        }
        return iP;
    }
    public List<ItemPedido> filtrarPorPedido(Pedido pedido){
        List<ItemPedido> item = itemsPedidos.stream().filter(p -> p.getPedido().getId() == pedido.getId()).collect(Collectors.toList());
        return item;
    }
    public void remove(ItemPedido itemPedido) throws ItemPedidoNoEncontradoException {
        this.itemsPedidos.remove(itemPedido);
    }
}
