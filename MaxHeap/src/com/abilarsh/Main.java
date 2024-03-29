package com.abilarsh;

public class Main {

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(4);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.insert(22);
        maxHeap.insert(9);
        System.out.println(maxHeap.toString());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.toString());

        Integer[] test = {5, 12, 3, 2, 54353, 1, 420, 14, 34, 4};
        maxHeap.heapsort(test);
        return;
    }
}
