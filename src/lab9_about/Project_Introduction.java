package lab9_about;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Project_Introduction extends Application {

    public VBox get_root_pane() {
        Label label5 = new Label("專案介紹");
        label5.setPrefSize(300, 100);
        label5.setAlignment(Pos.CENTER);
        label5.setStyle("-fx-font-size:48px;");
        label5.setTextAlignment(TextAlignment.CENTER);
        label5.getStyleClass().setAll("label", "lb3");

        TextArea display = new TextArea();
        display.appendText(
                "ScratchPOS 是 2023 課程的 JavaFX + MariaDB 銷售示範。\n"
                + "商品名稱與票面都是虛構的，不是任何官方彩券。\n\n"
                + "使用前：\n"
                + "1. 在 MariaDB 執行 sql/lottery_pos.sql，建立 lottery_pos 資料庫。\n"
                + "2. 預設連線 localhost:3306，帳號可放 config/db.properties，\n"
                + "   或設環境變數 LOTTERY_POS_DB_URL / USER / PASSWORD。\n"
                + "3. 範本在 config/db.properties.example。\n\n"
                + "功能：\n"
                + "1. 依價格分類點選虛構刮刮樂，寫入購物車與結帳。\n"
                + "2. 從資料庫讀取並維護商品。\n"
                + "3. 訂單分析：筆數、營業額、張數與商品排行。\n"
                + "4. 橫式版面，按鈕與文字偏大，方便課堂投影。\n"
        );
        display.setPrefSize(1450, 600);
        display.setStyle("-fx-font-size:28px;");
        display.setEditable(false);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label5);
        root.getChildren().add(display);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getStylesheets().add("/css/bootstrap3.css");
        return root;
    }

    @Override
    public void start(Stage stage) {
        VBox root = get_root_pane();
        Scene scene = new Scene(root, 1550, 800);
        stage.setTitle("專案介紹");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
