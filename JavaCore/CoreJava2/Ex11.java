package CoreJava2;

import java.util.HashMap;
import java.util.Scanner;

/*
Hãy tạo một từ điển đơn giản sử dụng HashMap. Cho phép người dùng thêm từ và định nghĩa của từ vào từ điển.
Sau đó, cho phép người dùng tra cứu từ điển bằng cách nhập từ cần tìm kiếm và hiển thị định nghĩa của từ đó.
 */
public class Ex11 {
    public static void main(String[] args) {
        HashMap<String, String> dictionary = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.print("\n=== Tu dien ===");
            System.out.println("1. Them tu vao tu dien");
            System.out.println("2. Tra cuu tu trong tu dien");
            System.out.println("0. Thoat");
            System.out.println("Nhap lua chon cua ban: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    addWordDictionary(dictionary, sc);
                    break;
                case 2:
                    searchWordDictionary(dictionary, sc);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai");
            }
        }
    }

    private static void addWordDictionary(HashMap<String, String> dictionary, Scanner sc) {
        System.out.println("Nhap tu can them: ");
        String word = sc.nextLine();
        // Check word da co trong hashMap
        if(dictionary.containsKey(word)){
            System.out.println("Tu da ton tai trong tu dien, vui long nhap tu moi");
        } else {
            System.out.println("Nhap dinh nghia tu: ");
            String dinhNghia = sc.nextLine();
            dictionary.put(word, dinhNghia);
            System.out.println("Them thanh cong");
        }
    }
    private static void searchWordDictionary(HashMap<String, String> dictionary, Scanner sc) {
        System.out.println("Nhap tu can tra cuu: ");
        String findName = sc.nextLine();
        String word = dictionary.get(findName);
        if(dictionary.containsKey(findName)){
            System.out.println("Dinh nghia cua tu: " + findName + " la: " + word);
        } else {
            System.out.println("Tu " + findName + " khong co trong tu dien");
        }
    }


}
