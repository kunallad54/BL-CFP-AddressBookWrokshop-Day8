/**
 * Purpose : To perform CRUD operations and provide services to controller and interact with repository too
 */
package com.bridgelabz.krunal.addressbookworkshop.service;

import com.bridgelabz.krunal.addressbookworkshop.dto.AddressBookDTO;
import com.bridgelabz.krunal.addressbookworkshop.entity.AddressBook;
import com.bridgelabz.krunal.addressbookworkshop.exceptions.AddressBookCustomException;
import com.bridgelabz.krunal.addressbookworkshop.repository.IAddressBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService implements IAddressBookService {

    /**
     * Purpose : To communicate with repository
     */
    @Autowired
    private IAddressBookRepository addressBookRepository;

    private ModelMapper mapper = new ModelMapper();

    /**
     * Purpose : To perform add operation i.e to add person details and then save it to repository which
     *          then gets save to database with help of repo
     *
     * @param addressBookDTO
     * @return
     */
    @Override
    public AddressBookDTO addPersonDetails(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = mapper.map(addressBookDTO, AddressBook.class);
        addressBookRepository.save(addressBook);
        return addressBookDTO;
    }

    /**
     * Purpose : To get list of all persons present in the address book repo
     *
     * @return
     */
    @Override
    public List<AddressBookDTO> getAddressBook(){
//        return addressBookRepository.findAll();
        return addressBookRepository.findAll().stream().map(element -> {
            return new AddressBookDTO(element.getPID(), element.getPName(), element.getPAddress(),
                    element.getPCity(),element.getPState(),
                    element.getPEmail(), element.getPMobileNumber(),element.getPZip());
        }).collect(Collectors.toList());
    }

    /**
     * Purpose : To perform update operation by giving option to reset user details except ID
     *
     * @param id taken from user
     * @param addressBookDTO
     * @return
     * @throws AddressBookCustomException
     */
    @Override
    public AddressBook updatePersonDetails(int id, AddressBookDTO addressBookDTO) throws AddressBookCustomException {
        AddressBook updateData = findPersonByID(id);
        String[] ignoreFields = {"pID","createdDate"};
        BeanUtils.copyProperties(addressBookDTO,updateData,ignoreFields);
        addressBookRepository.save(updateData);
        return updateData;
    }

    /**
     * Purpose : To delete person details from address book by its id
     *
     * @param id taken from user
     * @return
     * @throws AddressBookCustomException
     */
    @Override
    public AddressBook deletePersonDetails(int id) throws AddressBookCustomException {
        AddressBook addressBook = findPersonByID(id);
        addressBookRepository.delete(addressBook);
        System.out.println("Deleted Successfully !!!");
        return addressBook;
    }

    /**
     * Purpose : To get person details by the id given
     *
     * @param id
     * @return
     * @throws AddressBookCustomException
     */
    @Override
    public AddressBook getPersonDetailsByID(int id) throws AddressBookCustomException {
        AddressBook addressBook = findPersonByID(id);
        return addressBook;
    }

    /**
     * Purpose : To find person id is present or not if yes then return data else throw exception
     *
     * @param id
     * @return
     * @throws AddressBookCustomException
     */
    @Override
    public AddressBook findPersonByID(int id) throws AddressBookCustomException {
        return addressBookRepository
                .findById(id)
                .orElseThrow(()-> new AddressBookCustomException("Person ID Not found", AddressBookCustomException.ExceptionType.ID_NOT_FOUND));
    }

}
