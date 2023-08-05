    package Codelearn.OOP.Ex_34;

    public class Teacher_Ex34 extends Person_Ex34{
        private int salary;
        public Teacher_Ex34(String name, int age, String address, int salary){
            super(name, age, address);
            this.salary = salary;
        }
        public int getSalary(){
            return salary;
        }
        public void setSalary(int salary){
            this.salary = salary;
        }
        public void display(){
            super.display();
            System.out.println("Salary: " + salary);
        }
    }
