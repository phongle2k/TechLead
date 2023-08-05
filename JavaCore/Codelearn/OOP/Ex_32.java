package Codelearn.OOP;

class Person_Ex32 {
    private String name;
    private String gender;
    public Person_Ex32(String name, String gender){
        this.name = name;
        this.gender = gender;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void display(){
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
    }
}
class Student_Ex32 extends Person_Ex32 {
    private int salary;
    public Student_Ex32(String name, String gender, int salary){
        super(name, gender);
        this.salary = salary;
    }
    public int getSalary(){
        return salary;
    }
    public void setSalary(int salary){
        this.salary = salary;
    }
    public void display(){
        super.display();
        System.out.println("Salary: " + salary);
    }
}

public class Ex_32 {
    public static void main(String[] args) {
        Student_Ex32 st = new Student_Ex32("Phong", "Nam", 10000000);
        st.display();
    }
}
