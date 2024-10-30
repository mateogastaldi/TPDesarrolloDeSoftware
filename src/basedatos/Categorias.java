package basedatos;

import tp.Bebida;
import tp.Categoria;
import tp.Plato;

public class Categorias {
    public static Categoria categoria1 = new Categoria("Postres", Plato.class);
    public static Categoria categoria2 = new Categoria("Entradas", Plato.class);
    public static Categoria categoria3 = new Categoria("Principales", Plato.class);
    public static Categoria categoria4 = new Categoria("Vinos", Bebida.class);
    public static Categoria categoria5 = new Categoria("Cervezas", Bebida.class);
    public static Categoria categoria6 = new Categoria( "Gaseosa", Bebida.class);
    public static Categoria categoria7 = new Categoria("Agua", Bebida.class);
}
