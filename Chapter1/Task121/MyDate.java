package Task121;
import Task121.DateTask;

public class MyDate {
    public static void main(String[] args) {

        DateTask date1 = new DateTask(); 

        DateTask date2 = new DateTask(); 

        System.out.println("Date1 starts as: " + date1.toString()); 

        date1.setDate(12, 12, 2012); 

        System.out.println("Date1 is now: " + date1.toString()); 

        date2.setDate(1, 1, 1978); 

        System.out.println("Date2 is now: " + date2.toString()); 
       
        System.out.println("\n--- Leap Years ---");

        DateTask.leapYears(); 
    }
}