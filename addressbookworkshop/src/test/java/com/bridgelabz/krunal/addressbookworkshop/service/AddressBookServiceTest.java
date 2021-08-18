/**
 * Purpose : Ability to write Test Cases for AddressBookService.class
 */
package com.bridgelabz.krunal.addressbookworkshop.service;

import com.bridgelabz.krunal.addressbookworkshop.builder.AddressBuilder;
import com.bridgelabz.krunal.addressbookworkshop.dto.AddressBookDTO;
import com.bridgelabz.krunal.addressbookworkshop.entity.AddressBook;
import com.bridgelabz.krunal.addressbookworkshop.exceptions.AddressBookCustomException;
import com.bridgelabz.krunal.addressbookworkshop.repository.IAddressBookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookServiceTest {

    @InjectMocks
    private AddressBookService addressBookService;

    @Mock
    private IAddressBookRepository addressBookRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AddressBuilder addressBuilder;

    /**
     * Purpose : Ability to Test getAddressBook Method whether it is returning proper object or not
     */
    @Test
    public void getAddressesTest() {
        List<AddressBook> addressBookList = new ArrayList<>();
        AddressBook addressBook = new AddressBook();
        addressBook.setPID(1);
        addressBook.setPName("Krunal");
        addressBook.setPAddress("Mumbai");
        addressBook.setPCity("Mumbai");
        addressBook.setPState("MH");
        addressBook.setPEmail("kunallad567@gmail.com");
        addressBook.setPMobileNumber("09856365789");
        addressBook.setPZip("400064");

        AddressBook addressBook1 = new AddressBook();
        addressBook1.setPID(2);
        addressBook1.setPName("Sonal");
        addressBook1.setPAddress("Pune");
        addressBook1.setPCity("Pune");
        addressBook1.setPState("MH");
        addressBook1.setPEmail("soanl123@gmail.com");
        addressBook1.setPMobileNumber("09654869586");
        addressBook1.setPZip("400044");

        addressBookList.add(addressBook);
        addressBookList.add(addressBook1);

        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setPID(1);
        addressBookDTO.setPName("Krunal");
        addressBookDTO.setPAddress("Mumbai");
        addressBookDTO.setPCity("Mumbai");
        addressBookDTO.setPState("MH");
        addressBookDTO.setPEmail("kunallad567@gmail.com");
        addressBookDTO.setPMobileNumber("09856365789");
        addressBookDTO.setPZip("400064");

        AddressBookDTO addressBookDTO1 = new AddressBookDTO();
        addressBookDTO1.setPID(2);
        addressBookDTO1.setPName("Sonal");
        addressBookDTO1.setPAddress("Pune");
        addressBookDTO1.setPCity("Pune");
        addressBookDTO1.setPState("MH");
        addressBookDTO1.setPEmail("soanl123@gmail.com");
        addressBookDTO1.setPMobileNumber("09654869586");
        addressBookDTO1.setPZip("400044");

        when(addressBookRepository.findAll()).thenReturn(addressBookList);
        when(modelMapper.map(addressBookList.get(0), AddressBookDTO.class)).thenReturn(addressBookDTO);
        when(modelMapper.map(addressBookList.get(1), AddressBookDTO.class)).thenReturn(addressBookDTO1);

        List<AddressBookDTO> actualAddressList = addressBookService.getAddressBook();

        assertNotNull(actualAddressList);
        for (int i = 0; i < actualAddressList.size(); i++) {
            assertEquals(i + 1, actualAddressList.get(i).getPID());
        }
        assertEquals("Krunal", actualAddressList.get(0).getPName());
        assertEquals("Sonal", actualAddressList.get(1).getPName());
    }

    /**
     * Purpose : Ability to test addPersonDetails method whether it is able to add details properly or not
     */
    @Test
    public void addAddressBookTest() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setPName("Krunal");
        addressBookDTO.setPAddress("MH");
        addressBookDTO.setPCity("Mumbai");
        addressBookDTO.setPState("MH");
        addressBookDTO.setPEmail("kunallad547@gamil.com");
        addressBookDTO.setPMobileNumber("09654589685");
        addressBookDTO.setPZip("422522");

        AddressBook addressBook = new AddressBook();
        addressBook.setPName("Krunal");
        addressBook.setPAddress("MH");
        addressBook.setPCity("Mumbai");
        addressBook.setPState("MH");
        addressBook.setPEmail("kunallad547@gamil.com");
        addressBook.setPMobileNumber("09654589685");
        addressBook.setPZip("422522");

        when(addressBuilder.buildDO(addressBookDTO)).thenReturn(addressBook);

        when(addressBookRepository.save(addressBook)).thenReturn(addressBook);

        AddressBookDTO actualAddressBookDTO = addressBookService.addPersonDetails(addressBookDTO);
        assertNotNull(actualAddressBookDTO);
        assertEquals("kunallad547@gamil.com", actualAddressBookDTO.getPEmail());
    }

    /**
     * Purpose : Ability to Test updatePersonDetails() when Id is not found it should throw proper exception
     *
     * @throws AddressBookCustomException
     */
    @Test(expected = AddressBookCustomException.class)
    public void updateAddressBook_whenFindById_shouldThrowExceptionTest() throws AddressBookCustomException {
        int id = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setPName("Krunal");
        addressBookDTO.setPAddress("MH");

        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        addressBookService.updatePersonDetails(id, addressBookDTO);
    }

    /**
     * Purpose : Ability to update details when updatePersonDetails() method is called
     *
     * @throws AddressBookCustomException
     */
    @Test
    public void updateAddressBookTest() throws AddressBookCustomException {
        int id = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setPName("Krunal");
        addressBookDTO.setPAddress("MH");

        AddressBook addressBook = new AddressBook();
        addressBook.setPID(1);
        addressBook.setPName("Test");
        addressBook.setPAddress("MH");

        when(addressBookRepository.findById(id)).thenReturn(Optional.of(addressBook));
        when(addressBookRepository.save(addressBook)).thenReturn(addressBook);
        AddressBookDTO actualAddressBook = addressBookService.updatePersonDetails(id, addressBookDTO);
        ArgumentCaptor<AddressBook> addressBookDOArgumentCaptor = ArgumentCaptor.forClass(
                AddressBook.class);
        verify(addressBookRepository, times(1)).save(addressBookDOArgumentCaptor.capture());
        assertNotNull(actualAddressBook);
        assertEquals("Krunal", addressBookDOArgumentCaptor.getValue().getPName());
        assertEquals("MH", addressBookDOArgumentCaptor.getValue().getPAddress());
    }

    /**
     * Purpose : Ability to throw exception when deletePersonDetails() is called and ID is not found
     *
     * @throws AddressBookCustomException
     */
    @Test(expected = AddressBookCustomException.class)
    public void  deleteAddressDetails_whenFindById_shouldThrowExceptionTest() throws AddressBookCustomException {
        int id = 5;
        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        addressBookService.deletePersonDetails(id);
    }

    /**
     * Purpose : Ability to delete Person Details when deletePersonDetails() method is called
     *
     * @throws AddressBookCustomException
     */
    @Test
    public void deleteAddressDetailsTest() throws AddressBookCustomException {
        int id = 1;

        AddressBook addressBook = new AddressBook();
        addressBook.setPID(1);
        addressBook.setPName("Krunal");
        addressBook.setPAddress("MH");
        addressBook.setPCity("Mumbai");
        addressBook.setPState("MH");
        addressBook.setPEmail("kunallad547@gamil.com");
        addressBook.setPMobileNumber("09654589685");
        addressBook.setPZip("422522");

        when(addressBookRepository.findById(id)).thenReturn(Optional.of(addressBook));
        when(addressBookRepository.save(addressBook)).thenReturn(addressBook);
        AddressBookDTO actualAddressBook = addressBookService.deletePersonDetails(id);
        ArgumentCaptor<AddressBook> addressBookDOArgumentCaptor = ArgumentCaptor.forClass(AddressBook.class);
        verify(addressBookRepository, times(1)).delete(addressBookDOArgumentCaptor.capture());
        assertNull(actualAddressBook);
    }

    /**
     * Purpose : Ability to throw exception when getPersonDetailsByID is called and ID is not found
     *
     * @throws AddressBookCustomException
     */
    @Test(expected = AddressBookCustomException.class)
    public void getAddressDetailsByID_whenFindById_shouldThrowExceptionTest() throws AddressBookCustomException {
        int id = 1;

        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        addressBookService.getPersonDetailsByID(id);
    }

    /**
     * Purpose : Ability to get Person Details of particular ID when getPersonDetailsByID is called
     *
     * @throws AddressBookCustomException
     */
    @Test
    public void getAddressDetailsByIDTest() throws AddressBookCustomException {
        int id = 1;

        AddressBook addressBook = new AddressBook();
        addressBook.setPID(1);
        addressBook.setPName("Krunal");
        addressBook.setPAddress("MH");
        addressBook.setPCity("Mumbai");
        addressBook.setPState("MH");
        addressBook.setPEmail("kunallad547@gamil.com");
        addressBook.setPMobileNumber("09654589685");
        addressBook.setPZip("422522");

        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setPID(1);
        addressBookDTO.setPName("Krunal");
        addressBookDTO.setPAddress("MH");
        addressBookDTO.setPCity("Mumbai");
        addressBookDTO.setPState("MH");
        addressBookDTO.setPEmail("kunallad547@gamil.com");
        addressBookDTO.setPMobileNumber("09654589685");
        addressBookDTO.setPZip("422522");

        when(addressBookRepository.findById(id)).thenReturn(Optional.of(addressBook));
        when(modelMapper.map(addressBook, AddressBookDTO.class)).thenReturn(addressBookDTO);

        AddressBookDTO actualAddressBook = addressBookService.getPersonDetailsByID(id);

        assertNotNull(actualAddressBook);
        assertEquals(1, actualAddressBook.getPID());

        assertEquals("Krunal", actualAddressBook.getPName());

    }
}
