package CoreJava2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
Kiểm tra phân phối điểm số Hãy viết một chương trình Java để kiểm tra phân phối điểm số của một lớp học.
Người dùng sẽ nhập danh sách điểm số của sinh viên và chương trình sẽ tính số lượng sinh viên đạt điểm cao (>= 8.0),
số lượng sinh viên đạt điểm trung bình (>= 5.0 và < 8.0) và số lượng sinh viên trượt (dưới 5.0).
Sử dụng HashMap để lưu trữ điểm số làm key và số lượng sinh viên đạt điểm tương ứng làm value.
 */
public class Ex10 {
    public static void main(String[] args) {
        HashMap<Double, Integer> students = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap danh sach sinh vien: ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap diem cua tung sinh vien");
        for(int i = 0; i < n; i++){
            System.out.print("\nDNhap diem so cua sinh vien thu " + (i+1) + ": ");
            double score = sc.nextDouble();
            // chuyen doi diem so ve dang lam tron den 1 chu so thap phan
            score = Math.round(score * 10.0) / 10.0;
            // Tang so luong sinh vien dat diem tuong ung len 1
            students.put(score, students.getOrDefault(score,0) + 1);
        }
        int hightScore = 0;
        int avgScore = 0;
        int failCount = 0;
        for(Map.Entry<Double, Integer> entry : students.entrySet()){
            double score = entry.getKey();
            double count = entry.getValue();
            if(score >= 8.0){
                hightScore += count;
            } else if(score >= 5.0 && score <= 8.0){
                avgScore += count;
            } else {
                failCount += count;
            }
        }
        System.out.print("\nPhan phoi diem so");
        System.out.println("\nSinh vien co diem trung binh cao nhat: " + hightScore);
        System.out.println("\nSinh vien co diem so dat trung binh: " + avgScore);
        System.out.println("\nSinh vien co diem so duoi trung binh: " + failCount);
    }
}
