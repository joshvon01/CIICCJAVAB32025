package OtherTask;
import java.util.Arrays;

public class twoSum{
    public static int[] findtwoSum(int target, int... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int firstNumber = numbers[i];
            
            for (int j = i + 1; j < numbers.length; j++) {
                int secondNumber = numbers[j];

                if (firstNumber + secondNumber == target) {
                return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Input: Numbers = 2, 7, 11, 15 | Target = 9");
        int[] result1 = findtwoSum(9, 2, 7, 11, 15);
        if (result1 != null){
            System.out.println("Output: " + Arrays.toString(result1));
        } else {
            System.out.println("Output: target not found");
        }
        System.out.println();
        
        System.out.println("Input: Numbers = 3, 2, 4 | Target = 6");
        int[] result2 = findtwoSum(6, 3, 2, 4);
        if (result2 != null){
            System.out.println("Output: " + Arrays.toString(result2));
        } else {
            System.out.println("Output: target not found");
        }
        System.out.println();  
    }

}