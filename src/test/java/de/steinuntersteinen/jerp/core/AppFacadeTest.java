package de.steinuntersteinen.jerp.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AppFacadeTest {
    static AppFacade appFacade;

    @BeforeAll
    static void setUp() {
        appFacade = new AppFacade();
    }
    @Test
    public void shouldCreateInvoiceFromFile() throws IOException {

    }
    @Test
    public void saveEmptyInvoiceShouldThrowException () throws Exception {
        appFacade.loadUser();
        appFacade.createInvoice();
        appFacade.getPDFInvoice();
        assertThrows(Exception.class, () -> appFacade.saveInvoice());
    }

    @Test
    public void shouldAddUser() throws Exception {
        appFacade.createUser();
        appFacade.setFirstName("John");
        appFacade.setLastName("Smith");
        appFacade.setProfession("Smith");
        appFacade.setPhoneNumber("1234567890");
        appFacade.setEmail("john.smith@steinuntersteinen.de");
        appFacade.setWebsite("www.steinuntersteinen.de");
        appFacade.setStreet("Steinuntersteinen-Str. 40 B");
        appFacade.setCity("San Francisco");
        appFacade.setZipCode("9410");
        appFacade.setBankName("SteinuntersteinenBank");
        appFacade.setIban("123456789");
        appFacade.setBic("123456789");
        appFacade.setTaxNumber("123456789");
        appFacade.setPathToDocumentDirectory("/home/julien/IdeaProjects/Jerp/Invoices/");
        appFacade.saveUser();
    }


    @Test
    public void shouldCreateUser() throws IOException {
        appFacade.createUser();
        String userId1 = appFacade.getUserId();
        appFacade.createUser();
        String userId2 = appFacade.getUserId();
        assertNotEquals(userId1, userId2);
    }

}