/**
 * Purpose : To perform CRUD operations and provide services to controller and interact with repository too
 */
package com.bridgelabz.krunal.addressbookworkshop.service;

import com.bridgelabz.krunal.addressbookworkshop.builder.AddressBuilder;
import com.bridgelabz.krunal.addressbookworkshop.dto.AddressBookDTO;
import com.bridgelabz.krunal.addressbookworkshop.entity.AddressBook;
import com.bridgelabz.krunal.addressbookworkshop.exceptions.AddressBookCustomException;
import com.bridgelabz.krunal.addressbookworkshop.repository.IAddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressBookService implements IAddressBookService {

    /**
     * Purpose : To communicate with repository
     */
    @Autowired
    private IAddressBookRepository addressBookRepository;

    @Autowired
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private AddressBuilder addressBuilder;

    /**
     * Purpose : To perform add operation i.e to add person details and then save it to repository which
     *          then gets save to database with help of repo
     *
     * @param addressBookDTO
     * @return
     */
    @Override
    public AddressBookDTO addPersonDetails(AddressBookDTO addressBookDTO) {
        log.info("Inside addPersonDetails() method");
        AddressBook addressBook = addressBuilder.buildDO(addressBookDTO);
        AddressBook addressBook1 = addressBookRepository.save(addressBook);
        addressBookDTO.setPID(addressBook1.getPID());
        return addressBookDTO;
    }

    /**
     * Purpose : To get list of all persons present in the address book repo
     *
     * @return
     */
    @Override
    public List<AddressBookDTO> getAddressBook(){
        log.info("Inside getAddressBook() method");
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
    public AddressBookDTO updatePersonDetails(int id, AddressBookDTO addressBookDTO) throws AddressBookCustomException {
        log.info("Inside updatePersonDetails() method");
        AddressBook updateData = findPersonByID(id);
        String[] ignoreFields = {"pID","createdDate"};
        BeanUtils.copyProperties(addressBookDTO,updateData,ignoreFields);
        addressBookRepository.save(updateData);
        return addressBookDTO;
    }

    /**
     * Purpose : To delete person details from address book by its id
     *
     * @param id taken from user
     * @return
     * @throws AddressBookCustomException
     */
    @Override
    public AddressBookDTO deletePersonDetails(int id) throws AddressBookCustomException {
        log.info("Inside deletePersonDetails() method");
        AddressBook addressBook = findPersonByID(id);
        addressBookRepository.delete(addressBook);
        System.out.println("Deleted Successfully !!!");
        return null;
    }

    /**
     * Purpose : To get person details by the id given
     *
     * @param id
     * @return
     * @throws AddressBookCustomException
     */
    @Override
    public AddressBookDTO getPersonDetailsByID(int id) throws AddressBookCustomException {
        log.info("Inside getPersonDetailsByID() method");
        AddressBook addressBook = findPersonByID(id);
        AddressBookDTO addressBookDTO = mapper.map(addressBook,AddressBookDTO.class);
        return addressBookDTO;
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
        log.info("Inside findPersonByID() method");
        return addressBookRepository
                .findById(id)
                .orElseThrow(()-> new AddressBookCustomException("Person ID Not found", AddressBookCustomException.ExceptionType.ID_NOT_FOUND));
    }

}
