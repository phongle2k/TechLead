package Codelearn.OOP;
class Animal {
    public void sound() {
        System.out.println("some sound");
    }
}
class Dog extends Animal {
    public void sound(){
        System.out.println("gau gau");
    }
    public void play(){
        System.out.println("choi choi");
    }
}

public class Ex_39 {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.sound(); // Up-casting
        ((Dog)animal).play(); // Down-casting
    }
}
