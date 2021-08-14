package com.bridgelabz.krunal.addressbookworkshop.exceptions;

public class AddressBookCustomException extends Exception {

    private ExceptionType type;

    public enum ExceptionType{
        ID_NOT_FOUND;
    }
    public AddressBookCustomException(String message,ExceptionType type) {
        super(message);
        this.type = type;
    }

}
