package es.upm.miw.iwvg_devops.rest.code;

import es.upm.miw.iwvg_devops.code.Fraction;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import es.upm.miw.iwvg_devops.code.Searches;

import static org.junit.jupiter.api.Assertions.*;

class SearchesTest {

    @Test
    void testFindUserFamilyNameByUserNameDistinct() {
        assertEquals(List.of("Torres"), new Searches().findUserFamilyNameByUserNameDistinct("Paula")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindUserFractionNumeratorByFamilyName() {
        assertEquals(List.of(2, 4, 0, 1, 1), new Searches().findFractionNumeratorByUserFamilyName("Torres")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFamilyNameByFractionDenominator() {
        assertEquals(List.of("López", "Torres"), new Searches().findUserFamilyNameByFractionDenominator(2)
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFractionAdditionByUserId() {
        Fraction result = new Searches().findFractionAdditionByUserId("1");
        assertEquals(3, result.getNumerator());
        assertEquals(1, result.getDenominator());
    }
    @Test
    void testFindFractionAdditionByUserId_InvalidUser() {
        assertThrows(RuntimeException.class, () -> new Searches().findFractionAdditionByUserId("99"));
    }

    @Test
    void testFindFractionAdditionByUserId_NonExistentUser() {
        assertThrows(RuntimeException.class, () -> {
            new Searches().findFractionAdditionByUserId("7");
        });
    }

    @Test
    void testFindFractionAdditionByUserId_UserWithNegativeFractions() {
        Fraction result = new Searches().findFractionAdditionByUserId("2");
        assertEquals(109, result.getNumerator());
        assertEquals(30, result.getDenominator());
    }

    @Test
    void testFindFractionAdditionByUserId_UserWithDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> new Searches().findFractionAdditionByUserId("6"));
    }

    @Test
    void testFindUserIdByAllProperFraction() {
        Searches searches = new Searches();

        List<String> result = searches.findUserIdByAllProperFraction().toList();

        assertEquals(1,result.size());
        assertFalse(result.contains("3"));
        assertFalse(result.contains("4"));
        assertFalse(result.contains("1"));
        assertFalse(result.contains("2"));
        assertFalse(result.contains("5"));
        assertFalse(result.contains("6"));
        assertTrue(result.contains("8"));
    }

    @Test
    void testFindHighestFraction() {
        Searches searches = new Searches();
        Fraction highestFraction = searches.findHighestFraction();
        assertNotNull(highestFraction);
        assertEquals(2.0, highestFraction.decimal());
        assertEquals(2, highestFraction.getNumerator());
        assertEquals(1,highestFraction.getDenominator());
    }

    @Test
    void testFindDecimalFractionByNegativeSignFraction() {
        Searches searches = new Searches();

        List<Double> expectedDecimals = List.of(-0.2, -0.5);
        List<Double> actualDecimals = searches.findDecimalFractionByNegativeSignFraction().toList();

        assertEquals(expectedDecimals.size(), actualDecimals.size());
        assertTrue(actualDecimals.containsAll(expectedDecimals));
    }

}