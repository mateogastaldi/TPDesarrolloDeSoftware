
package test.model;

import model.MediosDePagos;
import model.MercadoPago;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class MercadoPagoTest {
    private MercadoPago mp;
    
    @BeforeEach
    void config(){
        mp = new MercadoPago();
    }
    
    @Test
    public void testGetMedioDePago() {
        assertEquals(MediosDePagos.MERCADO_PAGO, mp.getMedioDePago(),"El medio de pago debería ser MERCADO_PAGO.");
    }

    @Test
    public void testPrecio() {
        double precioOriginal = 100.0;
        double precioEsperado = precioOriginal * 1.04;

        assertEquals(precioEsperado, mp.precio(precioOriginal),"El precio calculado no es el esperado.");
    }
}
