package CoreJava2;

import java.util.HashMap;
import java.util.Scanner;

/*
HashMap Hãy tạo một chương trình Java để thực hiện các thao tác cơ bản trên một HashMap.
Hãy tạo một HashMap để lưu trữ cặp key-value với key là tên của một người và value là tuổi của người đó.
Thực hiện các thao tác sau:
- Thêm các cặp key-value vào HashMap.
- Hiển thị danh sách tên của tất cả các người trong HashMap.
- Tìm tuổi của một người dựa trên tên của họ.
- Xóa một người cụ thể khỏi HashMap.
- Kiểm tra xem một người có tồn tại trong HashMap hay không.
 */
public class Ex3 {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong danh sach: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap danh sach: ");
        for(int i = 0; i < n; i++){
            String name = sc.nextLine();
            int age = sc.nextInt();
            sc.nextLine();
            hashMap.put(name, age);
        }
        System.out.print("Hien thi danh sach: " + hashMap);

        // Find age dua tren name
        System.out.print("\nNhap ten can tim: ");
        String findAge = sc.nextLine();
        if(hashMap.containsKey(findAge)){
            System.out.println("Ten " + findAge + " co tuoi la: " + hashMap.get(findAge));
        }else {
            System.out.println("Ten " + findAge + " khong co trong HashMap");
        }
        // Kiểm tra xem một người có tồn tại trong HashMap hay không.
        System.out.print("\nNhap ten can kiem tra: ");
        String nameExist = sc.nextLine();
        boolean checkNameExist = hashMap.containsKey(nameExist);
        if(checkNameExist){
            System.out.println("Ten " + nameExist + " co trong HashMap");
        }else {
            System.out.println("Ten " + nameExist + " khong co trong HasMap");
        }
    }
}
