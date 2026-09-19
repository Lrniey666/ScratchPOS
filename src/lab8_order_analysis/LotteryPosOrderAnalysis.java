package lab8_order_analysis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.DBConnection;
import models.OrderDetailDAO;
import models.ProductSales;
import models.SaleOrder;
import models.SaleOrderDAO;

public class LotteryPosOrderAnalysis extends Application {

    private final SaleOrderDAO saleOrderDao = new SaleOrderDAO();
    private final OrderDetailDAO orderDetailDao = new OrderDetailDAO();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Label orderCountLabel;
    private Label revenueLabel;
    private Label ticketCountLabel;
    private TableView<SaleOrder> orderTable;
    private TableView<ProductSales> productTable;
    private TextArea display;

    public HBox get_root_pane() {
        orderCountLabel = makeStatLabel("訂單筆數", "0");
        revenueLabel = makeStatLabel("營業額", "0");
        ticketCountLabel = makeStatLabel("銷售張數", "0");

        HBox stats = new HBox(16);
        stats.setAlignment(Pos.CENTER);
        stats.setPadding(new Insets(8, 8, 8, 8));
        stats.getChildren().addAll(orderCountLabel, revenueLabel, ticketCountLabel);

        initializeOrderTable();
        initializeProductTable();

        VBox left = new VBox(8);
        Label leftTitle = makeSectionTitle("訂單列表");
        left.getChildren().addAll(leftTitle, orderTable);
        VBox.setVgrow(orderTable, Priority.ALWAYS);
        HBox.setHgrow(left, Priority.ALWAYS);

        VBox right = new VBox(8);
        Label rightTitle = makeSectionTitle("商品銷售排行");
        right.getChildren().addAll(rightTitle, productTable);
        VBox.setVgrow(productTable, Priority.ALWAYS);
        HBox.setHgrow(right, Priority.ALWAYS);

        HBox tables = new HBox(12);
        tables.getChildren().addAll(left, right);

        Button btnRefresh = new Button("重新整理");
        btnRefresh.setPrefSize(180, 72);
        btnRefresh.setStyle("-fx-font-size: 24px;");
        btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reload();
            }
        });

        display = new TextArea();
        display.setEditable(false);
        display.setPrefHeight(140);
        display.setStyle("-fx-font-size: 20px;");

        HBox actions = new HBox(12);
        actions.setAlignment(Pos.CENTER_LEFT);
        actions.getChildren().addAll(btnRefresh, display);
        HBox.setHgrow(display, Priority.ALWAYS);

        VBox column = new VBox(10);
        column.setPadding(new Insets(10, 10, 10, 10));
        column.getChildren().addAll(stats, tables, actions);
        VBox.setVgrow(tables, Priority.ALWAYS);
        column.getStylesheets().add("/css/bootstrap3.css");

        HBox root = new HBox();
        root.getChildren().add(column);
        HBox.setHgrow(column, Priority.ALWAYS);
        root.setPrefSize(1550, 800);
        root.getStylesheets().add("/css/bootstrap3.css");

        reload();
        return root;
    }

    private void initializeOrderTable() {
        orderTable = new TableView<SaleOrder>();
        orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        orderTable.setStyle("-fx-font-size: 16px;");

        TableColumn<SaleOrder, String> numCol = new TableColumn<SaleOrder, String>("訂單編號");
        numCol.setCellValueFactory(new PropertyValueFactory<SaleOrder, String>("orderNum"));

        TableColumn<SaleOrder, Date> dateCol = new TableColumn<SaleOrder, Date>("時間");
        dateCol.setCellValueFactory(new PropertyValueFactory<SaleOrder, Date>("orderDate"));

        TableColumn<SaleOrder, Double> priceCol = new TableColumn<SaleOrder, Double>("金額");
        priceCol.setCellValueFactory(new PropertyValueFactory<SaleOrder, Double>("totalPrice"));

        TableColumn<SaleOrder, String> nameCol = new TableColumn<SaleOrder, String>("顧客");
        nameCol.setCellValueFactory(new PropertyValueFactory<SaleOrder, String>("customerName"));

        orderTable.getColumns().addAll(numCol, dateCol, priceCol, nameCol);
    }

    private void initializeProductTable() {
        productTable = new TableView<ProductSales>();
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.setStyle("-fx-font-size: 16px;");

        TableColumn<ProductSales, String> nameCol = new TableColumn<ProductSales, String>("品名");
        nameCol.setCellValueFactory(new PropertyValueFactory<ProductSales, String>("productName"));

        TableColumn<ProductSales, Integer> qtyCol = new TableColumn<ProductSales, Integer>("張數");
        qtyCol.setCellValueFactory(new PropertyValueFactory<ProductSales, Integer>("quantity"));

        TableColumn<ProductSales, Integer> revenueCol = new TableColumn<ProductSales, Integer>("營收");
        revenueCol.setCellValueFactory(new PropertyValueFactory<ProductSales, Integer>("revenue"));

        productTable.getColumns().addAll(nameCol, qtyCol, revenueCol);
    }

    private void reload() {
        if (DBConnection.getConnection() == null) {
            orderTable.setItems(FXCollections.observableArrayList());
            productTable.setItems(FXCollections.observableArrayList());
            orderCountLabel.setText("訂單筆數\n—");
            revenueLabel.setText("營業額\n—");
            ticketCountLabel.setText("銷售張數\n—");
            display.setText("無法連線資料庫。請先執行 sql/lottery_pos.sql，並檢查 config/db.properties。");
            return;
        }
        List<SaleOrder> orders = saleOrderDao.getAllSaleOrders();
        List<ProductSales> sales = orderDetailDao.getProductSales();
        int orderCount = saleOrderDao.countOrders();
        int revenue = saleOrderDao.sumRevenue();
        int tickets = saleOrderDao.countTickets();

        orderTable.setItems(FXCollections.observableArrayList(orders));
        productTable.setItems(FXCollections.observableArrayList(sales));
        orderCountLabel.setText("訂單筆數\n" + orderCount);
        revenueLabel.setText("營業額\n" + revenue);
        ticketCountLabel.setText("銷售張數\n" + tickets);

        if (orders.isEmpty()) {
            display.setText("目前沒有訂單。結帳後按「重新整理」即可看到分析。");
            return;
        }

        StringBuilder note = new StringBuilder();
        note.append("共 ").append(orderCount).append(" 筆訂單，營業額 ").append(revenue).append(" 元，賣出 ").append(tickets).append(" 張。\n");
        SaleOrder latest = orders.get(0);
        note.append("最新一筆：").append(latest.getOrderNum());
        if (latest.getOrderDate() != null) {
            note.append("（").append(dateFormat.format(latest.getOrderDate())).append("）");
        }
        note.append("，").append(Math.round(latest.getTotalPrice())).append(" 元。");
        if (!sales.isEmpty()) {
            ProductSales top = sales.get(0);
            note.append("\n銷售最高：").append(top.getProductName())
                    .append("，").append(top.getQuantity()).append(" 張／")
                    .append(top.getRevenue()).append(" 元。");
        }
        display.setText(note.toString());
    }

    private Label makeStatLabel(String title, String value) {
        Label label = new Label(title + "\n" + value);
        label.setPrefSize(280, 110);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setTextFill(Color.web("#FFFFFF"));
        label.setStyle("-fx-font-size: 28px; -fx-background-color: #9B1B30; -fx-background-radius: 8;");
        return label;
    }

    private Label makeSectionTitle(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        return label;
    }

    @Override
    public void start(Stage stage) {
        HBox root = get_root_pane();
        Scene scene = new Scene(root, 1550, 800);
        stage.setTitle("訂單分析");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
