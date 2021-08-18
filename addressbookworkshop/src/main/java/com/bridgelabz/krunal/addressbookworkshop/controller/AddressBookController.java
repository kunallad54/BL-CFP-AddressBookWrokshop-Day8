/*****************************************************************************************************
 *
 * Purpose :1: Address Book Spring Project Setup
 *          2: Make sure all CURL Calls – GET, GET by ID, POST,
 *             PUT to Update by ID, and DELETE work with simple Controller
 *          3: Make sure all CURL Calls – GET, GET by ID, POST, PUT to Update by ID, and
 *             DELETE work with simple Service Layer and storing in a Local List.
 *          4: ResponseDTO,Validation,Exception Handling
 *
 * @author Krunal Lad
 * @since 14-08-2021
 *
 *******************************************************************************************************/
package com.bridgelabz.krunal.addressbookworkshop.controller;

import com.bridgelabz.krunal.addressbookworkshop.dto.AddressBookDTO;
import com.bridgelabz.krunal.addressbookworkshop.dto.ResponseDTO;
import com.bridgelabz.krunal.addressbookworkshop.entity.AddressBook;
import com.bridgelabz.krunal.addressbookworkshop.exceptions.AddressBookCustomException;
import com.bridgelabz.krunal.addressbookworkshop.service.IAddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class AddressBookController {

    /**
     * Purpose : To make use of methods of service classes
     */
    @Autowired
    private IAddressBookService addressBookService;

    /**
     * Purpose: To add new person details to database using service class addPersonDetails
     *          method and save it in the database
     *
     * @param addressBookDTO
     * @return
     */
    @PostMapping(value = "/addPersonDetails")
    public ResponseEntity<ResponseDTO> addPersonDetails(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Inside addPersonDetails() controller method");
        AddressBookDTO newPersonDetails = addressBookService.addPersonDetails(addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added new Person Details", newPersonDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Purpose : To get details of all person in the address book from the database
     *
     * @return list of persons in the address book
     */
    @GetMapping(value = "/getAddressBook")
    public ResponseEntity<ResponseDTO> getAddressBook() {
        log.info("Inside getAddressBook() Controller method");
        List<AddressBookDTO> addressBook = addressBookService.getAddressBook();
        ResponseDTO responseDTO = new ResponseDTO("Got All Persons from AddressBook", addressBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose : To get details of the person by his/her id from the database of address book
     *
     * @param id define by user
     * @return
     * @throws AddressBookCustomException if ID is not found
     */
    @GetMapping(value = "/getPersonByID")
    public ResponseEntity<ResponseDTO> getPersonByID(@RequestParam(name = "id") int id) throws AddressBookCustomException {
        log.info("Inside getPersonByID() Controller method");
        AddressBookDTO personByID = addressBookService.getPersonDetailsByID(id);
        ResponseDTO responseDTO = new ResponseDTO("Get Call with Person ID Successfull",
                personByID);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose : To update person details in the address book and again save it to database
     *
     * @param id given by user
     * @param addressBookDTO
     * @return
     * @throws AddressBookCustomException if ID is not found
     */
    @PutMapping(value = "/updatePersonDetails")
    public ResponseEntity<ResponseDTO> updatePersonDetails(@RequestParam(name = "id") int id,
                                                           @RequestBody AddressBookDTO addressBookDTO) throws AddressBookCustomException {
        log.info("Inside updatePersonDetails() method of Controller");
        AddressBookDTO updatedDetails = addressBookService.updatePersonDetails(id, addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Person Details Successfully !!!"
                , updatedDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose : To delete person details from the addressbook when id is given
     *
     * @param id given by user
     * @return
     * @throws AddressBookCustomException if ID is not found
     */
    @DeleteMapping(value = "/deletePersonDetails")
    public ResponseEntity<ResponseDTO> deletePersonDetails(@Valid @RequestParam(name = "id") int id) throws AddressBookCustomException {
        log.info("Inside deletePersonDetails() Controller method");
        AddressBookDTO addressBookAfterDeletion = addressBookService.deletePersonDetails(id);
        ResponseDTO responseDTO = new ResponseDTO("Delete Call Successfull", addressBookAfterDeletion);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
