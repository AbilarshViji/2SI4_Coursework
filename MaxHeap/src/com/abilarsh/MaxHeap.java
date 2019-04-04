package com.abilarsh;

public class MaxHeap {
    int heapSize;
    int arrSize;
    Integer[] heap;

    public MaxHeap(int i) {
        heap = new Integer[i];
        heapSize = 0;
        arrSize = i;
    }

    public MaxHeap(Integer[] someArray) {
        arrSize = someArray.length;
        heap = new Integer[arrSize];
        heapSize = 0;
        for (int i = 0; i < someArray.length; i++) {
            this.insert(someArray[i]);
        }

    }

    public void insert(int n) {
        heapSize++;
        if (heapSize == 1) {
            heap[0] = n;
            return;
        }
        if (heapSize > arrSize) {
            Integer[] temp = heap;
            heap = new Integer[arrSize * 2];
            for (int i = 0; i < arrSize; i++) {
                heap[i] = temp[i];
            }
            arrSize *= 2;
        }
        heap[heapSize - 1] = n;
        int currentHeapSize = heapSize - 1;
        while (heap[currentHeapSize] > heap[getParent(currentHeapSize)]) {
            swap(currentHeapSize, getParent(currentHeapSize));
            currentHeapSize = getParent(currentHeapSize);
        }
    }

    public int deleteMax() {
        if (this.heapSize == 0) {
            return -1;
        }

        int max = heap[0];
        heap[0] = heap[heapSize - 1];
        heap[heapSize - 1] = null;
        int current = 0;
        heapSize--;
        if (this.heapSize == 1 || this.heapSize == 0) {
            return max;
        }

        if (heap[current] < heap[getLeftChild(current)] || heap[current] < heap[getRightChild(current)]) {
            if (heap[getLeftChild(current)] > heap[getRightChild(current)]) {
                swap(current, getLeftChild(current));
                deleteMax(getLeftChild(current));
            } else {
                swap(current, getRightChild(current));
                deleteMax(getRightChild(current));
            }
        }
        return max;
    }

    private void deleteMax(int i) {
        if (this.heapSize == 0) {
            return;
        }
        if (i >= (heapSize / 2) && i <= heapSize) {
            return;
        }
        if (heap[i] < heap[getLeftChild(i)] || heap[i] < heap[getRightChild(i)]) {
            if (heap[getLeftChild(i)] > heap[getRightChild(i)]) {
                swap(i, getLeftChild(i));
                deleteMax(getLeftChild(i));
            } else {
                swap(i, getRightChild(i));
                deleteMax(getRightChild(i));
            }
        }
    }

    public String toString() {
        String toString = "Heap: ";
        for (int i = 0; i < heapSize; i++) {
            toString += (heap[i] + ", ");
        }
        return toString;
    }

    public static void heapsort(Integer[] arrayToSort) {
        MaxHeap sorted = new MaxHeap(arrayToSort);

        for (int i = arrayToSort.length - 1; i >= 0; i--) {
            arrayToSort[i] = sorted.deleteMax();
            System.out.print(arrayToSort[i] + ", ");
        }
    }

    public int getHeapSize() {
        return heapSize;
    }

    public int getArrSize() {
        return arrSize;
    }

    public Integer[] getHeap() {
        return heap;
    }

    private int getParent(int i) {
        return (i) / 2;
    }

    private int getLeftChild(int i) {
        return (2 * i);
    }

    private int getRightChild(int i) {
        return (2 * i) + 1;
    }

    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
}
