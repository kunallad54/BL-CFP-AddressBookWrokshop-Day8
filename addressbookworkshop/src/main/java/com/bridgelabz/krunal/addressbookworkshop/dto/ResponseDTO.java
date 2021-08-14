/**
 * Purpose : To provide proper response to the end user with proper message and
 *            proper data
 *
 */
package com.bridgelabz.krunal.addressbookworkshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDTO {

    private String message;
    private Object data;

}
