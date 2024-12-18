
package test.model;

import model.Bebida;
import model.Categoria;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.itemMenu.categoria.CategoriaNoCreadaException;
import org.junit.jupiter.api.BeforeEach;

public class CategoriaTest {
    private Categoria categoria;
    
    @BeforeEach
    void config(){
        categoria = new Categoria("Bebidas", Bebida.class);
    }
    
    @Test
    public void testCategoriaCreadaCorrectamente() throws CategoriaNoCreadaException {
        assertEquals("Bebidas", categoria.getDescripcion(), "La descripción de la categoría debe ser 'Bebidas'.");

        assertEquals(Bebida.class, categoria.getTipoItem(), "El tipo de item debe ser 'Bebida'.");

        assertEquals(0, categoria.getId(), "El id debe ser 0 por defecto.");
    }

    @Test
    public void testCategoriaNoCreadaPorDescripcionNula() {
        assertThrows(CategoriaNoCreadaException.class, () -> {
            new Categoria(null, Bebida.class);
        }, "Debe lanzar una excepción cuando la descripción es nula.");
    }

    @Test
    public void testCategoriaNoCreadaPorTipoItemNulo() {
        assertThrows(CategoriaNoCreadaException.class, () -> {
            new Categoria("Comida", null);
        }, "Debe lanzar una excepción cuando el tipo de item es nulo.");
    }

    @Test
    public void testSetId() throws CategoriaNoCreadaException {
        categoria.setId(1);
        
        assertEquals(1, categoria.getId(), "El id debe ser 1.");
    }

    @Test
    public void testGetters() throws CategoriaNoCreadaException {
        assertEquals("Bebidas", categoria.getDescripcion(), "La descripción debe ser 'Bebidas'.");
        assertEquals(Bebida.class, categoria.getTipoItem(), "El tipo de item debe ser 'Bebidas'.");
    }
}
