package ru.vgeser.application;

import java.util.*;

public class Application {

    static private Random r = new Random(22022022);
    private final Heap heap = new Heap();
    private final long ceiling;

    public Application() {
        ceiling = Math.round(Math.sqrt(Integer.MAX_VALUE));
    }
    public Application(long ceiling){
        this.ceiling = ceiling;
    }

    /**
     * creating an ArrayList of int arrays
     * each array has unique length with maximum of set ceiling
     * ArrayList type used for memory safety
     * @param quantity - number of int arrays
     * @return - ArrayList of specified number of int arrays
     */
    private ArrayList<int[]> makeArrayList(int quantity) {
        ArrayList<int[]> res = new ArrayList<>(quantity);
        ArrayList<Integer> lengths = new ArrayList<>(quantity);
        for (int i = 0; i < ceiling; i++) {
            lengths.add(i);
        }
        Collections.shuffle(lengths);
        for (int i = 0; i < quantity; i++) {
            int[] arr = new int[lengths.get(i)];
            res.add(arr);
        }
        return res;
    }

    /**
     * filling arrays with random numbers
     * random number generator is different for each array
     * @param arr - filled array
     */
    private void fillArray(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            arr[i] = r.nextInt();
        }
        long millis = System.currentTimeMillis();
        r.setSeed(millis);
    }

    /**
     * unboxing ArrayList and passing its int arrays to be filled
     * @param given - ArrayList with all of its int arrays filled with random numbers
     */
    private void fillArrayList(ArrayList<int[]> given) {
        given.parallelStream().forEach(this::fillArray);
    }

    /**
     * simple function of reversing an int array
     * Collections.reverse method not used because it does not work with primitive type arrays
     * @param arr - reversed array
     */
    private void reverseArray(int[] arr) {
        int len = arr.length / 2;
        for (int i = 0; i < len; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    /**
     * sorting int arrays of a given ArrayList
     * @param given - input ArrayList to be sorted
     */
    private void sortArrayList(ArrayList<int[]> given) {
        int size = given.size();
        given.parallelStream().forEach(heap::heapsort);
        for (int i = 0; i < size; i++) {
            if (i % 2 != 0) {
                int[] tmp = given.get(i);
                reverseArray(tmp);
                given.set(i, tmp);
            }
        }
    }

    /**
     * the main required function
     * calls other methods and operates with their output
     * @param n - number of arrays to be created
     * @return - two-dimensional array of ints
     */
    public int[][] myFunc(int n) {
        ArrayList<int[]> temp = makeArrayList(n);
        this.fillArrayList(temp);
        this.sortArrayList(temp);
        int[][] answer = new int[n][];
        temp.toArray(answer);
        return answer;
    }
}
