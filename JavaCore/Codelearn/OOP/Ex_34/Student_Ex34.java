package Codelearn.OOP.Ex_34;

public class Student_Ex34 extends Person_Ex34{
    private double gpa;
    public Student_Ex34(String name, int age, String address, double gpa){
        super(name, age,address);
        this.gpa = gpa;
    }
    public double getGpa(){
        return gpa;
    }
    public void setGpa(double gpa){
        this.gpa = gpa;
    }
    public void display(){
        super.display();
        System.out.println("GPA: " + gpa);
    }
}
