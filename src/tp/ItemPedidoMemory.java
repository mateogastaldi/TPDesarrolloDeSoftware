package tp;

import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void addItemPedido(ItemPedido itemPedido){this.itemsPedidos.add(itemPedido);}
    public List<ItemPedido> filtrarPorCliente(String nombreCliente) throws ItemNoEncontradoException{

        List<ItemPedido> itemsPedidosFiltrados = itemsPedidos.stream()
                .filter(item -> item.getPedido().getCliente().getNombre().equalsIgnoreCase(nombreCliente))
                .collect(Collectors.toList());

        if(itemsPedidosFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron items para el cliente " + nombreCliente);
        }

        return itemsPedidosFiltrados;

    }
    @Override
    public List<ItemPedido> filtrarPorNombreVendedor(String nombreVendedor) throws ItemNoEncontradoException {
        List<ItemPedido> resultado = itemsPedidos.stream()
                .filter(item -> item.getItemMenu().getVendedor().getNombre().equalsIgnoreCase(nombreVendedor))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron ítems para el vendedor: " + nombreVendedor);
        }

        return resultado;
    }
    @Override
    public List<ItemPedido> filtrarPorRangoPrecio(double precioMin, double precioMax) throws ItemNoEncontradoException {
        List<ItemPedido> resultado = itemsPedidos.stream()
                .filter(item -> item.getItemMenu().getPrecio() >= precioMin && item.getItemMenu().getPrecio() <= precioMax)
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron ítems en el rango de precios: " + precioMin + " - " + precioMax);
        }

        return resultado;
    }
    @Override
    public List<ItemPedido> filtrarPorIdVendedor(int idVendedor) throws ItemNoEncontradoException {
        List<ItemPedido> itemsFiltrados =  itemsPedidos.stream()
                .filter(item -> item.getItemMenu().getVendedor().getId() == idVendedor)
                .collect(Collectors.toList());

        if (itemsFiltrados.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron ítems del restaurante: " + idVendedor);
        }

        return itemsFiltrados;
    }
    public void printPedidos(){
        for (ItemPedido itemPedido : itemsPedidos){
            System.out.println(itemPedido.getItemMenu().getNombre());
        }
    }
}
