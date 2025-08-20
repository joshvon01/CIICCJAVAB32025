public class Task8 {

    public static int calculateSums(int... numbers) {

        int totalSum = 0;

        for (int num : numbers) {

            int cumulativeSum = 0;

            for (int i = 1; i <= num; i++) {
                cumulativeSum += i;
            }

            System.out.println("The cumulative sum for " + num + " is: " + cumulativeSum);

            totalSum += cumulativeSum;
        }

        return totalSum;
    }

    public static void main(String[] args) {

        System.out.println("Running with parameters (4, 5, 10):");
        int finalTotal = calculateSums(4, 5, 10);
        System.out.println("The total sum of all parameters is: " + finalTotal);

        System.out.println("\n---");

        System.out.println("\nRunning with parameters (1, 2, 3, 6):");
        int finalTotal2 = calculateSums(1, 2, 3, 6);
        System.out.println("The total sum of all parameters is: " + finalTotal2);
    }
}
