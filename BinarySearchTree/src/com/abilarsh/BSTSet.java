package com.abilarsh;

public class BSTSet {
    private TNode root;

    public BSTSet() {
        root = null;
    }

    public BSTSet(int[] input) {
        if (input.length > 0) {
            sort(input);
            root = new TNode(input[input.length / 2], null, null);
            for (int i = 0; i < input.length; i++) {
                add(input[i]);
            }
        } else {
            root = null;
        }
    }

    public void sort(int input[]) {
        int len = input.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (input[j] > input[j + 1]) {
                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
        }
    }


    public boolean isIn(int v) {
        TNode temp = root;

        while (true) {
            if (temp == null) {
                return false;
            }

            if (v > temp.element) {
                temp = temp.right;
            } else if (v < temp.element) {
                temp = temp.left;
            } else {
                return true;
            }
        }
    }

    public void add(int v) {
        if (root == null) {
            root = new TNode(v, null, null);
        } else {
            TNode current = root;
            TNode prev = root;
            while (current != null) {
                prev = current;
                if (v > current.element) {
                    current = current.right;
                } else if (v < current.element) {
                    current = current.left;
                } else if (v == current.element) {
                    return;
                }
            }
            if (v > prev.element) {
                prev.right = new TNode(v, null, null);
            } else {
                prev.left = new TNode(v, null, null);
            }
        }
    }


    public boolean remove(int v) {
        TNode current = root;
        TNode prev = root;
        while (current.element != v) {
            prev = current;
            if (v > current.element) {
                current = current.right;
            } else if (v < current.element) {
                current = current.left;
            }
        }
        if (current.element == v) {
            if (prev.element > v) {
                prev.left = null;
            } else if (prev.element < v) {
                prev.right = null;
            } else {
                //TODO its gonna break the tree
            }
            return true;
        }
        return false;
    }

    /*
        public BSTSet union(BSTSet s) {

        }

        public BSTSet intersection(BSTSet s) {

        }

        public BSTSet difference(BSTSet s) {

        }

        public int size() {

        }
    */
    public int height() {
        TNode t = root;
        if (root == null) {
            return -1;

        }
        return height(root);
    }

    private int height(TNode t) {
        if (t == null) {
            return 0;
        }

        int left = height(t.left);
        int right = height(t.right);

        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }

    public void printBSTset() {
        if (root == null) {
            System.out.println("This set it empty");
        } else {
            System.out.println("the set elements are: ");
            printBSTset(root);
            System.out.println("\n");
        }
    }

    /*
        public int[] list(TNode t) {
            int[] tree = new int[size()];
            int i = 0;
            if (t != null) {
                list(t.left);
                tree[i] = t.element;
                i++;
                list(t.right);
            }
            return tree;
        }
    */
    private void printBSTset(TNode t) {
        if (t != null) {
            printBSTset(t.left);
            System.out.println(" " + t.element + ", ");
            printBSTset(t.right);
        }
    }

    public void printNonRec() {
        // TODO: 25/02/19 This method is nonrecusrive and uses a stack to inplement the inorder traversal 
    }
}
