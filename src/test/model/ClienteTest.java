
package test.model;

import model.Cliente;
import model.Coordenada;
import model.Direccion;
import model.Vendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    
    private Cliente cliente;
    private Vendedor vendedor;

    @BeforeEach
    void config() {
        Direccion direccion1 = new Direccion("Calle1", 123, "Santa Fe", "Argentina");
        Coordenada coordenadas1 = new Coordenada(40.7128, -74.0060);
        cliente = new Cliente("Cliente 1", 123456789L, "cliente1@example.com", direccion1, coordenadas1);

        Direccion direccionVendedor = new Direccion("Calle2", 789, "Parana", "Argentina");
        Coordenada coordenadasVendedor = new Coordenada(40.73061, -73.935242);
        vendedor = new Vendedor("Vendedor 1", direccionVendedor, coordenadasVendedor);
    }

    @Test
    void testNombreIgual() {
        assertTrue(cliente.nombreIgual("Cliente 1"), "El método nombreIgual debería devolver true para un nombre coincidente.");
        assertFalse(cliente.nombreIgual("Otro Cliente"), "El método nombreIgual debería devolver false para un nombre diferente.");
    }

    @Test
    void testModificarAtributos() {
        Direccion direccion2 = new Direccion("Nueva Calle", 456, "New York", "USA");
        Coordenada coordenadas2 = new Coordenada(34.0522, -118.2437);

        cliente.modificarCliente("Cliente Modificado", "987654321",  "modificado@example.com",direccion2, coordenadas2);

        assertEquals("Cliente Modificado", cliente.getNombre(), "El nombre del cliente no se actualizó correctamente.");
        assertEquals(987654321L, cliente.getCuit(), "El CUIT del cliente no se actualizó correctamente.");
        assertEquals("modificado@example.com", cliente.getEmail(), "El email del cliente no se actualizó correctamente.");
        assertEquals(direccion2, cliente.getDireccion(), "La dirección del cliente no se actualizó correctamente.");
        assertEquals(coordenadas2, cliente.getCoordenadas(), "Las coordenadas del cliente no se actualizaron correctamente.");
    }
}
