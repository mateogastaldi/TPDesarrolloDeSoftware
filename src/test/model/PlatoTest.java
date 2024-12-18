
package test.model;

import model.Plato;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class PlatoTest {
    private Plato plato;
    
    @BeforeEach
    void config(){
        plato = new Plato("Plato1", "Descripcion", 100.0, true, true, null, null, 500.0, 200.0);
    }
    
    @Test
    public void testPeso() {
        assertEquals(220.0, plato.peso(), "El peso debe ser 220.0 (200.0 * 1.1).");
    }

    @Test
    public void testEsComida() {
        assertTrue(plato.esComida(), "No es comida");
    }

    @Test
    public void testEsBebida() {
        assertFalse(plato.esBebida(), "No es bebida.");
    }

    @Test
    public void testAptoVegano() {
        assertTrue(plato.aptoVegano(), "El plato no es apto para veganos.");
    }

    @Test
    public void testAptoCeliaco() {
        assertTrue(plato.aptoCeliaco(), "El plato no es apto para celíacos.");
    }

    @Test
    public void testEditarItem() {
        plato.editarItem("Plato2", "Descripcion", 150.0, false, false, null, null, 600.0, 250.0);

        assertEquals("Plato2", plato.getNombre(), "El nombre debe ser 'Plato2'.");
        assertEquals(600.0, plato.getCalorias(), "Las calorías deben ser 600.0.");
        assertEquals(250.0, plato.getPeso(), "El peso debe ser 250.0.");
    }
}
