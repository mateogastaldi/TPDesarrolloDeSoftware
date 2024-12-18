
package test.model;

import model.Direccion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class DireccionTest {
    private Direccion direccion;
    
    @BeforeEach
    void config(){
        direccion = new Direccion("San Martin", 742, "Santa Fe", "Argentina");
    }
    
    @Test
    public void testDireccionCreadaCorrectamente() {
        assertEquals("San Martin", direccion.getCalle(), "La calle debe ser 'San Martin'.");
        assertEquals(742, direccion.getAltura(), "La altura debe ser 742.");
        assertEquals("Santa Fe", direccion.getCiudad(), "La ciudad debe ser 'Santa Fe'.");
        assertEquals("Argentina", direccion.getPais(), "El pais debe ser 'Argentina'.");
    }

    @Test
    public void testSettersAndGetters() {
        direccion.setCalle("Chacabuco");
        direccion.setAltura(456);
        direccion.setCiudad("Madrid");
        direccion.setPais("España");

        assertEquals("Chacabuco", direccion.getCalle(), "La calle debe ser 'Chacabuco'.");
        assertEquals(456, direccion.getAltura(), "La altura debe ser 456.");
        assertEquals("Madrid", direccion.getCiudad(), "La ciudad debe ser 'Madrid'.");
        assertEquals("España", direccion.getPais(), "El pais debe ser 'España'.");
    }

    @Test
    public void testDireccionConValoresExtremos() {
        Direccion direccion1 = new Direccion("Calle", Integer.MAX_VALUE, "Ciudad", "Pais");

        assertEquals(Integer.MAX_VALUE, direccion1.getAltura(), "La altura debe ser el valor máximo de un entero.");
    }

    @Test
    public void testDireccionConValoresNull() {
        Direccion direccion1 = new Direccion(null, 0, null, null);

        assertNull(direccion1.getCalle(), "La calle no debe ser null.");
        assertEquals(0, direccion1.getAltura(), "La altura debe ser 0.");
        assertNull(direccion1.getCiudad(), "La ciudad no debe ser null.");
        assertNull(direccion1.getPais(), "El pais no debe ser null.");
    }
}
