public class CumulativeSum {

    public static int[] calculateCumulativeSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0]; // Return an empty array for null or empty input
        }

        int[] cumulativeSumArray = new int[arr.length];
        int currentSum = 0;

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i]; // Add the current element to the running sum
            cumulativeSumArray[i] = currentSum; // Store the cumulative sum at the current index
        }
        return cumulativeSumArray;
    }

    public static void main(String[] args) {
        int[] originalArray = {1, 2, 3, 4, 5};
        System.out.print("Original Array: ");
        for (int num : originalArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] cumulativeResult = calculateCumulativeSum(originalArray);

        System.out.print("Cumulative Sum Array: ");
        for (int num : cumulativeResult) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] anotherArray = {10, -5, 20, -15};
        System.out.print("Original Array: ");
        for (int num : anotherArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] anotherCumulativeResult = calculateCumulativeSum(anotherArray);

        System.out.print("Cumulative Sum Array: ");
        for (int num : anotherCumulativeResult) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}