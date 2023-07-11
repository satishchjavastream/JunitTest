package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

    ContactManager contactManager;
    @BeforeAll
    public static void setupAll(){
        System.out.println("Should print before all Tests");
    }

    @BeforeEach
    public void setup(){
        System.out.println("Initialise the ContactManager Object.");
        contactManager = new ContactManager();
    }

    @Test
    public void shouldCreateContact(){
        contactManager.addContact("satish","kumar","0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("satish") && contact.getLastName().equals("kumar") && contact.getPhoneNumber().equals("0123456789")).findAny().isPresent());
    }

    @Test
    @DisplayName("Should not create a contact When first name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class,() -> {contactManager.addContact(null,"kumar","0123456789");});
    }

    @Test
    @DisplayName("Should not create a contact When last name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class,() -> {contactManager.addContact("satish",null,"0123456789");});
    }

    @Test
    @DisplayName("Should not create a contact When phone number is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        Assertions.assertThrows(RuntimeException.class,() -> {contactManager.addContact("satish","kumar",null);});
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Should execute after each test.");
    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("Should be executed at the end of the test.");
    }

  /*  @Test
    @DisplayName("shouldCreateContactOnMac")
    @EnabledOnOs(value= OS.MAC,disabledReason = "Disabled on MAC OS")
    public void shouldCreateContactOnMac(){
        contactManager.addContact("satish","kumar","0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("satish") && contact.getLastName().equals("kumar") && contact.getPhoneNumber().equals("0123456789")).findAny().isPresent());
    }

    @Test
    @DisplayName("shouldNotCreateContactOnWindows")
    @DisabledOnOs(value= OS.WINDOWS,disabledReason = "Disabled on WINDOWS OS")
    public void shouldNotCreateContactOnWindows(){
        contactManager.addContact("satish","kumar","0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());

    }*/

    @Test
    @DisplayName("Test contact create in DEV machine")
    public void shouldTestConectOnDEV(){
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("satish","kumar","0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1,contactManager.getAllContacts().size());

    }

    @DisplayName("Repeat contact creation Test 5 times")
    @RepeatedTest(value =5,name= "Repeat Contact Creation Test {currentRepetition} of {totalRepetition}")
    public void shouldRepeatedTestConectCreate(){
        contactManager.addContact("satish","kumar","0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1,contactManager.getAllContacts().size());
    }

    /*@DisplayName("Param meter test")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "+01234567899"})
    public void shouldParameterizedTestConectCreate(String phoneNumber){
        contactManager.addContact("satish","kumar",phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1,contactManager.getAllContacts().size());
    }*/

}