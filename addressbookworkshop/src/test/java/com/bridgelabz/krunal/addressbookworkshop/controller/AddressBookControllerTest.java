/**
 * Purpose : To test all AddressBookController methods
 */
package com.bridgelabz.krunal.addressbookworkshop.controller;

import com.bridgelabz.krunal.addressbookworkshop.dto.AddressBookDTO;
import com.bridgelabz.krunal.addressbookworkshop.dto.ResponseDTO;
import com.bridgelabz.krunal.addressbookworkshop.exceptions.AddressBookCustomException;
import com.bridgelabz.krunal.addressbookworkshop.service.IAddressBookService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookControllerTest {

    @InjectMocks
    private AddressBookController addressBookController;
    @Mock
    private IAddressBookService addressBookService;

    /**
     * Purpose : Ability to write Test Cases for getAddressBook() of AddressBookController.class
     *           Using the Mockito framework, when the addressBookService.getAddressBook() is called,
     *           then return a list of AddressBookDTO object.
     *           Store the return value of addressBookController.getAddressBook()
     *           in a variable of ResponseEntity<ResponseDTO>.
     *
     *
     * Test : Check whether the responseEntity value is null or not.
     *        Check whether the status code of responseEntity matches with the expected value.
     *        Check whether the message of the responseEntity body matches with the expected value.
     *        Check whether the responseEntity body data is null or not.
     */
    @Test
    public void getAddressesTest() {
        when(addressBookService.getAddressBook()).thenReturn(Lists.newArrayList(new AddressBookDTO()));
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.getAddressBook();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Got All Persons from AddressBook", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    /**
     * Purpose : Ability to write Test Cases for addPersonDetails() of AddressBookController.class
     *           Create an object  of the AddressBookDTO and set values to it.
     *           Using the Mockito framework, when the addressBookService.addPersonDetails() is called,
     *           then return the AddressBookDTO object.
     *           Store the return value of addressBookController.addPersonDetails()
     *           in a variable of ResponseEntity<ResponseDTO>.
     *
     *
     * Test : Check whether the responseEntity value is null or not.
     *        Check whether the status code of responseEntity matches with the expected value.
     *        Check whether the message of the responseEntity body matches with the expected value.
     *        Check whether the responseEntity body data is null or not.
     */
    @Test
    public void addAddressDetailsTest() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setPName("Krunal");
        addressBookDTO.setPAddress("Mumbai");
        addressBookDTO.setPCity("Mumbai");
        addressBookDTO.setPState("MH");
        addressBookDTO.setPEmail("kunallad567@gmail.com");
        addressBookDTO.setPMobileNumber("09856365789");
        addressBookDTO.setPZip("400064");

        when(addressBookService.addPersonDetails(addressBookDTO)).thenReturn(new AddressBookDTO());
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.addPersonDetails(addressBookDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Added new Person Details", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    /***
     * Purpose : Ability to write Test Cases for updatePersonDetails() of AddressBookController.class
     *           Create an object  of the AddressBookDTO and set values to it.
     *           Using the Mockito framework, when the iAddressBookService.updatePersonDetails() is called,
     *           then return the AddressBookDTO object.
     *           Store the return value of addressBookController.updatePersonDetails()
     *           in a variable of ResponseEntity<ResponseDTO>.
     *
     * Test : Check whether the responseEntity value is null or not.
     *        Check whether the status code of responseEntity matches with the expected value.
     *        Check whether the message of the responseEntity body matches with the expected value.
     *        Check whether the responseEntity body data is null or not.
     * @throws AddressBookCustomException
     */
    @Test
    public void updateAddressDetailsTest() throws AddressBookCustomException {
        int id = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setPName("Krunal");
        addressBookDTO.setPAddress("Mumbai");
        addressBookDTO.setPCity("Mumbai");
        addressBookDTO.setPState("MH");
        addressBookDTO.setPEmail("kunallad567@gmail.com");
        addressBookDTO.setPMobileNumber("09856365789");
        addressBookDTO.setPZip("400064");

        when(addressBookService.updatePersonDetails(id, addressBookDTO)).thenReturn(new AddressBookDTO());
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.updatePersonDetails(id, addressBookDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated by ID : Address Book Details", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    /**
     * Purpose : Ability to write Test Cases for deletePersonDetails() of AddressBookController.class
     *           Using the Mockito framework, when the addressBookService.deletePersonDetails() is called,
     *           then return the AddressBookDTO object.
     *           Store the return value of addressBookController.deletePersonDetails()
     *           in a variable of ResponseEntity<ResponseDTO>.
     *
     * Test : Check whether the responseEntity value is null or not.
     *        Check whether the status code of responseEntity matches with the expected value.
     *        Check whether the message of the responseEntity body matches with the expected value.
     *        Check whether the responseEntity body data is null or not.
     *
     * @throws AddressBookCustomException
     */
    @Test
    public void deleteAddressDetailsTest() throws AddressBookCustomException {
        int id = 1;

        when(addressBookService.deletePersonDetails(id)).thenReturn(new AddressBookDTO());
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.deletePersonDetails(id);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deleted by ID : Address Book Details", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    /**
     * Purpose : Ability to write Test Cases for getPersonDetailsByID() of AddressBookController.class
     *           Using the Mockito framework, when the addressBookService.getPersonDetailsByID() is called,
     *           then return the AddressBookDTO object.
     *           Store the return value of addressBookController.getPersonDetailsByID()
     *           in a variable of ResponseEntity<ResponseDTO>.
     *
     * Test : Check whether the responseEntity value is null or not.
     *        Check whether the status code of responseEntity matches with the expected value.
     *        Check whether the message of the responseEntity body matches with the expected value.
     *        Check whether the responseEntity body data is null or not.
     *
     * @throws AddressBookCustomException
     */
    @Test
    public void getAddressDetailsByIDTest() throws AddressBookCustomException {
        int id = 1;

        when(addressBookService.getPersonDetailsByID(id)).thenReturn(new AddressBookDTO());
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.getPersonByID(id);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fetched by ID : Address Book Details", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }
}
