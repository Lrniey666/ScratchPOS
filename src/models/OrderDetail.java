package models;

public class OrderDetail {

    private int id;
    private String order_num;
    private String product_id;
    private int quantity;
    private int product_price;
    private int total_price;
    private String product_name;
    private String product_photo;

   public OrderDetail(String product_id, String product_name, int product_price, int quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.quantity = quantity;
        this.total_price= product_price * quantity;
    }

    
    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotalPrice();
    }

    public String getOrderNum() {
        return order_num;
    }

    public void setOrderNum(String orderNum) {
        this.order_num = orderNum;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String productId) {
        this.product_id = productId;
    }

    public int getProductPrice() {
        return product_price;
    }

    public void setProductPrice(int productPrice) {
        this.product_price = productPrice;
        updateTotalPrice();
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String productName) {
        this.product_name = productName;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_photo() {
        return product_photo;
    }

    public void setProduct_photo(String product_photo) {
        this.product_photo = product_photo;
    }

public int getTotalPrice() {
        return total_price;
    }
    
    private void updateTotalPrice() {
        this.total_price = this.product_price * this.quantity;
    }

    
    
}
