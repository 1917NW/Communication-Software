package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {


    private static final double MAX_LABEL_WIDTH = 200; // 最大宽度值

    private static final double MAX_TEXT_WIDTH = 200; // 最大宽度值

    private int startIndex = -1;
    @Override
    public void start(Stage primaryStage) throws IOException {

        Label label = new Label("可滑动选中的文本内容");


        // 设置鼠标按下事件处理程序
        label.setOnMousePressed(event -> {
            if (event.getButton().equals(Mo)) {
                startIndex = getIndex(label, event.getX());
            }
        });

        // 设置鼠标拖动事件处理程序
        label.setOnMouseDragged(event -> {
            if (startIndex != -1) {
                int endIndex = getIndex(label, event.getX());
                label.selectRange(startIndex, endIndex);
            }
        });

        // 设置鼠标释放事件处理程序
        label.setOnMouseReleased(event -> startIndex = -1);

        StackPane root = new StackPane(label);
        primaryStage.setScene(new Scene(root, 200, 100));
        primaryStage.show();

    }

    private int getIndex(Label label, double x) {
        int index = 0;
        double width = label.getWidth();

        for (int i = 0; i < label.getText().length(); i++) {
            double charWidth = label.getFont().getSize() / 2;
            if (x <= width) {
                return index;
            }
            width += charWidth;
            index++;
        }

        return index;
    }

    public static void main(String[] args) {
        launch();
    }


    public void show(Stage primaryStage){
        Label label = new Label();
        Text text = new Text("这是一段很长的文本11111111文本11111111111111111111111");
        // 将文本设置给Label，并将Label添加到布局中

        HBox root = new HBox(label);
        root.setPrefWidth(500);
        root.setAlignment(Pos.TOP_RIGHT);


        // 获取文本的宽度
        Bounds textBounds = text.getBoundsInLocal();
        double textWidth = textBounds.getWidth();

        // 设置Label的宽度，不超过最大宽度
        label.setPrefWidth(Math.min(textWidth, MAX_LABEL_WIDTH));
        label.setText(text.getText());
        label.setWrapText(true);

        label.setStyle("-fx-background-color: red");


        // 创建场景并显示
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}