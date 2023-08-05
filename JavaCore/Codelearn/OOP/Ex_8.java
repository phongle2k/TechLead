package Codelearn.OOP;


public class Ex_8 {
    public static void main(String [] args){
        Student_Ex8 s1 = new Student_Ex8("Phong", 24, "Nam", 2.65);
        s1.display();
    }
}
class Student_Ex8 {
    private String name;
    private int age;
    private String gender;
    private double gpa;

    public Student_Ex8(String name, int age, String gender, double gpa){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.gpa = gpa;
    }
    public void display(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("GPA: " + gpa);
    }
}
