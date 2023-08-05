package Codelearn.MethodJava;

interface IShape {
    double getArea();
    double getPerimeter();
}

class Rectangle implements IShape {
    private double length;
    private double width;
    public Rectangle(double length, double width){
        super();
        this.length = length;
        this.width = width;
    }
    @Override
    public double getArea(){
        return length * width;
    }
    public double getPerimeter(){
        return (length + width) / 2;
    }
}
class Circle implements IShape {
    private double radius;
    public Circle(double radius){
        this.radius = radius;
    }
    public double getArea(){
        return 3.14 * radius * radius;
    }
    public double getPerimeter(){
        return 2 * 3.14 * radius;
    }
}

public class Ex_45 {
    public static void main(String[] args) {
        IShape[] iShapes = new IShape[3];
        iShapes[0] = new Rectangle(3,4);
        iShapes[1] = new Circle(5.5);
        iShapes[2] = new Rectangle(2.3, 4.5);
        for(int i = 0; i < 3; i++){
            System.out.println(iShapes[i].getPerimeter());
            System.out.println(iShapes[i].getArea());
        }
    }
}
