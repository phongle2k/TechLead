package CoreJava2;

import java.util.HashSet;
import java.util.Scanner;
/*
HashSet Hãy tạo một chương trình Java để thực hiện các thao tác cơ bản trên một HashSet.
Hãy tạo một HashSet chứa tên của các quốc gia và thực hiện các thao tác sau:
- Thêm các tên quốc gia vào HashSet.
- Hiển thị danh sách các quốc gia đã thêm vào.
- Kiểm tra xem một quốc gia có tồn tại trong HashSet hay không.
- Xóa một quốc gia cụ thể khỏi HashSet.
- Tính số lượng các quốc gia có trong HashSet.
 */
public class Ex2 {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap danh sach cac quoc gia: ");
        for(int i = 1; i <= n; i++){
            hashSet.add(sc.nextLine());
        }
        System.out.print("Cac quoc gia vua nhap: " + hashSet);

        // Check Exists
        System.out.print("\nNhap ten quoc gia can kiem tra: ");
        String countryToCheck = sc.nextLine();
        boolean exists = hashSet.contains(countryToCheck);

        if (exists) {
            System.out.println("Country " + countryToCheck + " exists in the HashSet.");
        } else {
            System.out.println("Country " + countryToCheck + " does not exist in the HashSet.");
        }

        // Remove country
        System.out.println("\nNhap ten quoc gia can xoa: ");
        String countryToRemove = sc.nextLine();
        boolean existsRemove = hashSet.remove(countryToRemove);
        if(existsRemove){
            System.out.println("Xoa " + countryToRemove + " ra khoi HashSet");
        }else {
            System.out.println("Khong tim thay " + countryToRemove + " trong HashSet");
        }
        System.out.print("\nDanh sach quoc gia sau khi xoa: " + hashSet);

        // Tính số lượng các quốc gia có trong HashSet.
        System.out.println("\nSo luong quoc gia co trong HashSet: " + hashSet.size());
    }
}
