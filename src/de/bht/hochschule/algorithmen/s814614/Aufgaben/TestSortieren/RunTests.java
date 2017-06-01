package de.bht.hochschule.algorithmen.s814614.Aufgaben.TestSortieren;

/**
 * Created by David on 03.11.2016.
 */
public class RunTests {

    public static void main(String[] args) {

        MergesortTest testMerge = new MergesortTest();
        QuicksortTest testQuick = new QuicksortTest();
        HeapsortTest testHeap = new HeapsortTest();

        try {
            testMerge.testMergesort();
            testHeap.testHeapsort();
//            testQuick.testQuickSort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
