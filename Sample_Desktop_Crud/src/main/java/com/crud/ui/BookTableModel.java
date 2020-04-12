/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.ui;

import com.crud.model.Book;
import com.crud.util.ConstMessagesEN;

/**
 *
 * @author simiyu
 */
public class BookTableModel extends DefaultTableModel<Book> {

    @Override
    public String[] getColumnLabels() {
        return new String[]{
            ConstMessagesEN.BookLabels.BOOK_NAME,
            ConstMessagesEN.BookLabels.BOOK_AUTHOR,
            ConstMessagesEN.BookLabels.BOOK_TITLE};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return book.getBookName();
            case 1:
                return book.getAuthor();
            case 2:
                return book.getBookTitle() == null ? "" : book.getBookTitle();
            default:
                return "";
        }
    }

    public Book getBookAt(int row) {
        return entities.get(row);
    }
 
}
