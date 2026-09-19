package lab9_about;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Image_Source extends Application {

    public VBox get_root_pane() {
        Label label5 = new Label("圖片來源");
        label5.setPrefSize(300, 100);
        label5.setAlignment(Pos.CENTER);
        label5.setStyle("-fx-font-size:48px;");
        label5.setTextAlignment(TextAlignment.CENTER);
        label5.getStyleClass().setAll("label", "lb3");

        TextArea display = new TextArea();
        display.setText(readSourceNote());
        display.setPrefSize(1450, 600);
        display.setStyle("-fx-font-size:22px;");
        display.setEditable(false);

        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label5);
        root.getChildren().add(display);
        root.getStylesheets().add("/css/bootstrap3.css");
        return root;
    }

    private String readSourceNote() {
        InputStream in = getClass().getResourceAsStream("/source/source.txt");
        if (in == null) {
            return "公開樹的卡面由 tools/make_demo_cards.py 產生，是原創示範圖。";
        }
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append('\n');
            }
        } catch (Exception ex) {
            return "無法讀取圖片來源說明。";
        }
        return text.toString();
    }

    @Override
    public void start(Stage stage) {
        VBox root = get_root_pane();
        Scene scene = new Scene(root, 1550, 800);
        stage.setTitle("圖片來源");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
