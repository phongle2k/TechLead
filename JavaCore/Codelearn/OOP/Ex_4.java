package Codelearn.OOP;

class Demo {
    String name;
    public void display(){
        System.out.println("Name: " + name);
    }
}

public class Ex_4 {
    public static void main(String[] args) {
        Demo d = new Demo();
        d.name = "Codelearn";
        d.display();
    }
}
