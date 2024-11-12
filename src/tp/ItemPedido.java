package tp;

public class ItemPedido {
    private ItemMenu itemMenu;
    private Pedido pedido;
    private static int contadorId = 0;
    private int id;

    //constructor
    public ItemPedido(ItemMenu itemMenu, Pedido pedido) {
        setItemMenu(itemMenu);
        setId();
        setPedido(pedido);
    }

    //getters-setters
    public ItemMenu getItemMenu() {return this.itemMenu;}
    public Pedido getPedido() {return this.pedido;}
    public int getId() {return this.id;}

    private void setItemMenu(ItemMenu itemMenu) {this.itemMenu = itemMenu;}
    private void setPedido(Pedido pedido) {this.pedido = pedido;}
    private void setId() {this.id = ++contadorId;}
}
