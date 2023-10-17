/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MsgBox {

    public static void alert(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, "EduSys",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component parentComponent, String message) {
        int result = JOptionPane.showConfirmDialog(parentComponent, message, "EduSys",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parenComponent, String message) {
        return JOptionPane.showInputDialog(parenComponent, message, "EduSys", JOptionPane.INFORMATION_MESSAGE);
    }
}
