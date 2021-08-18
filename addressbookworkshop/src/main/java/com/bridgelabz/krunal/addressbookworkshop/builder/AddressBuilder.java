package com.bridgelabz.krunal.addressbookworkshop.builder;

import com.bridgelabz.krunal.addressbookworkshop.dto.AddressBookDTO;
import com.bridgelabz.krunal.addressbookworkshop.entity.AddressBook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressBuilder {

    public AddressBook buildDO(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook();
        BeanUtils.copyProperties(addressBookDTO,addressBook);
        return addressBook;
    }
}
