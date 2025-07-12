import java.util.Arrays; // We use this to easily print our results

public class Tsk {

    public static int[] findTwoSum(int target, int... numbers) {

        for (int i = 0; i < numbers.length; i++) {
            int firstNumber = numbers[i];

            for (int j = i + 1; j < numbers.length; j++) {
                int secondNumber = numbers[j];

                if (firstNumber + secondNumber == target) {

                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {

        System.out.println("Input: Numbers = 2, 7, 11, 15 | Target = 9");
        int[] result1 = findTwoSum(9, 2, 7, 11, 15);
        if (result1 != null) {
            System.out.println("Output: " + Arrays.toString(result1));
        } else {
            System.out.println("Output: target not found");
        }
        System.out.println();

        System.out.println("Input: Numbers = 3, 2, 4 | Target = 6");
        int[] result2 = findTwoSum(6, 3, 2, 4);
        if (result2 != null) {
            System.out.println("Output: " + Arrays.toString(result2));
        } else {
            System.out.println("Output: target not found");
        }
        System.out.println();

        System.out.println("Input: Numbers = 2, 3, 6, 6 | Target = 20");
        int[] result3 = findTwoSum(20, 2, 3, 6, 6);
        if (result3 != null) {
            System.out.println("Output: " + Arrays.toString(result3));
        } else {
            System.out.println("Output: target not found");
        }
        System.out.println();

        System.out.println("Input: Numbers = 10, 20, 30 | Target = 50");
        int[] result4 = findTwoSum(50, 10, 20, 30);
        if (result4 != null) {
            System.out.println("Output: " + Arrays.toString(result4));
        } else {
            System.out.println("Output: target not found");
        }
    }
}