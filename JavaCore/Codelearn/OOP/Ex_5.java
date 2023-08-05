package Codelearn.OOP;

import java.util.Scanner;

class Students {
    private String name;
    private int age;
    public Students(){
        name = "Codelearn";
        age = 24;
    }
    public void display(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

public class Ex_5 {
    public static void main(String[] args) {
        Students st = new Students();
        st.display();
    }
}
