package Codelearn.OOP;

// Khai bao va su dung cac doi tuong mang trong java
class Student_Ex11 {
    String name;
    int age;
    // Constructor co tham so
//    public Student_Ex11(String name, int age){
//        this.name = name;
//        this.age = age;
//    }

    // Constructor khong co tham so
    public Student_Ex11(){

    }
    public void show(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

public class Ex_11 {
    public static void main(String[] args) {
        Student_Ex11[] students = new Student_Ex11[3];
        // Constructor co tham so
//        students[0] = new Student_Ex11("Phong", 24);
//        students[1] = new Student_Ex11("Hien", 26);
//        students[2] = new Student_Ex11("Linh", 25);

        // Constructor khong co tham so
        for(int i = 0; i < 3; i++){
            students[i] = new Student_Ex11();
        }
        students[0].name = "Phong";
        students[0].age = 24;
        students[1].name = "Linh";
        students[1].age = 25;
        students[2].name = "Hien";
        students[2].age = 26;

        // Show
        for(int i = 0; i < 3; i++){
            students[i].show();
        }
    }
}
