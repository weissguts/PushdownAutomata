/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_assign2_topic4_hernandez;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Hernandez
 */
class OptionPane extends WindowAdapter {

    JFrame f;

    /**
     * Gets user inputString entered into JOptionPane.
     *
     * @return
     */
    public String getInputString() {
        String inputString = (String) JOptionPane.showInputDialog(f, "Enter your String",
                "CS475", JOptionPane.PLAIN_MESSAGE);
        return inputString;
    }

    /**
     * Displays in OptionPane and console if or if not the user string was
     * accepted based on DFA guidelines.
     *
     * @param accepted
     */
    public void displayAccepted(boolean accepted) {
        printResult("Your input string entered to the DFA was " + (accepted ? ""
                : "not ") + "accepted.");
        JOptionPane.showMessageDialog(f, "Your input string entered to the DFA was "
                + (accepted ? "" : "not ") + "accepted.");
    }

    /**
     * Allows program to proceed to next step after user inputs their string.
     *
     * @param WindowEvent
     */
    public void windowClosing(WindowEvent e) {
        int a = JOptionPane.showConfirmDialog(f, "Are you sure?");
        if (a == JOptionPane.YES_OPTION) {
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    }

    /**
     * Prints if user input string was accepted.
     */
    private void printResult(String string) {
        System.out.println(string);
    }

}
