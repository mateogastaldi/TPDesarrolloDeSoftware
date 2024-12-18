
package test.model;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class VendedorTest {
    private Vendedor vendedor;
    private Cliente cliente;

    @BeforeEach
    void config() {
        Direccion direccion = new Direccion("Calle Falsa", 123, "Springfield", "USA");
        Coordenada coordenadas = new Coordenada(40.7128, -74.0060);
        ArrayList<ItemMenu> items = new ArrayList<>();
        items.add(new Bebida("Pinta Cerveza Santa Fe", "500ml de Cerveza rubia Santa Fe", 68, true, false, new Categoria("Cervezas",Bebida.class), vendedor, 5, 500));
        items.add(new Bebida("Vino Tinto", "Vino Tinto Reserva", 120, true, true, new Categoria("Vinos",Bebida.class), vendedor, 10, 750));
        vendedor = new Vendedor("Vendedor 1", coordenadas, items, new ArrayList<>());

        Coordenada coordCliente = new Coordenada(34.0522, -118.2437);
        cliente = new Cliente("Cliente 1", 20123122431L, "juan@frsf.com", direccion, coordCliente);
    }

    @Test
    void testGetItemBebida() {
        ArrayList<Bebida> bebidas = vendedor.getItemBebida();
        assertEquals(2, bebidas.size(), "El número de bebidas en el menú no es correcto");
        assertEquals("Pinta Cerveza Santa Fe", bebidas.get(0).getNombre(), "El nombre de la primera bebida no coincide");
        assertEquals("Vino Tinto", bebidas.get(1).getNombre(), "El nombre de la segunda bebida no coincide");
    }

    @Test
    void testDistancia() {
        double distancia = vendedor.distancia(cliente);
        assertEquals(3935.746254609722, distancia, 0.1, "La distancia calculada entre el vendedor y el cliente no es correcta");
    }
}
