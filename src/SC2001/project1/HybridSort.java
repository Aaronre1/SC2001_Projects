package SC2001.project1;

public class HybridSort {
    public HybridSort(int threshold) {
        THRESHOLD = threshold;
        intSortKey = 0;
        mergeSortKey = 0;
    }

    int THRESHOLD;
    long intSortKey;
    long mergeSortKey;

    public void Sort(int[] arr) {
        long startTime = System.nanoTime();
        mergeSort(arr);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
//        System.out.println("------------------------------------");
//        System.out.println("HybridSort sorted "+ arr.length + " elements in " + executionTime/1000000 + " ms | " + executionTime + " ns " + " with threshold of " + THRESHOLD);
//        System.out.println("Insert sort key comparison: " + intSortKey);
//        System.out.println("Merge sort key comparison: " + mergeSortKey);
//        System.out.println("Total key comparison: " + (mergeSortKey + intSortKey));

        System.out.print((mergeSortKey + intSortKey) + ", " + executionTime);
    }

    private void mergeSort(int[] inputArray) {
        int inputLength = inputArray.length;
        // base case
        if (inputLength < 2) {
            return;
        }
        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];

        // assign left half
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = inputArray[i];
        }
        // assign right half, start 0
        for (int i = midIndex; i < inputLength; i++) {
            rightHalf[i - midIndex] = inputArray[i];
        }
        if (leftHalf.length <= THRESHOLD) {
            InsertSort(leftHalf);
        } else {
            mergeSort(leftHalf);
        }
        if (rightHalf.length <= THRESHOLD) {
            InsertSort(rightHalf);
        } else {
            mergeSort(rightHalf);
        }
        // merge to one
        merge(inputArray, leftHalf, rightHalf);
    }

    private void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;
        // loop till run out of element in the left or right arr
        while (i < leftSize && j < rightSize) {
            // add to arr if left is smaller
            if (leftHalf[i] <= rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            } else { // else add right
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }
        // clean up to add remaining
        while (i < leftSize) {
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }
        mergeSortKey++;
    }

    public int[] InsertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // if value of arr[0] > arr[1]
            for (int j=i; j>0; j--){
                intSortKey++;
                if (arr[j] < arr[j-1]){
                    int tmp = arr[j];
                    // move to next if greater
                    arr[j] = arr[j - 1];
                    // move the smaller value to left
                    arr[j - 1] = tmp;
                } else {
                    break;
                }
            }
        }
        return arr;
    }
    private int[] insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            // j = 1, initial
            int j = i;
            // if value of arr[0] > arr[1]
            while ((j > 0) && (arr[j - 1] > arr[j])) {
                int tmp = arr[j];
                // move to next if greater
                arr[j] = arr[j - 1];
                // move the smaller value to left
                arr[j - 1] = tmp;
                intSortKey++;
                j--;
            }
        }
        // printArray(arr);
        return arr;
    }
}
