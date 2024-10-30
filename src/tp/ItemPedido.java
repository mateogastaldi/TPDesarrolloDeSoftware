package tp;

public class ItemPedido {
    private ItemMenu itemMenu;
    private Pedido pedido;

    //constructor
    public ItemPedido(ItemMenu itemMenu, Pedido pedido) {
        setItemMenu(itemMenu);
        setPedido(pedido);
    }

    //getters-setters
    public ItemMenu getItemMenu() {return this.itemMenu;}
    public Pedido getPedido() {return this.pedido;}

    private void setItemMenu(ItemMenu itemMenu) {this.itemMenu = itemMenu;}
    private void setPedido(Pedido pedido) {this.pedido = pedido;}
}
