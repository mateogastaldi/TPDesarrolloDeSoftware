package model;

public class ItemPedido {
    private ItemMenu itemMenu;
    private Pedido pedido;
    private int id;

    //constructor-------------------------------------------------------------------------------------
    public ItemPedido(ItemMenu itemMenu, Pedido pedido) {
        setItemMenu(itemMenu);
        setPedido(pedido);
    }
    //-------------------------------------------------------------------------------------------------

    //getters-setters---------------------------------------------------------------------------------
    public ItemMenu getItemMenu() {return this.itemMenu;}
    public Pedido getPedido() {return this.pedido;}
    public int getId() {return this.id;}

    private void setItemMenu(ItemMenu itemMenu) {this.itemMenu = itemMenu;}
    private void setPedido(Pedido pedido) {this.pedido = pedido;}
    public void setId(int id) {this.id = id;}
    //-------------------------------------------------------------------------------------------------
}
