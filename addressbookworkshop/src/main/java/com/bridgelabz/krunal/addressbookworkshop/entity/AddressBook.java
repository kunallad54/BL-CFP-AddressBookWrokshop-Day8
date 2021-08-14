package com.bridgelabz.krunal.addressbookworkshop.entity;

import com.bridgelabz.krunal.addressbookworkshop.dto.AddressBookDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pID;
    private String pName;
    private String pAddress;
    private String pCity;
    private String pState;
    private String pEmail;
    private String pMobileNumber;
    private String pZip;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public AddressBook(){
        this.createdDate=LocalDate.now();
        this.updatedDate=LocalDate.now();
    }
}
