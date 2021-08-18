package com.bridgelabz.krunal.addressbookworkshop.service;

import com.bridgelabz.krunal.addressbookworkshop.dto.AddressBookDTO;
import com.bridgelabz.krunal.addressbookworkshop.entity.AddressBook;
import com.bridgelabz.krunal.addressbookworkshop.exceptions.AddressBookCustomException;

import java.util.List;

public interface IAddressBookService {

    AddressBookDTO addPersonDetails(AddressBookDTO addressBookDTO);

    List<AddressBookDTO> getAddressBook();

    AddressBookDTO updatePersonDetails(int id, AddressBookDTO addressBookDTO) throws AddressBookCustomException;

    AddressBook findPersonByID(int id) throws AddressBookCustomException;

    AddressBookDTO deletePersonDetails(int id) throws AddressBookCustomException;

    AddressBookDTO getPersonDetailsByID(int id) throws AddressBookCustomException;
}
