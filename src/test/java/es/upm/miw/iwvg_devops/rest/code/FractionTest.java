package es.upm.miw.iwvg_devops.rest.code;

import es.upm.miw.iwvg_devops.code.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.miw.iwvg_devops.code.Fraction;

import java.util.ArrayList;

public class FractionTest {

    private Fraction fraction;

    @BeforeEach
    void before() {
        this.fraction = new Fraction(3,10);
    }
    @Test
    void testConstructorWithParameters() {
        this.fraction = new Fraction(2,8);
        assertEquals(2,fraction.getNumerator());
        assertEquals(8,fraction.getDenominator());
    }

    @Test
    void testDefaultConstructor() {
        this.fraction = new Fraction();
        assertEquals(1,fraction.getNumerator());
        assertEquals(1,fraction.getDenominator());
    }

    @Test
    void testSetNumerator(){
        this.fraction.setNumerator(5);
        assertEquals(5, fraction.getNumerator());
    }

    @Test
    void testSetDenominator(){
        this.fraction.setDenominator(9);
        assertEquals(9, fraction.getDenominator());
    }

    @Test
    void testDecimal() {
        assertEquals(0.3,this.fraction.decimal());
    }

    @Test
    void testIsProper() {
        assertTrue(this.fraction.isProper());
    }

    @Test
    void  testIsImproper() {
        assertFalse(this.fraction.isImproper());
    }

     @Test
    void testToString() {
        assertEquals("Fraction{numerator=3, denominator=10}",this.fraction.toString());
     }
}
