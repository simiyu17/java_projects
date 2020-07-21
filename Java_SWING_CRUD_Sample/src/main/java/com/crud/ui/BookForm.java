/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.ui;

import com.crud.controller.BookController;
import com.crud.model.Book;
import com.crud.util.ConstMessagesEN;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author simiyu
 */
public class BookForm extends JFrame{
    
    private JTextField txtId;
    private JTextField textFieldbookName;
    private JTextField textFieldbookTitle;
    private JTextField textFieldauthor;
    
    private JButton btnSave;
    private JButton btnClear;
    private JButton btnCancel;
    
    private BookController bookcontroller;
    
    private BookTableModel bookmodel;
    private JTable tab;
    
    public BookForm(Book book, JTable t) {
        this.setTitle("Book Details");
        bookcontroller = new BookController();
        initLayout();

        addListeners();
        
         tab = t;
        bookmodel = new BookTableModel();

        if (book != null) {
            preLoadBookForm(book);
        }

        this.setVisible(true);

    }
    
     private void initLayout() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(100, 100, (screenSize.width / 4), (screenSize.height / 4));
        setLocationRelativeTo(null);
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(null);
        mainpanel.setBackground(new Color(102, 102, 255));
        mainpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JLabel lblBookName = new JLabel(ConstMessagesEN.BookLabels.BOOK_NAME);
        lblBookName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBookName.setBounds(20, 20, 100, 14);
        mainpanel.add(lblBookName);

        JLabel lblBookTitle = new JLabel(ConstMessagesEN.BookLabels.BOOK_TITLE);
        lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBookTitle.setBounds(20, 50, 100, 14);
        mainpanel.add(lblBookTitle);

        JLabel lblBookAuthor = new JLabel(ConstMessagesEN.BookLabels.BOOK_AUTHOR);
        lblBookAuthor.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBookAuthor.setBounds(20, 80, 100, 14);
        mainpanel.add(lblBookAuthor);
        
        txtId = new JTextField();
        
        textFieldbookName = new JTextField();
        textFieldbookName.setColumns(10);
        textFieldbookName.setBounds(167, 20, 230, 20);
        mainpanel.add(textFieldbookName);
        
        textFieldbookTitle = new JTextField();
        textFieldbookTitle.setColumns(10);
        textFieldbookTitle.setBounds(167, 50, 230, 20);
        mainpanel.add(textFieldbookTitle);
        
        textFieldauthor = new JTextField();
        textFieldauthor.setColumns(10);
        textFieldauthor.setBounds(167, 80, 230, 20);
        mainpanel.add(textFieldauthor);

        
        btnSave = new JButton("Save");
        btnSave.setBackground(new Color(0,0,102));
        btnSave.setForeground(Color.WHITE);
        btnSave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,0,102), 4, true));
        btnSave.setBounds(40, 140, 123, 30);
        mainpanel.add(btnSave);
        btnClear = new JButton("Clear");
        btnClear.setBackground(new Color(0,0,102));
        btnClear.setForeground(Color.WHITE);
        btnClear.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,0,102), 4, true));
        btnClear.setBounds(170, 140, 123, 30);
        mainpanel.add(btnClear);
        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(new Color(0,0,102));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,0,102), 4, true));
        btnCancel.setBounds(300, 140, 123, 30);
        mainpanel.add(btnCancel);
        setContentPane(mainpanel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }
 
    
    
    public Book getBook() {
        Book b = new Book();
        String id = txtId.getText();
        if (id != null && id != "" && !id.isEmpty()) {
            BookController bController = new BookController(new Long(id));
            b = bController.getSingleBook();
        }
        b.setBookName(textFieldbookName.getText());
        b.setBookTitle(textFieldbookTitle.getText());
        b.setAuthor(textFieldauthor.getText());

        return b;
    }

    private void clearForm() {
        txtId.setText("");
        textFieldbookName.setText("");
        textFieldbookTitle.setText("");
        textFieldauthor.setText("");
    }
    
    private void preLoadBookForm(Book b) {
        txtId.setText(b.getId().toString());
        textFieldbookName.setText(b.getBookName());
        textFieldbookTitle.setText(b.getBookTitle());
        textFieldauthor.setText(b.getAuthor());
    }
    
    private void addListeners() {

        btnSave.addActionListener(e -> {
            Book b = getBook();
            BookController bController = new BookController(b, tab);
            bController.saveOrUpdate(this);

        });

        btnClear.addActionListener(e -> {
            clearForm();
        });
        btnCancel.addActionListener(e -> {
            this.setVisible(false);
        });
     
    }
    
}
