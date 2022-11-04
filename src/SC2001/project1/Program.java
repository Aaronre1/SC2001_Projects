package SC2001.project1;

import java.util.Arrays;
import java.util.Random;

public class Program {
    static final int THRESHOLD = 2;

    public static void main(String[] args) {
//        System.out.println("RUNNING VARIABLE SIZE with THRESHOLD 10");
        RunSize(10);
//
//        System.out.println("RUNNING VARIABLE THRESHOLD with SIZE 10,000,000");
//        RunThreshold(10000000);System.out.println("");System.out.println("");

        //System.out.println("RUNNING VARIABLE THRESHOLD 20-40 with SIZE 10,000,000");
        RunThreshold2040(10000000);
    }

    private static void RunSize(int threshold){
        // 1,000 to 10,000,000
        int size = 1000;
        for (int i=0; i<5; i++){
//            System.out.println("==============================================");
//            System.out.println("Sorting " + size + " elements");
            System.out.print(size + ", " + threshold + ", ");
            int[] mainArr = RandomArray(size);
            int[] mergeArr = mainArr.clone();
            int[] hybridArr = mainArr.clone();

            Arrays.sort(mainArr);

            MergeSort mergeSort = new MergeSort();
            mergeSort.Sort(mergeArr);

            HybridSort hybridSort = new HybridSort(threshold);
            hybridSort.Sort(hybridArr);

            SequenceEqual(mainArr, mergeArr, hybridArr);
            size *= 10;
            System.out.println("");
        }
    }

    private static void RunThreshold(int size){
        int n1 = 2;
        int threshold = 3;
        int[] masterArr = RandomArray(size);

        for (int i=0; i<10; i++){
            System.out.print(size + ", " + threshold + ", ");
            int[] mainArr = masterArr.clone();
            int[] mergeArr = mainArr.clone();
            int[] hybridArr = mainArr.clone();

            Arrays.sort(mainArr);

            MergeSort mergeSort = new MergeSort();
            mergeSort.Sort(mergeArr);

            HybridSort hybridSort = new HybridSort(threshold);
            hybridSort.Sort(hybridArr);

            SequenceEqual(mainArr, mergeArr, hybridArr);

            int temp = threshold;
            threshold = threshold + n1;
            n1 = temp;

            System.out.println("");
        }
    }

    private static void RunThreshold2040(int size){
        int threshold = 4;
        int[] masterArr = RandomArray(size);

        for (int i=0; i<41; i++){
            System.out.print(size + ", " + threshold + ", ");
            int[] mainArr = masterArr.clone();
            int[] mergeArr = mainArr.clone();
            int[] hybridArr = mainArr.clone();

            Arrays.sort(mainArr);

            MergeSort mergeSort = new MergeSort();
            mergeSort.Sort(mergeArr);

            HybridSort hybridSort = new HybridSort(threshold);
            hybridSort.Sort(hybridArr);

            SequenceEqual(mainArr, mergeArr, hybridArr);

            threshold+=2;

            System.out.println("");
        }
    }
    static Random rand = new Random();

    private static int[] RandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000);
        }
        return array;
    }

    private static boolean SequenceEqual(int[] main, int[] mergeArr, int[] hybridArr) {
        for (int i = 0; i < main.length; i++) {
            if (main[i] != mergeArr[i] || main[i] != hybridArr[i]) {
                System.out.println("Wrong sequence (main:merge:hybrid) : " + main[i] + " : " + mergeArr[i] + " : " + hybridArr[i]);
                return false;
            }
        }
        return true;
    }
}
