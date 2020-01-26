/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_assign2_topic4_hernandez;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents acceptable Pushdown Automata.
 *
 * @author Daniel Hernandez
 */
public class PushdownAutomata {

    static Stack stack = new Stack();
            
    private static String startState;
    private static List<String> acceptStates = new ArrayList<>();
    private static ArrayList<String> states = new ArrayList<>();

    static BufferedReader bufferedReader = null;

    static List<Transition> transitions = new ArrayList<>();    
    private static String currentState;  
    static List<Character> inputAlphabet = new ArrayList<>();
    static List<Character> tapeAlphabet = new ArrayList<>();
  

    /**
     * Pop up box to user to select file for DFA guidelines.
     *
     * @throws FileNotFoundException
     */
    public static void chooseFile() throws FileNotFoundException {
        //read file
        FileChooser fcs = new FileChooser();
        bufferedReader = new BufferedReader(new FileReader(fcs.Chooser()));
    }

    /**
     * processFile - Big O for fileInput and processFile is O(n) since we are
     * just iterating through the while loop and there is nothing nested inside.
     *
     * Sets the DFA guidelines to test against user input string.
     *
     * @throws IOException
     */
    public static void processFile() throws IOException {

        //read startState
        String firstLine = bufferedReader.readLine();
        if (firstLine != null) {
            startState(firstLine);
        }

        //read acceptStates
        String secondLine = bufferedReader.readLine();
        if (secondLine != null) {
            readAcceptStates(secondLine);
        }

        //read transition
        String nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            // Loop transitions
            readTransition(nextLine);
            nextLine = bufferedReader.readLine();
        }
    }

    /**
     * Sets start state to first line from DFA guidelines file.
     *
     * @param firstLine
     */
    public static void startState(String firstLine) {
        startState = firstLine;
    }

    /**
     * Uses regex to filter out the states and adds them to a list.
     *
     * @param secondLine
     */
    public static void readAcceptStates(String secondLine) {
        acceptStates = Arrays.asList(secondLine.split(" "));
    }

    public static void readTransition(String nextLine) {
        //split line
        String[] lineString = nextLine.split(" ");
        String fromState = lineString[0];
        String inputSymbol = lineString[1];  
        String stackSymbol = lineString[3]; 
        String stackOperation = lineString[1].concat(lineString[2].concat(lineString[3]));
        String toState = lineString[4];
        //create transition
        Transition transition = new Transition(fromState, inputSymbol, stackSymbol,
                stackOperation, toState);
        transitions.add(transition);
        //add to inputAlphabet unless input is 'e'. 'e' is for empty input.
        if (!inputAlphabet.contains(transition.getInputSymbol())
                && transition.getInputSymbol() != 'e') {
            inputAlphabet.add(transition.getInputSymbol());
//            if (tapeAlphabet.get(3) == '$' && ( stack.peek() == 0)) {
//                    stack.push('$');
//                }
        }
        //add to States
        if (!states.contains(fromState)) {
            states.add(fromState);
        }
        if (!states.contains(toState)) {
            states.add(toState);
        }              

    }

    /**
     * run - Big O for run method is O(N * M) where "N" is input string length.
     * "M" is number of transitions.
     *
     * Compares user input String char by char against acceptable DFA
     * guidelines.
     *
     * @param input
     * @return
     */
    public boolean run(String input) {
        stack.push('$');
        //split input
        char[] inputChars = input.toCharArray();
        //for split
        System.out.println("Start state is " + startState);
        currentState = startState;
        for (int i = 0; i < inputChars.length; i++) {
            //if split, check in inputAlphabet
            System.out.println("current state is "
                    + currentState);
            if (!inputAlphabet.contains(inputChars[i])) {
                return false;
            }
            //lookup transistions for current state
            for (Transition transition : transitions) {
                System.out.println(transition.toString());
                //find transistions with split inputSymbol
                boolean stateCheck = (currentState.equals(transition.getFromState()));                
                boolean inputSymbol = inputChars[i] == transition.getInputSymbol();
                String stackOperation = transition.getStackOperation();
                
                // Convert stackOperation to CharArray tapeAlphabet.
                for (char c : stackOperation.toCharArray()) {
                    tapeAlphabet.add(c); 
                }
                
                while (tapeAlphabet.contains('e')) {
                    if (tapeAlphabet.get(1) == 'e') {
                        currentState = transition.getToState();
                        break;
                    } else {
                        stack.push(tapeAlphabet.get(3));
                    }

                    if (tapeAlphabet.get(2) == 'e') {
                        currentState = transition.getToState();
                        stack.push(tapeAlphabet.get(inputChars[i]));
                        break;
                    } else if(tapeAlphabet.get(2) != 'e')  {                        
                        stack.pop();                       
                    }

                    if (tapeAlphabet.get(3) == 'e') {
                        stack.pop();
                        currentState = transition.getToState();
                        break;
                    } else if(tapeAlphabet.get(3) != 'e') {
                        
                    }
                    break;
                } 

                if (stateCheck && inputSymbol) {
                    currentState = transition.getToState();
                    break;
                }
            }

        }
        if (!acceptStates.contains(currentState)) {
            return false;
        }

        return true;
    }
    
    
    
    

    /**
     * Gives the user the acceptable inputAlphabet parameters from the DFA guidelines
 file.
     *
     * @return
     */
    public String displayAlphabet() {
        System.out.println("\u03A3 = " + inputAlphabet);
        return null;
    }

    public static ArrayList<String> states() {
        return states;
    }

}