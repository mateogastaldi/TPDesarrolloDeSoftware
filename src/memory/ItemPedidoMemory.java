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


    //metodos
    @Override
    public void addItemPedido(ItemPedido itemPedido){this.itemsPedidos.add(itemPedido);}
    @Override
    public List<ItemPedido> getItemsPedidos() throws ItemPedidoNoEncontradoException {
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
