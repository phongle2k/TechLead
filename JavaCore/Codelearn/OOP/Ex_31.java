package Codelearn.OOP;

class Person_Ex31 {
    private String name;
    private int age;
    public Person_Ex31(String name, int age){
        this.name = name;
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
}

class Student_Ex31 extends Person_Ex31 {
    private double gpa;
    public Student_Ex31(String name, int age, double gpa) {
        super(name, age);
        this.gpa = gpa;
    }
    public Double getGpa(){
        return gpa;
    }
    public void setGpa(double gpa){
        this.gpa = gpa;
    }

}

public class Ex_31 {
    public static void main(String[] args) {
        Student_Ex31 st = new Student_Ex31("Phong", 24, 3.65);
        System.out.println("Name: " + st.getName());
        System.out.println("Age: " + st.getAge());
        System.out.println("GPA: " + st.getGpa());
    }
}
