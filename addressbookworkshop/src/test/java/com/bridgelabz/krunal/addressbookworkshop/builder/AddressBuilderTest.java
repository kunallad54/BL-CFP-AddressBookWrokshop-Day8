/**
 * Purpose : To test method of Address Book Builder class
 */
package com.bridgelabz.krunal.addressbookworkshop.builder;

import com.bridgelabz.krunal.addressbookworkshop.dto.AddressBookDTO;
import com.bridgelabz.krunal.addressbookworkshop.entity.AddressBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddressBuilderTest {

    @InjectMocks
    private AddressBuilder addressBuilder;

    @Test
    public void buildDOTest() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setPID(1);
        addressBookDTO.setPName("Krunal");
        addressBookDTO.setPAddress("Mumbai");
        addressBookDTO.setPCity("Mumbai");
        addressBookDTO.setPState("MH");
        addressBookDTO.setPEmail("kunallad567@gmail.com");
        addressBookDTO.setPMobileNumber("09856365789");
        addressBookDTO.setPZip("400064");

        AddressBook addressBook = addressBuilder.buildDO(addressBookDTO);
        assertEquals(1,addressBook.getPID());
        assertEquals("Krunal",addressBook.getPName());
        assertEquals("Mumbai",addressBook.getPAddress());
        assertEquals("Mumbai",addressBook.getPCity());
        assertEquals("MH",addressBook.getPState());
        assertEquals("kunallad567@gmail.com",addressBook.getPEmail());
        assertEquals("09856365789",addressBook.getPMobileNumber());
        assertEquals("400064",addressBook.getPZip());
    }
}
