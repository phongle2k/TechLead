package CoreJava2.Ex8;

import java.util.HashMap;
import java.util.Scanner;

/*
Quản lý danh sách sản phẩm: Hãy tạo một ứng dụng quản lý danh sách sản phẩm sử dụng HashMap.
Mỗi sản phẩm có một mã sản phẩm làm key và thông tin sản phẩm làm value (ví dụ: tên sản phẩm, giá, số lượng).
Hãy cung cấp các chức năng sau:
Thêm sản phẩm mới vào danh sách.
Hiển thị danh sách tất cả các sản phẩm.
Tìm thông tin sản phẩm dựa trên mã sản phẩm.
Xóa sản phẩm khỏi danh sách dựa trên mã sản phẩm.
Cập nhật thông tin sản phẩm.
 */
public class Ex8 {
    public static void main(String[] args) {
        HashMap<String, Product> productList = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("\n===Quan ly danh sach san pham===");
            System.out.println("1. Them san pham moi");
            System.out.println("2. Hien thi danh sach san pham");
            System.out.println("3. Tim thong tin san pham");
            System.out.println("4. Xoa san pham khoi danh sach");
            System.out.println("5. Cap nhat thong tin san pham");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            int choise = sc.nextInt();
            sc.nextLine();
            switch (choise){
                case 1:
                    addProduct(productList, sc);
                    break;
                case 2:
                    showProduct(productList);
                    break;
                case 3:
                    findProduct(productList, sc);
                    break;
                case 4:
                    removeProduct(productList, sc);
                    break;
                case 5:
                    updateProduct(productList, sc);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai");
            }
        }

    }

    private static void addProduct(HashMap<String, Product> productList, Scanner sc) {
        System.out.println("Nhap ma sp: ");
        String productCode = sc.nextLine();
        if(productList.containsKey(productCode)){
            System.out.println("Ma " + productCode + " da ton tai. Vui long nhap ma moi");
            return;
        }
        System.out.print("Nhap ten san pham: ");
        String productName = sc.nextLine();

        System.out.println("Nhap gia san pham: ");
        double productPrice = sc.nextDouble();

        System.out.println("Nhap so luong sp: ");
        int productQuantity = sc.nextInt();
        productList.put(productCode, new Product(productCode, productName, productPrice, productQuantity));
        System.out.println("Them san pham thanh cong");
    }
    private static void showProduct(HashMap<String, Product> productList) {
        System.out.println("Danh sach san pham");
        for(Product products : productList.values()){
            System.out.println(products);
        }
    }

    private static void findProduct(HashMap<String, Product> productList, Scanner sc) {
        System.out.print("Nhap ma san pham can tim: ");
        String productCode = sc.nextLine();
        Product findProduct = productList.get(productCode);
        if(findProduct != null){
            System.out.println("Thong tin san pham");
            System.out.println(findProduct);
        }else {
            System.out.println("Khong tim thay san pham voi ma san pham vua nhap");
        }
    }
    private static void removeProduct(HashMap<String, Product> productList, Scanner sc) {
        System.out.println("Nhap ma san pham can xoa: ");
        String productCode = sc.nextLine();
        Product removeProduct = productList.remove(productCode);
        if(removeProduct != null){
            System.out.println("Da xoa san pham");
            System.out.println(removeProduct);
        }else {
            System.out.println("Khong tim thay san pham voi ma san pham vua nhap");
        }
    }
    private static void updateProduct(HashMap<String, Product> productList, Scanner sc) {
        System.out.println("Nhap ma san pham muon update: ");
        String productCode = sc.nextLine();
        Product updateProduct = productList.get(productCode);
        if(updateProduct != null){
            System.out.println("Nhap ten san pham moi: ");
            String productName = sc.nextLine();
            updateProduct.setProductName(productName);

            System.out.println("Nhap gia san pham moi: ");
            double productPrice = sc.nextDouble();
            updateProduct.setProductPrice(productPrice);

            System.out.println("Nhap so luong san pham moi: ");
            int productQuantity = sc.nextInt();
            updateProduct.setProductQuantity(productQuantity);

            System.out.println("Cap nhat san pham thanh cong");
        }else {
            System.out.println("Khong tim thay san pham voi ma san pham vua nhap");
        }
    }
}
