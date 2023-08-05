package Codelearn.MethodJava;
class A_Ex54 {
    public A_Ex54() {
        System.out.println("A");
    }
}
class B_Ex54 extends A_Ex54{
    public B_Ex54(){
        super();
        System.out.println("B");
    }
}

public class Ex_54 {
    public static void test(B_Ex54 b){

    }

    public static void main(String[] args) {
        B_Ex54 b = new B_Ex54();
        test(b);
    }
}
