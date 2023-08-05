package Codelearn.OOP;

class Account {
    private int id;
    private String name;
    private int balance = 0;

    public Account(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Account(int id, String name, int balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getBalance(){
        return balance;
    }
    public void deposit(int amount){
        balance += amount;
    }
    public void withdraw(int amount){
        if(balance >= amount){
            balance -= amount;
        } else {
            System.out.println("That amount exceeds your current balance");
        }
    }
    public void display(){
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }
}

public class Ex_28 {
    public static void main(String[] args) {
        Account a = new Account(1000, "Phong", 100000);
//        a.deposit(5000);
        a.display();
        a.withdraw(120000);
        System.out.println("Balance: " + a.getBalance());



    }
}
