package Codelearn.OOP;

class Student_Ex6{
    String name;
    int age;
    public Student_Ex6(String n, int a){
        name = n;
        age = a;
    }
    public void show(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

public class Ex_6 {
    public static void main(String[] args) {
        Student_Ex6 s1 = new Student_Ex6("Long", 24);
        Student_Ex6 s2 = new Student_Ex6("Phong", 24);
        s1.show();
        s2.show();
    }
}
