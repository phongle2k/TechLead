package Codelearn.OOP;

abstract class Person_Ex44 {
    private String name;
    private String address;
    public Person_Ex44(String name, String address){
        super();
        this.name = name;
        this.address = address;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public abstract void display();
}

class Employee_Ex44 extends Person_Ex44 {
    private int salary;
    public Employee_Ex44(String name, String address, int salary){
        super(name, address);
        this.salary = salary;
    }
    @Override
    public void display(){
        System.out.println("Name: " + super.getName());
        System.out.println("Address: " + super.getAddress());
        System.out.println("Salary: " + salary);
    }
}
class Customer_Ex44 extends Person_Ex44 {
    private int balance;
    public Customer_Ex44(String name, String address, int balance){
        super(name, address);
        this.balance = balance;
    }
    @Override
    public void display(){
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Balance: " + balance);
    }
}
public class Ex_44 {
    public static void main(String[] args) {
        Person_Ex44 person1 = new Employee_Ex44("Phong", "Ha Nam", 1000);
        Person_Ex44 person2 = new Customer_Ex44("Phi", "Ha Nam", 3000);
        person1.display();
        person2.display();
    }
}
