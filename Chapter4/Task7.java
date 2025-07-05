import java.util.*;
public class Task7 {
    public arithmetic(){
        int num1;
        int num2;
        public arithmetic(int a, int b){
            this.num1 = a;
            this.num2 = b;
        }
    public int add(){
        return a + b;
    }
    public int diff() {
        return a - b;
    }
    public int multi() {
        return a * b;
    }
    public double divide() {
            if (b != 0) {
                return (double) a / b;
            } else {
                System.out.println("Error: Division by zero!");
                return Double.NaN; // Not a Number
            }
    }
    public static void main(String[] args){
        System.out.println("Enter First Number: ");
    }
}
}


    


