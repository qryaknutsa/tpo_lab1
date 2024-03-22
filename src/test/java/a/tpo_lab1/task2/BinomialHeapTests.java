package a.tpo_lab1.task2;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class BinomialHeapTests {
    static BinomialHeap binHeap;
    static ArrayList<Integer> arr;

    @BeforeEach
    void initHeap() {
        arr = new ArrayList<>(Arrays.asList(33, 54, 12, 775, 24, 19, 75));
        binHeap = new BinomialHeap();
        for (int i : arr) {
            binHeap.insert(i);
        }
    }

    @Test
    void checkSize() {
        assertEquals(binHeap.getSize(), arr.size());
    }

    @Test
    void isSorted() {
        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(75, 24, 19, 54, 33, 775, 12));
        assertEquals(binHeap.getArrayList(), temp);
    }
    @Test
    void checkMin() {
        int[] temp = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            temp[i] = arr.get(i);
        }
        Arrays.sort(temp);
        assertEquals(temp[0], binHeap.findMinimum());
    }

    @Test
    void extractMin() {
        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(775, 75, 54, 33, 24, 19));
        binHeap.extractMin();
        assertEquals(temp, binHeap.getArrayList());
    }


    @Test
    void delete() {
        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(775, 75, 24, 19, 54, 12));
        binHeap.delete(33);
        assertEquals(temp, binHeap.getArrayList());
    }


    @Test
    void deleteNotExist() {
        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(75, 24, 19, 54, 33, 775, 12));
        binHeap.delete(11111);
        assertEquals(temp, binHeap.getArrayList());
    }


    @Test
    void sameNums() {
        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(2,2,2,2,2));
        binHeap = new BinomialHeap();
        binHeap.insert(2);
        binHeap.insert(2);
        binHeap.insert(2);
        binHeap.insert(2);
        binHeap.insert(2);
        assertEquals(temp, binHeap.getArrayList());
    }
    @Test
    void extractOneNumHeap() {
        ArrayList<Integer> temp = new ArrayList<>();
        binHeap = new BinomialHeap();
        binHeap.insert(2);
        binHeap.extractMin();
        assertEquals(temp, binHeap.getArrayList());
    }


    @Test
    void isClear() {
        binHeap.clearHeap();
        assertTrue(binHeap.isEmpty());
    }





}
