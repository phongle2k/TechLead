package Codelearn.MethodJava.Ex_46;

public abstract class Employee implements IEmployee {
    protected String name;
    protected int paymentPerHour;

    public Employee(String name, int paymentPerHour) {
        this.name = name;
        this.paymentPerHour = paymentPerHour;
    }

    @Override
    public String getName() {
        return name;
    }
}
