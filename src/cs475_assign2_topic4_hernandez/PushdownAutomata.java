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
     * Pop up box to user to select file for DPA guidelines.
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
     * Sets the DPA guidelines to test against user input string.
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
     * Sets start state to first line from DPA guidelines file.
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

    /**
     * Reads transition line updates states. 
     * @param nextLine
     */
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
     * run - Big O for run method is O(N^3) where "N" is input string length,
     * number of transitions, and number of stackOperation Strings we convert
     * to charArrays. - 3 loops should be O(N^3).
     *
     * Compares user input String char by char against acceptable DPA
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
            //Add user input to top of stack.
                    stack.push(inputChars[i]);
            //lookup transistions for current state
            for (Transition transition : transitions) {
                
                System.out.println(transition.toString());
                //find transistions with split inputSymbol
                boolean stateCheck = (currentState.equals(transition.getFromState()));
                boolean inputSymbol = inputChars[i] == transition.getInputSymbol();
             
                String stackOperation = transition.getStackOperation();

                // Clear tapeAlphabet &
                // convert stackOperation to CharArray tapeAlphabet.
                tapeAlphabet.clear();
                for (char stackOperationString : stackOperation.toCharArray()) {
                    tapeAlphabet.add(stackOperationString);
                }

                //If userInput matches DPA inputSYmbol 
                //OR stackOperationString contains an "empty".
                if (stateCheck && inputSymbol || tapeAlphabet.contains('e')) { 
                   
                    //Checking if "a" in "a, b-->c" is equal to "e"(empty). From
                    //stackOperation string (tapeAlphabet). 
                    while (tapeAlphabet.contains('e')) {
                        //"If a is "empty" machine may make transition without reading
                        //any symbol from input.
                        if (tapeAlphabet.get(1) == 'e') {
                            currentState = transition.getToState();
                            break;
                        } else {
                            //If a is not "empty" replace symbol "b" at the top
                            //of the stack with a "c". 
                            if (tapeAlphabet.get(3) != 'e') {
                                stack.pop(); //pop user input to replace with c.
                                stack.push(tapeAlphabet.get(3));
                                break;
                            }
                        }

                        //Checking if "b" in "a, b-->c" is equal to "e"(empty).
                        //If "b" is empty, the machine may make this transition 
                        //without reading any symbol from the input. 
                        if (tapeAlphabet.get(2) == 'e') {
                            currentState = transition.getToState();
                            break;
                        } else if (tapeAlphabet.get(2) != 'e') {
                            stack.pop(); //pop user input from stack
                            stack.push(tapeAlphabet.get(2)); //push "b" to stack. 
                        }

                        //Checking if "c" in "a, b-->c" is equal to "e"(empty).
                        //If "c" is empty, the machine does not write any symbol
                        //on the stack when going along this transition.
                        if (tapeAlphabet.get(3) == 'e') {
                            break;
                        }                        
                        break;
                    }
                }

            }

        }
        if (!acceptStates.contains(currentState)) {
            return false;
        }
        return true;
    }

    /**
     * Gives the user the acceptable inputAlphabet parameters from the DPA
     * guidelines file.
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
