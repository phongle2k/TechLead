package Codelearn.OOP;

import java.util.Scanner;

class Rectangle {
    double width, length;
    public void getInformation(){
        Scanner sc = new Scanner(System.in);
        width = sc.nextDouble();
        length = sc.nextDouble();
    }
    public double Area(){
        return width * length;
    }
    public double Perimeter(){
        return (width + length) * 2;
    }
    public void display(){
        System.out.println("Area: " + Area());
        System.out.println("Perimeter: " + Perimeter());
    }
}

public class Ex_3 {
    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.getInformation();
        r.display();
    }
}
