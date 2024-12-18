
package test.model;

import model.MediosDePagos;
import model.Transferencia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransferenciaTest {
    
    @Test
    public void testGetMedioDePago() {
        Transferencia transferencia = new Transferencia();

        assertEquals(MediosDePagos.TRANSFERENCIA, transferencia.getMedioDePago(), "El medio de pago debe ser TRANSFERENCIA.");
    }

    @Test
    public void testPrecio() {
        Transferencia transferencia = new Transferencia();
        
        double precio = 100.0;
        double precioEsperado = 100.0 * 1.02;

        assertEquals(precioEsperado, transferencia.precio(precio), "El precio con comisión debe ser 102.0.");
    }
}
