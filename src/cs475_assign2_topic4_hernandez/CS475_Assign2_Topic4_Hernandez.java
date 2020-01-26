/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_assign2_topic4_hernandez;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main Class - Creates DPA from input file. Tests user input string to see if
 * language is acceptable.
 * @author Daniel Hernandez
 */
public class CS475_Assign2_Topic4_Hernandez {

    static OptionPane optionPane = new OptionPane();
    static String inputString;
    static boolean accepted = false;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        PushdownAutomata pushdownAutomata = new PushdownAutomata();
//       prompts the user for a file location (use JFileChooser)       
        pushdownAutomata.chooseFile();
//       reads a deterministic pushdown automata (PDA) from a text file (see format below),
        pushdownAutomata.processFile();
//       displays to the user the alphabet associated with the PDA that was read
        pushdownAutomata.displayAlphabet();
//       prompts the user for an input string (use JOptionPane),
        inputString = optionPane.getInputString();
        
        accepted = pushdownAutomata.run(inputString);
        
//       displays whether the PDA accepts the user’s input string (use JOptionPane).
        optionPane.displayAccepted(accepted);
//       as Java comments give the Big-O analysis of the fileInput and run methods
//       as a separate file, submit the PDA file you “unit” tested on.
    }

}
