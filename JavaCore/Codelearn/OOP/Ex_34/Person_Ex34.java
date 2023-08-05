package Codelearn.OOP.Ex_34;

public class Person_Ex34 {
    private String name;
    private int age;
    private String address;
    public Person_Ex34(String name, int age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void display(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
    }
}
