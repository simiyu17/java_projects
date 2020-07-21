/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.ui;

import com.crud.Main;
import com.crud.dao.common.DbUpdaterDaoImpl;
import com.crud.util.AppConfig;
import com.crud.util.Database;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author simiyu
 */
public class DatabaseSetting extends EscapeDialog {

    public DatabaseSetting() {
        super("");
        initComponent();
        initLayout();

    }

    /**
     * Init the components.
     */
    private void initComponent() {

        lbServerAddress = new JLabel("Server Address/Host");
        tfServerAddress = new JTextField(15);

        lbDatabaseName = new JLabel("Database Name");
        tfDatabaseName = new JTextField(15);

        lbPassword = new JLabel("Password");
        tfPassword = new JPasswordField(15);

        lbdbComb = new JLabel("Select Database");
        cmbDatabase = new JComboBox(Database.values());

        lbServerPort = new JLabel("Server Port");
        tfServerPort = new JTextField(15);

        lbUserName = new JLabel("Username");
        tfUserName = new JTextField(15);

        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        btnSave.addActionListener(e -> {
            Database selectedDb = (Database) cmbDatabase.getSelectedItem();

            final String providerName = selectedDb.getProviderName();
            final String databaseURL = tfServerAddress.getText();
            final String databasePort = tfServerPort.getText();
            final String databaseName = tfDatabaseName.getText();
            final String user = tfUserName.getText();
            final String pass = new String(tfPassword.getPassword());

            final String connectionString = selectedDb.getConnectString(databaseURL, databasePort, databaseName);
            final String hibernateDialect = selectedDb.getHibernateDialect();
            final String driverClass = selectedDb.getHibernateConnectionDriverClass();

            try {
                saveConfig(selectedDb, providerName, databaseURL, databasePort, databaseName, user, pass, connectionString, hibernateDialect);
                DbUpdaterDaoImpl updateDatabase = new DbUpdaterDaoImpl();
                updateDatabase.updateDatabase();

                Main.restart();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Datasource Connection!!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        btnCancel.addActionListener(e -> {
            System.exit(0);
        });

    }

    private void saveConfig(Database selectedDb, String providerName, String databaseURL, String databasePort, String databaseName, String user, String pass,
            String connectionString, String hibernateDialect) {
        AppConfig.setDatabaseProviderName(providerName);
        AppConfig.setConnectString(connectionString);
        AppConfig.setDatabaseHost(databaseURL);
        AppConfig.setDatabasePort(databasePort);
        AppConfig.setDatabaseName(databaseName);
        AppConfig.setDatabaseUser(user);
        AppConfig.setDatabasePassword(pass);
    }

    // TODO set the components with appropriate Managers
    private void initLayout() {

        Container con = getContentPane();

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST; // Align to the left?

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCenter.add(lbdbComb, gbc);

        gbc.gridy = 1;
        panelCenter.add(lbServerAddress, gbc);

        gbc.gridy = 2;
        panelCenter.add(lbServerPort, gbc);

        gbc.gridy = 3;
        panelCenter.add(lbDatabaseName, gbc);

        gbc.gridy = 4;
        panelCenter.add(lbUserName, gbc);

        gbc.gridy = 5;
        panelCenter.add(lbPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCenter.add(cmbDatabase, gbc);

        gbc.gridy = 1;
        panelCenter.add(tfServerAddress, gbc);

        gbc.gridy = 2;
        panelCenter.add(tfServerPort, gbc);

        gbc.gridy = 3;
        panelCenter.add(tfDatabaseName, gbc);

        gbc.gridy = 4;
        panelCenter.add(tfUserName, gbc);

        gbc.gridy = 5;
        panelCenter.add(tfPassword, gbc);

        JPanel panelBottom = new JPanel();
        panelBottom.add(btnSave);
        panelBottom.add(btnCancel);

        Border border = BorderFactory.createLineBorder(Color.GRAY);
        panelBottom.setBorder(border);

        JLabel lbHeading = new JLabel(
                "<html><body><h3>DATABASE SETUP</h3></body></html>");
        lbHeading.setHorizontalAlignment(JLabel.CENTER);

        con.add(lbHeading, BorderLayout.NORTH);
        con.add(panelCenter, BorderLayout.CENTER);
        con.add(panelBottom, BorderLayout.SOUTH);

    }

    private JTextField tfServerAddress;
    private JTextField tfServerPort;
    private JTextField tfDatabaseName;
    private JComboBox cmbDatabase;
    private JTextField tfUserName;
    private JPasswordField tfPassword;

    private JLabel lbdbComb;
    private JLabel lbServerAddress;
    private JLabel lbServerPort;
    private JLabel lbDatabaseName;
    private JLabel lbUserName;
    private JLabel lbPassword;

    private JButton btnSave;
    private JButton btnCancel;

    /**
     *
     */
    private static final long serialVersionUID = -5082086110462853672L;

}
