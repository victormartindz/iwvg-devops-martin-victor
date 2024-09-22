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
    void testFindFractionAdditionByUserId_NonExistantUser() {
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

}