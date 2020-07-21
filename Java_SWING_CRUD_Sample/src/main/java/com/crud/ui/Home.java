/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.ui;

import com.crud.controller.BookController;
import com.crud.model.Book;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author simiyu
 */
public class Home extends JFrame {

    private JButton btnCreateNew;
    private JButton btnDetails;
    private JButton btnDelete;
    private JButton btnExit;
    
    private JTable table;
    private JScrollPane tblScroll;

    private BookController bookcontroller;
    private BookTableModel bookmodel;

    public Home() {
        this.setTitle("SAMPLE BOOK CRUD APP");
        bookcontroller = new BookController();
        initComponents();
        initLayout();

        addListeners();

        this.setVisible(true);

    }

    private void initLayout() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(100, 100, (screenSize.width / 2) - 100, (screenSize.height / 2) + 150);
        setLocationRelativeTo(null);

        setContentPane(getMainPanel());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    private JPanel getMainPanel() {
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(null);
        mainpanel.setBackground(new Color(102, 102, 255));
        mainpanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        mainpanel.add(gettable());
        mainpanel.add(getbuttonPanel());

        return mainpanel;
    }

    private JPanel getbuttonPanel() {
        JPanel button_panel = new JPanel();
        GridLayout grid = new GridLayout(1, 4, 20, 20);
        button_panel.setLayout(grid);
        button_panel.setBackground(new Color(102, 102, 255));

        button_panel.add(btnCreateNew);

        button_panel.add(btnDetails);

        button_panel.add(btnDelete);

        button_panel.add(btnExit);

        button_panel.setBounds(20, 500, 800, 40);

        return button_panel;
    }

    private JPanel gettable() {

        JPanel table_panel = new JPanel();
        table_panel.setLayout(null);
        table_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Books Listing", TitledBorder.CENTER, TitledBorder.TOP));
        //create the model
        bookmodel = new BookTableModel();
        bookmodel.addEntities(bookcontroller.loadAvailableBook(null));
        //create the table
        table = new JTable(bookmodel);

        tblScroll = new JScrollPane(table);
        tblScroll.setBounds(0, 30, 800, 440);

        table_panel.add(tblScroll);

        table_panel.setBounds(20, 20, 800, 470);

        return table_panel;
    }

    private void initComponents() {

        btnCreateNew = new JButton("Create New");
        btnCreateNew.setBackground(Color.GREEN);
        btnCreateNew.setForeground(Color.WHITE);
        btnCreateNew.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 4, true));

        btnDetails = new JButton("Details/Update");
        btnDetails.setBackground(new Color(0, 0, 102));
        btnDetails.setForeground(Color.WHITE);
        btnDetails.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 4, true));

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(Color.GRAY);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 4, true));

        btnExit = new JButton("Exit");
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.WHITE);
        btnExit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 4, true));

    }

    private void addListeners() {

        btnCreateNew.addActionListener(e -> {
            BookForm k = new BookForm(null, table);
            k.setVisible(true);

        });

        btnDetails.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(Home.this, "Please make selection and try again !!!");
            } else {
                Book book = ((BookTableModel) table.getModel()).getBookAt(row);
                BookForm f = new BookForm(book, table);
                f.setVisible(true);

            }

        });
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(Home.this, "Please make selection and try again !!!");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Delete Selected Book ?", "Confirm",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Book book = ((BookTableModel) table.getModel()).getBookAt(row);
                    BookController bcontroller = new BookController(table);
                    bcontroller.removeBankTitle(book);
                }

            }

        });
        btnExit.addActionListener(e -> {
            System.exit(0);
        });

    }
}
