package CoreJava2;

import java.util.*;

/*
Bài tập 12: Chúng ta có 1 list/set các các sản phẩm bao gồm các thông tin tên, mã, giá bán, ngày sản xuất.
Hãy viết 1 đoạn chương trình để sắp xếp các sản phẩm theo các tiêu chí sau:
- Theo tên
- Theo giá bán
- Theo ngày sản xuất
- Theo giá bán và ngày sản xuất

 */
public class Ex12 implements Comparable<Ex12> {
    private String name;
    private String code;
    private double price;
    private Date manufactureDate;

    public Ex12() {
    }

    public Ex12(String name, String code, double price, Date manufactureDate) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.manufactureDate = manufactureDate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", manufactureDate=" + manufactureDate +
                '}';
    }

    public static void main(String[] args) {
        List<Ex12> products = new ArrayList<>();
        products.add(new Ex12("Laptop", "P001", 1200.0, new Date(2023, 7, 30)));
        products.add(new Ex12("Smartphone", "P002", 800.0, new Date(2023, 7, 25)));
        products.add(new Ex12("Tablet", "P003", 500.0, new Date(2023, 7, 28)));
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("====Thuc hien sap xep====");
            System.out.println("1. Sap xep theo ten");
            System.out.println("2. Sap xep theo gia ban");
            System.out.println("3. Theo ngay san xuat");
            System.out.println("4. Theo gia ban va ngay san xuat");
            System.out.println("0. Thoat");
            System.out.println("Nhap lua chon cua ban: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    Collections.sort(products, Comparator.comparing(Ex12::getName));
                    break;
                case 2:
                    Collections.sort(products, Comparator.comparing(Ex12::getPrice));
                    break;
                case 3:
                    Collections.sort(products, Comparator.comparing(Ex12::getManufactureDate));
                    break;
                case 4:
                    Collections.sort(products, Comparator.comparing(Ex12::getPrice).thenComparing(Ex12::getManufactureDate));
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai");
            }
            if (running) {
                // In danh sách sản phẩm đã sắp xếp theo tiêu chí được chọn
                System.out.println("\nDanh sách sản phẩm đã sắp xếp:");
                for (Ex12 product : products) {
                    System.out.println(product);
                }
            }
        }
    }

    @Override
    public int compareTo(Ex12 o) {
        return this.name.compareTo(o.name);
    }
}
