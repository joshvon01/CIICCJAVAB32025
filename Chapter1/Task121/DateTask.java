package Task121;

public class DateTask {

    private int day;
    private int month;
    private int year;

    public DateTask() {
        this(1, 1, 1);
    }

    public DateTask(int day, int month, int year) {

        this.day = 1;
        this.month = 1;
        this.year = 1;

        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public int setDay(int day) {
        int maxDays = getDaysInMonth(this.month, this.year);

        if (day >= 1 && day <= maxDays) {
            this.day = day;
            return 1;
        } else {
            System.out.println("Invalid day: " + day + " for month " + this.month + " and year " + this.year);
            return 0;
        }
    }

    public int setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
            setDay(this.day);
            return 1;
        } else {
            System.out.println("Invalid month: " + month);
            return 0;
        }
    }

    public int setYear(int year) {
        if (year > 0) {
            this.year = year;
            setDay(this.day);
            return 1;
        } else {
            System.out.println("Invalid year: " + year);
            return 0;
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }
}

