package Codelearn.OOP;

class Date {
    private int day;
    private int month;
    private int year;
    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public void setDate(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public void setDay(int day){
        this.day = day;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setYear(int year){
        this.year = year;
    }
    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public void display(){
        if(day < 10){
            if(month < 10){
                System.out.println("0"+day+"/"+"0"+month+"/"+year);
            } else {
                System.out.println("0"+day+"/"+month+"/"+year);
            }
        }else if(month < 10){
            System.out.println(day+"/"+"0"+month+"/"+year);
        } else {
            System.out.println(day+"/"+month+"/"+year);
        }
    }
}

public class Ex_25 {
    public static void main(String[] args) {
        Date d = new Date(11, 7,1993);
        d.display();
    }
}
