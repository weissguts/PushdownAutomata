/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_assign2_topic4_hernandez;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Stack class
 * @author Daniel Hernandez
 */
public class Stack {

    private LinkedList<Character> items;

    public Stack() {
        this.items = new LinkedList<Character>();
    }

    public void push(Character item) {
        items.push(item);
    }

    public Character peek() {
        return items.getFirst();
    }

    public Character pop() {
        Iterator<Character> iter = items.iterator();
        Character item = iter.next();
        if (item != null) {
            iter.remove();
            return item;
        }
        return null;
    }

}
