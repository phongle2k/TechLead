package CoreJava2.Ex8;

public class Product {
    private String productCode;
    private String productName;
    private double productPrice;
    private int productQuantity;
    public Product(String productCode, String productName, double productPrice, int productQuantity){
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
    public String getProductCode(){
        return productCode;
    }
    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public double getProductPrice(){
        return productPrice;
    }
    public void setProductPrice(double productPrice){
        this.productPrice = productPrice;
    }
    public int getProductQuantity(){
        return productQuantity;
    }
    public void setProductQuantity(int productQuantity){
        this.productQuantity = productQuantity;
    }
    public String toString(){
        return  "Ma san pham: " + productCode +
                "\nTen san pham: " + productName +
                "\nGia san pham: " + productPrice +
                "\nSo luong san pham: " + productQuantity;
    }
}
