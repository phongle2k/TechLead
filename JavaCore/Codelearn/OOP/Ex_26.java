package Codelearn.OOP;

class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public void setTime(int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public void setHour(int hour){
        this.hour = hour;
    }
    public void setMinute(int minute){
        this.minute = minute;
    }
    public void setSecond(int second){
        this.second = second;
    }
    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }
    public int getSecond(){
        return second;
    }
    public void nextSecond(){
        // neu time: 23:59:59 thi nextSecond: 0 : 0 : 0 => break
        // neu time: 03:59:59 thi nextSecond: 04: 00 : 00 => break
        // neu time: 03:04:59 thi nextSecond: 03:05:00 => break
        if(hour == 23 && minute == 59 && second == 59){
            hour = 0;
            minute = 0;
            second = 0;
            return;
        }
        if(minute == 59 && second == 59){
            hour++;
            minute = 0;
            second = 0;
            return;
        }
        if(second == 59){
            minute++;
            second = 0;
            return;
        }
        second++;
    }
    public void previousSecond(){
        // Neu time: 00:00:00 thi previousSecond: 23:59:59
        // Neu time: 03:00:00 thi previousSecond: 02:59:59
        // Neu time: 03:05:00 thi previousSecond: 03:04:99
        if(hour == 0 && minute == 0 && second == 0){
            hour = 23;
            minute = 59;
            second = 59;
            return;
        }
        if(minute == 0 && second == 0){
            hour--;
            minute = 59;
            second = 59;
            return;
        }
        if(second == 0){
            minute--;
            second = 59;
            return;
        }
        second--;
    }
    public void display(){
//        if(hour < 10){
//            if(minute < 10){
//                if (second < 10){
//                    System.out.println("0"+hour+":0"+minute+":0"+second);
//                } else {
//                    System.out.println("0"+hour+":0"+minute+":"+second);
//                }
//            } else {
//                if(second < 10){
//                    System.out.println("0"+hour+":"+minute+":0"+second);
//                }
//                else {
//                    System.out.println("0"+hour+":"+minute+":"+second);
//                }
//            }
//        } else {
//            if(minute < 0){
//
//            }
//        }
        String hour = this.hour + "";
        String minute = this.minute + "";
        String second = this.second + "";
        if(hour.length() == 1){
            hour = '0' + hour;
        }
        if(minute.length() == 1){
            minute = '0' + minute;
        }
        if(second.length() == 1){
            second = '0' + second;
        }
        System.out.println(hour+":"+minute+":"+second);
    }
}

public class Ex_26 {
    public static void main(String[] args) {
        Time t = new Time(23, 59, 59);
        t.nextSecond();
        t.display();

        t.setTime(0, 0, 0);
        t.previousSecond();
        t.display();

        t.setTime(2,4,13);
        t.nextSecond();
        t.display();
    }
}
