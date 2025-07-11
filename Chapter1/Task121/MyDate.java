package Task121;

public class MyDate {
    public static void main(String[] args) {

        System.out.println("Demonstrating DateTask class and encapsulation:\n");

        DateTask date1 = new DateTask();
        System.out.println("Initial date1 (no-args constructor): " + date1);

        System.out.println("\nSetting date1 to January 1st, 1978:");
        date1.setYear(1978);
        date1.setMonth(1);
        date1.setDay(1);
        System.out.println("date1 after setting: " + date1);
        System.out.println("Is " + date1.getYear() + " a leap year? " + date1.isLeapYear(date1.getYear()));


        DateTask date2 = new DateTask(21, 9, 1984);
        System.out.println("\nCreating date2 with constructor (September 21st, 1984): " + date2);


        System.out.println("Is " + date2.getYear() + " a leap year? " + date2.isLeapYear(date2.getYear()));


        System.out.println("\nDemonstrating invalid date settings:");
        System.out.println("Attempting to set date1 to February 30, 2023 (invalid day):");
        date1.setYear(2023);
        date1.setMonth(2);
        date1.setDay(30);
        System.out.println("date1 after invalid day attempt: " + date1);

        System.out.println("\nAttempting to set date1 to Month 13 (invalid month):");
        date1.setMonth(13);
        System.out.println("date1 after invalid month attempt: " + date1);

        System.out.println("\nAttempting to set date1 to Year -5 (invalid year):");
        date1.setYear(-5);
        System.out.println("date1 after invalid year attempt: " + date1);

        System.out.println("\nChecking a known leap year (2000):");
        DateTask leapYearCheck = new DateTask(1, 1, 2000);
        System.out.println("Is " + leapYearCheck.getYear() + " a leap year? " + leapYearCheck.isLeapYear(leapYearCheck.getYear()));

        System.out.println("\nChecking a known non-leap year (1900):");
        DateTask nonLeapYearCheck = new DateTask(1, 1, 1900);
        System.out.println("Is " + nonLeapYearCheck.getYear() + " a leap year? " + nonLeapYearCheck.isLeapYear(nonLeapYearCheck.getYear()));
    }
}
