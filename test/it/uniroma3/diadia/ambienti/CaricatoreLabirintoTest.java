package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;

class CaricatoreLabirintoTest {

    private Labirinto labirinto;

    @BeforeEach
    void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        labirinto = Labirinto.newBuilder("resources/labirinto.txt");
    }

    @AfterEach
    void tearDown() {
        this.labirinto = null;
    }

    @Test
    void testLoader1() {
        assertEquals("N10", this.labirinto.getStanzaCorrente().getNome());
    }

    @Test
    void testLoader2() {
        assertEquals("N11 ", this.labirinto.getStanzaVincente().getNome());
    }

    @Test
    void testLoader3() {
        assertEquals("pinza", this.labirinto.getStanzaCorrente().getAttrezzo("pinza").getNome());
    }
}
