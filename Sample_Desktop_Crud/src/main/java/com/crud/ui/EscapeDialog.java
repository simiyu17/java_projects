package com.crud.ui;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class EscapeDialog extends JDialog {

    public EscapeDialog(String title) {
        setTitle(title);

        setModal(true);
        setAlwaysOnTop(true);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

}
