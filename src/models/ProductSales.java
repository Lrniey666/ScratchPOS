package models;

public class ProductSales {

    private String productId;
    private String productName;
    private int quantity;
    private int revenue;

    public ProductSales() {
    }

    public ProductSales(String productId, String productName, int quantity, int revenue) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.revenue = revenue;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}
