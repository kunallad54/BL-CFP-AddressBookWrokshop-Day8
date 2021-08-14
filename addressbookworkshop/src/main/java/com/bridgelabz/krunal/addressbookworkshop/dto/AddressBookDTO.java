/**
 * Purpose : To create validation patterns and conditions for each field of the entity
 *           and to make data transfer object
 */
package com.bridgelabz.krunal.addressbookworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class AddressBookDTO {

    private int pID;
    @Pattern(regexp = "^[A-Z][a-z]{2,}$",message = "Person name was Invalid")
    private String pName;

    @NotEmpty(message = "Please Enter the Proper Address")
    private String pAddress;

    @NotEmpty(message = "Please Enter City Name")
    private String pCity;

    @NotEmpty(message = "Please Enter State Name")
    private String pState;

    @Email(message = "Please Enter Valid Email")
    private String pEmail;

    @Size(min = 10,max = 10,message = "Please Enter Valid Phone Number")
    private String pMobileNumber;

    @Size(min = 6,max = 6,message = "Please Enter Valid Zip Code")
    private String pZip;

}
