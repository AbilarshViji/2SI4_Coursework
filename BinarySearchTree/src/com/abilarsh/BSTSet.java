package com.abilarsh;

public class BSTSet {
    private TNode root;
    
    public BSTSet(){
        root = null;
    }
    
    public BSTSet(int [] input){
        
    }
    
    public boolean isIn(int v){
        
    }
    
    public void add(int v){
        
    }
    
    public boolean remove(int v){
        
    }

    public BSTSet union(BSTSet s){
        
    }
    
    public BSTSet intersection(BSTSet s){
        
    }
    
    public BSTSet difference(BSTSet s){
        
    }
    
    public int size(){
        
    }
    
    public int height(){
        
    }
    
    public void printBSTset(){
        if(root==null){
            System.out.println("This set it empty");
        }
        else{
            System.out.println("the set elements are: ");
            printBSTset(root);
            System.out.println("\n");
        }
    }
    
    
    public void printBSTset(TNode t){
        if(t!=null){
            printBSTset(t.left);
            System.out.println(" "+ t.element + ", ");
            printBSTset(t.right);
        }
    }
    
    public void printNonRec(){
        // TODO: 25/02/19 This method is nonrecusrive and uses a stack to inplement the inorder traversal 
    }
}
