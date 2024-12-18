
package test.model;

import model.Coordenada;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoordenadaTest {
    @Test
    public void testCoordenadaCreadaCorrectamente() {
        Coordenada coordenada = new Coordenada(40.7128, -74.0060);

        assertEquals(40.7128, coordenada.getLat(), "La latitud debe ser 40.7128.");
        assertEquals(-74.0060, coordenada.getLng(), "La longitud debe ser -74.0060.");
    }

    @Test
    public void testLatitudCorrecta() {
        Coordenada coordenada = new Coordenada(34.0522, -118.2437);

        assertEquals(34.0522, coordenada.getLat(), "La latitud debe ser 34.0522.");
    }

    @Test
    public void testLongitudCorrecta() {
        Coordenada coordenada = new Coordenada(34.0522, -118.2437);

        assertEquals(-118.2437, coordenada.getLng(), "La longitud debe ser -118.2437.");
    }

    @Test
    public void testCoordenadaDeValoresNegativos() {
        Coordenada coordenada = new Coordenada(-33.8688, 151.2093);

        assertEquals(-33.8688, coordenada.getLat(), "La latitud debe ser -33.8688.");
        assertEquals(151.2093, coordenada.getLng(), "La longitud debe ser 151.2093.");
    }

    @Test
    public void testCoordenadaConValoresExtremos() {
        Coordenada coordenada = new Coordenada(90.0, 180.0);

        assertEquals(90.0, coordenada.getLat(), "La latitud debe ser 90.0.");
        assertEquals(180.0, coordenada.getLng(), "La longitud debe ser 180.0.");
    }
}
