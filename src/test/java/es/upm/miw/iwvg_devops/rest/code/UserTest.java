package es.upm.miw.iwvg_devops.rest.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.miw.iwvg_devops.code.User;

import java.util.ArrayList;

public class UserTest {

    private User user;

    @BeforeEach
    void before() {
        this.user = new User("1", "Stanislaw", "Lem", new ArrayList<>());
    }

    @Test
    public void testDefaultConstructor() {
        User user = new User();
        assertTrue((user.getFractions().isEmpty()));
    }

    @Test
    public void testGetId(){
        assertEquals("1", user.getId());
    }

    @Test
    public void testGetName(){
        assertEquals("Stanislaw", user.getName());
    }

    @Test
    public void testSetName(){
        user.setName("Isaac");
        assertEquals("Isaac", user.getName());
    }

    @Test
    public void testGetFamilyName(){
        assertEquals("Lem", user.getFamilyName());
    }

    @Test
    public void testSetFamilyName(){
        user.setFamilyName("Asimov");
        assertEquals("Asimov", user.getFamilyName());
    }

    @Test
    public void testFullName() {

        assertEquals("Stanislaw Lem", user.fullName());
    }

    @Test
    public void TestInitials() {
        assertEquals("S.",user.initials());
    }
}
