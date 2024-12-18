
package test.model;

import model.Efectivo;
import model.MediosDePagos;
import model.PagoStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class EfectivoTest {
    private PagoStrategy pago;
    
    @BeforeEach
    void config(){
        pago = new Efectivo();
    }
    
    @Test
    public void testGetMedioDePago() {
        assertEquals(MediosDePagos.EFECTIVO, pago.getMedioDePago(), "El medio de pago debe ser 'EFECTIVO'.");
    }

    @Test
    public void testPrecio() {
        double precioOriginal = 100.0;
        assertEquals(precioOriginal, pago.precio(precioOriginal), "El precio no deberia cambiar.");
    }
}
