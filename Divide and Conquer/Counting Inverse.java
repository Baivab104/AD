public class CountInversions {

    // Function to count inversions in the array
    public static long countInversions(int arr[]) {
        int temp[] = new int[arr.length];
        return mergeSortAndCount(arr, temp, 0, arr.length - 1);
    }

    // Function to sort the array and count inversions
    private static long mergeSortAndCount(int arr[], int temp[], int left, int right) {
        long invCount = 0;
        if (left < right) {
            int mid = (left + right) / 2;

            // Count inversions in the left half
            invCount += mergeSortAndCount(arr, temp, left, mid);

            // Count inversions in the right half
            invCount += mergeSortAndCount(arr, temp, mid + 1, right);

            // Count split inversions
            invCount += mergeAndCount(arr, temp, left, mid, right);
        }
        return invCount;
    }

    // Function to merge two halves and count inversions
    private static long mergeAndCount(int arr[], int temp[], int left, int mid, int right) {
        int i = left; // Starting index for left subarray
        int j = mid + 1; // Starting index for right subarray
        int k = left; // Starting index to be sorted
        long invCount = 0;

        // Conditions are checked to ensure that i doesn't exceed mid and j doesn't exceed right
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                // There are mid - i inversions, because all elements left to i in the left subarray
                // are greater than arr[j]
                temp[k++] = arr[j++];
                invCount += (mid + 1) - i;
            }
        }

        // Copy the remaining elements of left subarray, if any
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy the remaining elements of right subarray, if any
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy the sorted subarray into Original array
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return invCount;
    }

    // Main method to test the above
    public static void main(String args[]) {
        int arr[] = { 1, 20, 6, 4, 5 };

        System.out.println("Given Array");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        long invCount = countInversions(arr);

        System.out.println("\nNumber of inversions are: " + invCount);
    }
}
