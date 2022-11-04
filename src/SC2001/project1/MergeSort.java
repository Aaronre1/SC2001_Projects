package SC2001.project1;

public class MergeSort {
    long mergeSortKey;
    MergeSort(){
        mergeSortKey = 0;
    }
    public void Sort(int[] arr) {
        long startTime = System.nanoTime();
        mergeSort(arr);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
//        System.out.println("------------------------------------");
//        System.out.println("MergeSort sorted " + arr.length + " elements in " + executionTime/1000000 + " ms | " + executionTime + "ns");
//        System.out.println("Merge sort key comparison: " + mergeSortKey);
        System.out.print(mergeSortKey + ", " + executionTime + ", ");

    }

    private void mergeSort(int[] inputArray) {
        int inputLength = inputArray.length;
        //base case
        if (inputLength < 2) {
            return;
        }

        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];

        //assign left half
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = inputArray[i];
        }
        //assign right half, start 0
        for (int i = midIndex; i < inputLength; i++) {
            rightHalf[i - midIndex] = inputArray[i];
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        //merge to one
        merge(inputArray, leftHalf, rightHalf);
    }

    private void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;
        //loop till run out of element in the left or right arr
        while (i < leftSize && j < rightSize) {
            //add to arr if left is smaller
            if (leftHalf[i] <= rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            } else { //else add right
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        //clean up to add remaining
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

    private static void printArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}
