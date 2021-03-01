package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private User testUser;
    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setName("Ross");
        testUser.setPassportNo("BR9887-JK78889");
        testUser.setDateOfBirth("9-07-1999");
        testUser.setAddress("canada");
        testUser.setContact("987678767");
        testUser.setEmergencyContact("8778665");
        testUser.setGender("Male");
        testUser.setEmail("ross@gmail.com");

    }
    @Test
    void testConstructor(){
        assertEquals("Ross", testUser.getName());
        assertEquals("BR9887-JK78889",testUser.getPassportNo());
        assertEquals("9-07-1999",testUser.getDateOfBirth());
        assertEquals("canada",testUser.getAddress());
        assertEquals("987678767",testUser.getContact());
        assertEquals("8778665",testUser.getEmergencyContact());
        assertEquals("Male",testUser.getGender());
        assertEquals("ross@gmail.com",testUser.getEmail());
    }
}