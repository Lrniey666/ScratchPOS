package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleOrderDAO {

    private Connection conn;

    public List<SaleOrder> getAllSaleOrders() {
        conn = DBConnection.getConnection();
        List<SaleOrder> saleOrderList = new ArrayList<SaleOrder>();
        if (conn == null) {
            return saleOrderList;
        }
        String query = "select * from sale_order order by order_date desc, order_num desc";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rset = ps.executeQuery();

            while (rset.next()) {
                SaleOrder saleOrder = new SaleOrder();
                saleOrder.setOrderNum(rset.getString("order_num"));
                saleOrder.setOrderDate(rset.getTimestamp("order_date"));
                saleOrder.setTotalPrice(rset.getDouble("total_price"));
                saleOrder.setCustomerName(rset.getString("customer_name"));
                saleOrder.setCustomerAddress(rset.getString("customer_address"));
                saleOrder.setCustomerPhone(rset.getString("customer_phone"));
                saleOrderList.add(saleOrder);
            }
        } catch (SQLException ex) {
            System.out.println("getAllSaleOrders exception: " + ex.toString());
        }

        return saleOrderList;
    }

    public int countOrders() {
        return scalarInt("select count(*) from sale_order");
    }

    public int sumRevenue() {
        return scalarInt("select coalesce(sum(total_price), 0) from sale_order");
    }

    public int countTickets() {
        return scalarInt("select coalesce(sum(quantity), 0) from order_detail");
    }

    private int scalarInt(String query) {
        conn = DBConnection.getConnection();
        if (conn == null) {
            return 0;
        }
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rset = ps.executeQuery();
            if (rset.next()) {
                return rset.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("scalarInt exception: " + ex.toString());
        }
        return 0;
    }
}
