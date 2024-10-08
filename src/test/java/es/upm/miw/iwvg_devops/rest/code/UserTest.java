package es.upm.miw.iwvg_devops.rest.code;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.miw.iwvg_devops.code.User;
import es.upm.miw.iwvg_devops.code.Fraction;

import java.util.ArrayList;
import java.util.List;

class UserTest {

    private User user;

    @BeforeEach
    void before() {
        this.user = new User("1", "Stanislaw", "Lem", new ArrayList<>());
    }

    @Test
    void testDefaultConstructor() {
        User user = new User();
        assertTrue((user.getFractions().isEmpty()));
    }

    @Test
    void testParametrizedConstructor() {
        List<Fraction> fractionList = new ArrayList<>();
        fractionList.add(new Fraction(1, 2));
        fractionList.add(new Fraction(3, 10));
        User user = new User("2", "Orson", "Scott Card", fractionList);
        assertEquals("2", user.getId());
        assertEquals("Orson", user.getName());
        assertEquals("Scott Card", user.getFamilyName());
        assertEquals(fractionList, user.getFractions());
    }

    @Test
    void testGetId(){
        assertEquals("1", user.getId());
    }

    @Test
    void testGetName(){
        assertEquals("Stanislaw", user.getName());
    }

    @Test
    void testSetName(){
        user.setName("Isaac");
        assertEquals("Isaac", user.getName());
    }

    @Test
    void testGetFamilyName(){
        assertEquals("Lem", user.getFamilyName());
    }

    @Test
    void testSetFamilyName(){
        user.setFamilyName("Asimov");
        assertEquals("Asimov", user.getFamilyName());
    }

    @Test
    void testGetFractions() {
        user.addFraction(new Fraction(1, 3));
        Fraction fraction = user.getFractions().get(0);
        assertEquals(1, fraction.getNumerator());
        assertEquals(3, fraction.getDenominator());
    }

    @Test
    void testSetFractions() {
        List<Fraction> fractionList = new ArrayList<>();
        fractionList.add(new Fraction(2, 5));
        user.setFractions(fractionList);
        assertEquals(fractionList, user.getFractions());
    }

    @Test
    void testFullName() {
        assertEquals("Stanislaw Lem", user.fullName());
    }

    @Test
    void TestInitials() {
        assertEquals("S.",user.initials());
    }

    @Test
    void testToString() {
        List<Fraction> fractions = new ArrayList<>();
        fractions.add(new Fraction(1, 3));
        fractions.add(new Fraction(2, 5));
        user.setFractions(fractions);

        String expected = "User{id='1', name='Stanislaw', familyName='Lem', fractions=[Fraction{numerator=1, denominator=3}, Fraction{numerator=2, denominator=5}]}";
        assertEquals(expected, user.toString());
    }
}
