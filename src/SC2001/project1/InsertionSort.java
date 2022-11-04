package SC2001.project1;

public class InsertionSort {
    long intSortKey;

    public int[] Sort(int[] arr) {
        intSortKey = 0;
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
}
