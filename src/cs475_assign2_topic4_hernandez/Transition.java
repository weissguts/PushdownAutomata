/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_assign2_topic4_hernandez;

/**
 *
 * @author Daniel Hernandez
 */
public class Transition {
    private String fromState;
    private char inputSymbol;
    private char stackSymbol;
    private String stackOperation;      
    private String toState;

    /**
     * Constructor
     *
     * @param fromState
     * @param inputSymbol
     * @param stackSymbol
     * @param stackOperation
     * @param toState
     */
    public Transition(String fromState, String inputSymbol, String stackSymbol, 
            String stackOperation, String toState) {
        this.fromState = fromState;
        this.inputSymbol = inputSymbol.toCharArray()[1];
        this.stackSymbol = stackSymbol.toCharArray()[0];
        this.stackOperation = stackOperation; 
        this.toState = toState;
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public char getInputSymbol() {        
        return inputSymbol;
    }

    public void setInputSymbol(char inputSymbol) {        
        this.inputSymbol = inputSymbol;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }
    
    public char getStackSymbol() {
        return stackSymbol;
    }

    public void setStackSymbol(char stackSymbol) {
        this.stackSymbol = stackSymbol;
    }  
    
    public String getStackOperation() {
        return stackOperation;
    }

    public void setStackOperation(String stackOperation) {
        this.stackOperation = stackOperation;
    }

    public String toString() {
        return "FromState: " + this.fromState + ", StackOperation: " + this.stackOperation + ", "
                + "ToState: " + this.toState + ".";
    }
}
