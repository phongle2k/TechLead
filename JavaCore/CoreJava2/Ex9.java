package CoreJava2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
Đếm số lần xuất hiện của các từ trong một văn bản Hãy viết một chương trình Java để đọc một đoạn văn bản từ người dùng
và đếm số lần xuất hiện của mỗi từ trong văn bản. Sử dụng HashMap để lưu trữ từ làm key và số lần xuất hiện làm value.
Sau đó, hiển thị danh sách các từ và số lần xuất hiện tương ứng của chúng.
 */
public class Ex9 {
    public static void main(String[] args) {
        HashMap<String, Integer> documents = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap mot doan van: ");
        String str = sc.nextLine();

        // Tach cac tu trong doan van va dem so lan xuat hien
        String[] words = str.split("\\s+"); // tach tung tu trong doan van ban
        for(String word : words){
            // Chuyen chu hoa thanh chu thuong
            String lower = word.toLowerCase();
            // Kiem tra xem tu da ton tai trong HashMap chua
            if(documents.containsKey(lower)){
                int count = documents.get(lower);
                documents.put(lower,count + 1); // Neu da xuat hien tang count + 1
            }
            else {
                // Neu chua xuat hien, them tu vao HashMap voi so lan xuat hien ban dau la 1
                documents.put(lower, 1);
            }
        }
        // Hien thi danh sach cac tu va so lan xuat hien tuong ung
        System.out.println("\nDanh sach cac tu va so lan xuat hien tuong ung");
        for(Map.Entry<String, Integer> entry : documents.entrySet()){
            String word = entry.getKey();
            int count = entry.getValue();
            System.out.println(word + " " + count);
        }
    }
}
