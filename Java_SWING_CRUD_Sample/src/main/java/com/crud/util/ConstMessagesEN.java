/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.util;

/**
 *
 * @author simiyu
 */
public interface ConstMessagesEN {
    
    interface BookLabels {
        String BOOK_NAME = "Book Name";
        String BOOK_TITLE = "Book Title";
        String BOOK_AUTHOR = "Book Author";
        
    }


    interface Messages {

        String WINDOWS_STYLE_LOADING_ERROR_MESSAGE = "There was an error while loading windows look an feel: ";
        String ALERT_TILE = "Alert";
        String NON_ROW_SELECTED = "No row has been selected";
        String INFORMATION_TITLE = "Information";
        String SUCCESS_TITLE = "Success";
        String SUCCESS_SUBMIT_MESSAGE = "Successfully Submitted!";
        String SUCCESS_DELETE_MESSAGE = "Successfully Removed!";
        String SUCCESS_POST_MESSAGE = "Successfully Posted To Legder!";
        String SUCCESS_UNPOST_MESSAGE = "Successfully unPosted To Legder!";
        String GL_FOR_POST_NOT_MAPPED_MESSAGE = "Gl Accounts not fully mapped for ";
        String ERROR_TITLE = "Failure";
        String ERROR_DUPLICATE_UNIT = "Unit with number already exists!!";
        String ERROR_DUPLICATE_CUSTOMER = "Customer with CNIC number already exists!!";
        String ERROR_DUPLICATE_NOMINEE = "Nominee with CNIC number already exists!!";
        String ALREADY_POSTED = "Already Posted! Or Contact Admin!";
        String NOT_YET_POSTED = "Never Posted! Or Contact Admin!";
        String CANT_UPDATE_POSTED_OR_HAS_CONTRACT = "You can not update posted invoice or a sales contract invoice! Or Contact Admin!";
        String FAILED_SUBMIT_MESSAGE = "An Error Occured . Contact Admin!";
        String DELETE_ROW_ERROR = "An Error Occured while trying to remove record. Check if there are any relationship between tables or Contact Admin";
    }

 

    interface ValidationMessages {

        String REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA = "Not all required fields have been filled or filled data is incorrect";
    }
}
