package com.bridgelabz.krunal.addressbookworkshop.repository;

import com.bridgelabz.krunal.addressbookworkshop.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressBookRepository extends JpaRepository<AddressBook,Integer> {

}
