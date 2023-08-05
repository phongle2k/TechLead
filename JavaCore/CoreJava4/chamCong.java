package CoreJava4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class chamCong {
    public static void main(String[] args) throws FileNotFoundException {
        String url = "F:\\TechLead\\untitled\\src\\CoreJava4\\BangCong.xlsx";
        try {
            FileInputStream fileInputStream = new FileInputStream(url);
            Scanner sc = new Scanner(System.in);
            

            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        
    }
}
