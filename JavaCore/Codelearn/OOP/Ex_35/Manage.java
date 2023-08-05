package Codelearn.OOP.Ex_35;

public class Manage extends Employee{
    private int bonus;
    public Manage(String name, int salary, int bonus){
        super(name,salary);
        this.bonus = bonus;
    }
    public int getSalary(){
        return bonus + super.getSalary();
    }
    public int getBonus(){
        return bonus;
    }
    public void setBonus(int bonus){
        this.bonus = bonus;
    }
    public void display(){
        super.display();
        System.out.println("Name: " + getName());
        System.out.println("Bonus: " + getSalary());
    }
}
