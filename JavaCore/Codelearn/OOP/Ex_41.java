package Codelearn.OOP;

import org.w3c.dom.ls.LSOutput;

class Animal_Ex41 {
    public void sound(){
        System.out.println("some sound");
    }
}
class Dog_Ex41 extends Animal_Ex41{
    public void sound(){
        System.out.println("gau gau");
    }
}
class Meo_Ex41 extends Animal_Ex41 {
    public void sound(){
        System.out.println("meow meow");
    }
}
class Dunk_Ex41 extends Animal_Ex41 {
    public void sound(){
        System.out.println("quac quac");
    }
}

public class Ex_41 {
    public static void main(String[] args) {
        Animal_Ex41[] animals = new Animal_Ex41[4];
        animals[0] = new Animal_Ex41();
        animals[1] = new Dog_Ex41();
        animals[2] = new Meo_Ex41();
        animals[3] = new Dunk_Ex41();
        for(int i = 0; i < 4; i++){
            animals[i].sound();
        }
    }
}
