package Codelearn.OOP;

public class Ex_36 {
    private String name;
    private String address;
    private double gpa;
    public Ex_36(String name, String address, double gpa){
        this.name = name;
        this.address = address;
        this.gpa = gpa;
    }
    @Override
    public String toString(){
        return "Name: " + name + " Address: " + address + " GPA: " + gpa;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public static void main(String[] args) {
        Ex_36 e = new Ex_36("Phong", "Ha Nam", 2.65);
        e.setName("Phi");
        System.out.println(e);
    }
}
