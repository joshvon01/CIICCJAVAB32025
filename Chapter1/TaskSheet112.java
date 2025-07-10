class TaskSheet112 {

    public static void main(String[] args) {
        // Declare and initialize integer variables
        int a = 10;
        int b = 5;
        int result; // Variable to store the results of operations

        System.out.println("Simple Assignment with Arithmetic Operators");
        System.out.println("Values: a = " + a + ", b = " + b);

        // Simple Assignment
        result = a + b; // Addition
        System.out.println("a + b = " + result);

        result = a - b; // Subtraction
        System.out.println("a - b = " + result);

        result = a * b; // Multiplication
        System.out.println("a * b = " + result);

        result = a / b; // Division
        System.out.println("a / b = " + result);

        result = a % b; // Modulus (Remainder)
        System.out.println("a % b = " + result);

        System.out.println("\nCompound Assignments with Arithmetic Operators");
        System.out.println("Values: a = " + a + ", b = " + b);

        // Compound Addition (a = a + b; is equivalent to a += b;)
        a += b; // a becomes a + b (10 + 5 = 15)
        System.out.println("After a += b; (a = a + b): a = " + a); // a is now 15

        // Compound Subtraction (a = a - b; is equivalent to a -= b;)
        a -= b; // a becomes a - b (15 - 5 = 10)
        System.out.println("After a -= b; (a = a - b): a = " + a); // a is now 10

        // Compound Multiplication (a = a * b; is equivalent to a *= b;)
        a *= b; // a becomes a * b (10 * 5 = 50)
        System.out.println("After a *= b; (a = a * b): a = " + a); // a is now 50

        // Compound Division (a = a / b; is equivalent to a /= b;)
        a /= b; // a becomes a / b (50 / 5 = 10)
        System.out.println("After a /= b; (a = a / b): a = " + a); // a is now 10

        // Compound Modulus (a = a % b; is equivalent to a %= b;)
        a %= b; // a becomes a % b (10 % 5 = 0)
        System.out.println("After a %= b; (a = a % b): a = " + a); // a is now 0

    }
}