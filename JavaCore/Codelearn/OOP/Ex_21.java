package Codelearn.OOP;

class Person {
//    Id: 1001
//    Name: Quynh
//    Age: 24
//    Address: Ha Noi
    private int id;
    private String name;
    private int age;
    private String address;

    // Cach 2: Sd phuong thuc khoi tao thay cho setter
    public Person(int id, String name, int age, String address){
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    // Muon truy cap vao private ngoai class ta dung ham setter va getter
    public int getId(){
        return id;
    }
//    public void setId(int id){
//        this.id = id;
//    }
    public String getName(){
        return name;
    }
//    public void setName(String name){
//        this.name = name;
//    }
    public int getAge(){
        return age;
    }
//    public void setAge(int age){
//        this.age = age;
//    }
    public String getAddress(){
        return address;
    }
//    public void setAddress(String address){
//        this.address = address;
//    }
}
public class Ex_21 {
    public static void main(String[] args) {
        Person p = new Person(1001, "Quynh", 24, "Ha Noi");
//        p.setId(1001);
//        p.setName("Quynh");
//        p.setAge(24);
//        p.setAddress("Ha Noi");
        System.out.println("Id: " + p.getId());
        System.out.println("Name: " + p.getName());
        System.out.println("Age: " + p.getAge());
        System.out.println("Address: " + p.getAddress());
    }
}
