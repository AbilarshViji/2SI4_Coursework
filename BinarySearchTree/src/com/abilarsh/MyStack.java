package com.abilarsh;

import java.util.EmptyStackException;

public class MyStack {
    int size; //stores the number of objects within the stack
    StackNode top; //stores the top of the stack


    public MyStack(){
        this.top = null;
        this.size = 0;
    }

    void push(TNode itemToAdd) { //adds item to the top of stack
        size++;
        StackNode n = new StackNode(itemToAdd, this.top);
        this.top = n;
    }

    boolean empty() { //returns a boolean on if the stack is empty or not
        if (size == 0) {
            return false;
        } else {
            return true;
        }
    }

    StackNode peek() { //returns the object at the top of the stack
        return top;
    }

    TNode pop() { //removes and returns the data stored at the top
        TNode temp;

        if (size == 0) { //if the stack is empty, return null
            throw new EmptyStackException();
        }
        temp = top.element;
        top = top.previous;
        size--;
        return temp;
    }

    int top(){
        return this.top.element.element;
    }

    int size(){
        return this.size;
    }
}
