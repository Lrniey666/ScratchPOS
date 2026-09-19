package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {

    private Connection conn;

    public List<OrderDetail> getAllOrderDetails() {
        conn = DBConnection.getConnection();
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        if (conn == null) {
            return orderDetailList;
        }
        String query = "select * from order_detail";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rset = ps.executeQuery();

            while (rset.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rset.getInt("id"));
                orderDetail.setOrderNum(rset.getString("order_num"));
                orderDetail.setProductId(rset.getString("product_id"));
                orderDetail.setQuantity(rset.getInt("quantity"));
                orderDetail.setProductPrice(rset.getInt("product_price"));
                orderDetail.setProductName(rset.getString("product_name"));
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException ex) {
            System.out.println("getAllOrderDetails exception: " + ex.toString());
        }

        return orderDetailList;
    }

    public List<ProductSales> getProductSales() {
        conn = DBConnection.getConnection();
        List<ProductSales> rows = new ArrayList<ProductSales>();
        if (conn == null) {
            return rows;
        }
        String query = "select product_id, product_name, "
                + "sum(quantity) as qty, sum(quantity * product_price) as revenue "
                + "from order_detail "
                + "group by product_id, product_name "
                + "order by revenue desc, qty desc";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rset = ps.executeQuery();
            while (rset.next()) {
                rows.add(new ProductSales(
                        rset.getString("product_id"),
                        rset.getString("product_name"),
                        rset.getInt("qty"),
                        rset.getInt("revenue")));
            }
        } catch (SQLException ex) {
            System.out.println("getProductSales exception: " + ex.toString());
        }

        return rows;
    }
}
