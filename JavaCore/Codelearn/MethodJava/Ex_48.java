package Codelearn.MethodJava;

class Point_Ex48 {
    private int x, y;
    public Point_Ex48(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return y;
    }
}
class Triangle {
    private Point_Ex48 vertica1;
    private Point_Ex48 vertica2;
    private Point_Ex48 vertica3;
    public Triangle(Point_Ex48 vertica1, Point_Ex48 vertica2, Point_Ex48 vertica3){
        this.vertica1 = vertica1;
        this.vertica2 = vertica2;
        this.vertica3 = vertica3;
    }
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3){
        this.vertica1 = new Point_Ex48(x1, y1);
        this.vertica2 = new Point_Ex48(x2, y2);
        this.vertica3 = new Point_Ex48(x3, y3);
    }
    public double  getPerimeter(){
        double d1 = Math.sqrt(Math.pow(vertica1.getX() - vertica2.getX(), 2)
                + Math.pow(vertica1.getY() - vertica2.getY(), 2));
        double d2 = Math.sqrt(Math.pow(vertica1.getX() - vertica3.getX(), 2)
                + Math.pow(vertica1.getY() - vertica3.getY(), 2));
        double d3 = Math.sqrt(Math.pow(vertica2.getX() - vertica3.getX(), 2)
                + Math.pow(vertica2.getY() - vertica3.getY(), 2));
        return d1 + d2 + d3;
    }
}

public class Ex_48 {
    public static void main(String[] args) {
        Point_Ex48 vertica1 = new Point_Ex48(1,3);
        Point_Ex48 vertica2 = new Point_Ex48(2,3);
        Point_Ex48 vertica3 = new Point_Ex48(4,2);
        Triangle triangle1 = new Triangle(vertica1, vertica2, vertica3);
        Triangle triangle2 = new Triangle(4,5,2,6,3,7);
        System.out.println(triangle1.getPerimeter());
        System.out.println(triangle2.getPerimeter());
    }
}
