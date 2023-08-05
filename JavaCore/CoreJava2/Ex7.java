package CoreJava2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*
Tính toán điểm trung bình của sinh viên Hãy viết một chương trình Java để tính điểm trung bình của các sinh viên
trong lớp học. Sử dụng HashMap để lưu trữ tên của sinh viên làm key và danh sách điểm số làm value.
Sau đó, tính điểm trung bình cho mỗi sinh viên và hiển thị kết quả.
 */
public class Ex7 {
    public static void main(String[] args) {
        HashMap<String, List<Double>> students = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong sinh vien: ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap danh sach sinh vien: ");
        for(int i = 0;i < n; i++){
            System.out.print("\nSinh vien thu " + (i + 1) + ": ");
            String name = sc.nextLine();
            System.out.print("Nhap so luong diem so: ");
            int numScores = sc.nextInt();
            sc.nextLine();
            List<Double> scores = new ArrayList<>();
            for(int j = 0; j < numScores; j++){
                System.out.print("Nhap diem so "+(j+1) + ": ");
                double score = sc.nextDouble();
                scores.add(score);
            }
            sc.nextLine();
            students.put(name,scores);
        }
        System.out.println("\nDanh sach sinh vien vua nhap: " + students);
        // Tinh diem trung binh cho moi sinh vien
        for(String tenSinhVien : students.keySet()){
            List<Double> diem = students.get(tenSinhVien);
            double diemTb = 0;
            for(double score : diem){
                diemTb += score;
            }
            diemTb /= diem.size();
            System.out.println("diem TB cua " + tenSinhVien + " la " + Math.round(diemTb * 100.0) / 100.0);
            System.out.println();
        }
    }
}
