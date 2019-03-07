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
        if (!(this.isIn(v))) {
            return false;
        }
        int[] tree = this.arrayTree();
        int[] delTree = new int[tree.length - 1];
        int j = 0;
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != v) {
                delTree[j] = tree[i];
                j++;
            }
        }
        sort(delTree);
        this.root = new TNode(delTree[delTree.length / 2], null, null);
        for (int i = 0; i < delTree.length; i++) {
            add(delTree[i]);
        }
        return true;
    }

    public BSTSet union(BSTSet s) {
        int[] thisArray = this.arrayTree();
        int[] sArray = s.arrayTree();
        int union[] = new int[thisArray.length + sArray.length];
        for (int i = 0; i < thisArray.length; i++) {
            union[i] = thisArray[i];
        }
        for (int i = thisArray.length; i < thisArray.length + sArray.length; i++) {
            union[i] = sArray[i - thisArray.length];
        }
        return new BSTSet(union);
    }

    public BSTSet intersection(BSTSet s) {
        if (this.size() == 0 || s.size() == 0) {
            return new BSTSet();
        }
        int[] thisArray = this.arrayTree();
        int[] sArray = s.arrayTree();
        sort(thisArray);
        sort(sArray);
        BSTSet intersect = new BSTSet();
        int i = 0, j = 0;
        while (i < thisArray.length && j < sArray.length) {
            if (thisArray[i] < sArray[j]) {
                i++;
            } else if (sArray[j] < thisArray[i]) {
                j++;
            } else {
                intersect.add(sArray[j]);
                i++;
                j++;
            }
        }
        return intersect;
    }

    public BSTSet difference(BSTSet s) {
        BSTSet union = this.union(s);
        BSTSet intersect = this.intersection(s);
        int[] intArr = intersect.arrayTree();
        for (int i = 0; i < intArr.length; i++) {
            union.remove(intArr[i]);
        }
        return union;
    }

    public int size() {
        TNode t = root;
        return size(t);
    }

    private int size(TNode t) {
        int i = 0;
        if (t != null) {
            i += size(t.left);
            i++;
            i += size(t.right);
        }
        return i;
    }

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

    public int[] arrayTree() {
        MyStack stack = new MyStack();
        int[] tree = new int[size()];
        int i = 0;
        if (this.root == null) {
            return null;
        }
        TNode t = this.root;
        while (t != null || stack.size > 0) {
            while (t != null) {
                stack.push(t);
                tree[i] = t.element;
                i++;
                t = t.left;
            }
            t = stack.pop();

            t = t.right;

        }
        return tree;
    }

    public void printBSTSet() {
        if (root == null) {
            System.out.println("This set is empty");
        } else {
            System.out.println("the set elements are: ");
            printBSTSet(root);
            System.out.println("\n");
        }
    }

    private void printBSTSet(TNode t) {
        if (t != null) {
            printBSTSet(t.left);
            System.out.print(" " + t.element + ", ");
            printBSTSet(t.right);
        }
    }

    public void printNonRec() {
        MyStack stack = new MyStack();
        if (this.root == null) {
            System.out.println("This set is empty");
        }
        TNode t = this.root;
        System.out.println("the set elements are: ");
        while (t != null || stack.size > 0) {
            while (t != null) {
                stack.push(t);
                System.out.print(t.element + ", ");
                t = t.left;
            }
            t = stack.pop();

            t = t.right;

        }
    }
}
