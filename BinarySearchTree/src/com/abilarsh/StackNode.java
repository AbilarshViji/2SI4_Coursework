package com.abilarsh;

public class StackNode {
    TNode element;
    StackNode previous;

    public StackNode(TNode i, StackNode p){
        element = i;
        previous = p;
    }
}
