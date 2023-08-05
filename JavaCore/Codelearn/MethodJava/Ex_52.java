package Codelearn.MethodJava;

class A {
    public int x = 10;
    public void display(){
        System.out.println(this.x);
    }
}
class B extends A {
    public int x = 20;
//    public void display(){
//        System.out.println(this.x); // Lỗi biên dịch
//    }
}
public class Ex_52 {
    public static void main(String[] args) {
        A b = new B();
        b.display(); // 10

    }
}
