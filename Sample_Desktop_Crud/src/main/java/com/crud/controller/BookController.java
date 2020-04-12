/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.controller;

import com.crud.model.Book;
import com.crud.ui.BookForm;
import com.crud.ui.BookTableModel;
import com.crud.util.AlertNotification;
import com.crud.util.CommonFunction;
import com.crud.util.ConstMessagesEN;
import com.crud.validate.ValidationError;
import java.util.List;
import java.util.Optional;
import javax.swing.JTable;

/**
 *
 * @author simiyu
 */
public class BookController extends CommonFunction {

    private Book book;
    private Long bookId;
    private BookTableModel bookmodel;
    private JTable tab;

    public BookController() {
    }

    public BookController(JTable table) {
        tab = table;
    }

    public BookController(Long bId) {
        setBookId(bId);
    }

    public BookController(Book b, JTable t) {
        setBook(b);
        tab = t;
    }

    public Book getSingleBook() {
        return bookDao.findById(getBookId());
    }

    public void saveOrUpdate(BookForm p) {
        try {
            Optional<ValidationError> errors = bookValidator.validate(getBook());
            if (errors.isPresent()) {
                ValidationError validationError = errors.get();
                AlertNotification.showFormValidationAlert(validationError.getMessage());
            } else {
                bookDao.save(getBook());
                AlertNotification.showSuccessAlert(ConstMessagesEN.Messages.SUCCESS_SUBMIT_MESSAGE);
                bookmodel = (BookTableModel) tab.getModel();
                bookmodel.clear();
                bookmodel.addEntities(this.loadAvailableBook(null));
                tab.repaint();
                p.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertNotification.showFailureAlert(ConstMessagesEN.Messages.FAILED_SUBMIT_MESSAGE);
        }

    }

    public List<Book> loadAvailableBook(String nameOrAuthor) {
        List<Book> books = bookDao.getBooks(nameOrAuthor);
        return books;
    }

    public void removeBankTitle(Book b) {
        try {
            bookDao.delete(b);
            AlertNotification.showSuccessAlert(ConstMessagesEN.Messages.SUCCESS_DELETE_MESSAGE);
            bookmodel = (BookTableModel) tab.getModel();
            bookmodel.clear();
            bookmodel.addEntities(this.loadAvailableBook(null));
            tab.repaint();
        } catch (Exception e) {
            AlertNotification.showFailureAlert(ConstMessagesEN.Messages.DELETE_ROW_ERROR);
        }

    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the bookId
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

}
