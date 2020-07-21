/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.util;

import javax.swing.JOptionPane;

/**
 *
 * @author simiyu
 */
public class AlertNotification {
    
    public static void showSuccessAlert(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                ConstMessagesEN.Messages.SUCCESS_TITLE,
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showFailureAlert(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                ConstMessagesEN.Messages.ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
    }
    
     public static void showFormValidationAlert(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                ConstMessagesEN.Messages.INFORMATION_TITLE,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showTableRowNotSelectedAlert() {
        JOptionPane.showMessageDialog(null,
                ConstMessagesEN.Messages.NON_ROW_SELECTED,
                ConstMessagesEN.Messages.ALERT_TILE,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showDeleteRowErrorMessage() {
        JOptionPane.showMessageDialog(null,
                ConstMessagesEN.Messages.DELETE_ROW_ERROR,
                ConstMessagesEN.Messages.ALERT_TILE,
                JOptionPane.ERROR_MESSAGE);
    }
}
