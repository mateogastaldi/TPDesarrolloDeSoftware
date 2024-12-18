
package test.model;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BebidaTest {
    private Vendedor vendedor;
    private Categoria categoria;
    private Bebida bebida1;

    @BeforeEach
    void config(){
        Coordenada coordenada = new Coordenada(-54.807222, -68.304444);
        Direccion direccion = new Direccion("Belgrano", 2000, "Santa Fe", "Argentina");
        vendedor = new Vendedor("McDonalds", direccion, coordenada);
        categoria = new Categoria("Bebidas", Bebida.class);
        bebida1 = new Bebida("Cerveza", "Cerveza artesanal", 50.0, false, false, categoria, vendedor, 5.0, 500.0);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(5.0, bebida1.getGraduacionAlcoholica(), "La graduación alcohólica debe ser 5.0.");
        assertEquals(500.0, bebida1.getTamanio(), "El tamaño de la bebida debe ser 500.0.");
    }

    @Test
    public void testPesoConGraduacionAlcoholica() {
        double pesoEsperado = (500.0 * 0.99) * 1.2;

        assertEquals(pesoEsperado, bebida1.peso(), 0.01, "El peso calculado no es correcto.");
    }

    @Test
    public void testPesoSinGraduacionAlcoholica() {
        Bebida bebida = new Bebida("Agua", "Agua mineral", 20.0, true, true, categoria, vendedor, 0.0, 500.0);

        double pesoEsperado = (500.0 * 1.04) * 1.2;

        assertEquals(pesoEsperado, bebida.peso(), 0.01, "El peso calculado no es correcto.");
    }

    @Test
    public void testEsComida() {
        assertFalse(bebida1.esComida(), "No es comida.");
    }

    @Test
    public void testEsBebida() {
        assertTrue(bebida1.esBebida(), "No es una bebida.");
    }

    @Test
    public void testAptoVegano() {
        Bebida bebida = new Bebida("Cerveza vegana", "Cerveza apta para veganos", 50.0, true, false, categoria, vendedor, 5.0, 500.0);

        assertTrue(bebida.aptoVegano(), "La bebida no es apta para veganos.");
    }

    @Test
    public void testAptoCeliaco() {
        Bebida bebida = new Bebida("Cerveza sin gluten", "Cerveza apta para celíacos", 50.0, false, true, categoria, vendedor, 5.0, 500.0);

        assertTrue(bebida.aptoCeliaco(), "La bebida no es apta para celíacos.");
    }

    @Test
    public void testEditarItem() {
        bebida1.editarItem("Cerveza light", "Cerveza sin alcohol", 40.0, true, false, categoria, vendedor, 0.0, 400.0);

        assertEquals("Cerveza light", bebida1.getNombre(), "El nombre no fue actualizado.");
        assertEquals(40.0, bebida1.getPrecio(), "El precio no fue actualizado.");
        assertEquals(0.0, bebida1.getGraduacionAlcoholica(), "La graduación alcohólica no fue actualizada.");
        assertEquals(400.0, bebida1.getTamanio(), "El tamaño no fue actualizado.");
    }
}
