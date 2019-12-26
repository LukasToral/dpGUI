package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.security.KeyPair;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
//        Text textovySouobor = new Text("C:\\Users\\Lukas\\Desktop\\test.txt");
//        textovySouobor.vypisSouboru();
//        System.out.print(textovySouobor.vytvoreniPoleZnaku());
//        System.out.println(textovySouobor.stringZeSouboru());
//        try {
//            System.out.println("RSA");
//            KeyPair klic = RSA.generateKeyPair();
//            String zasifrovanyText = RSA.encrypt(textovySouobor.stringZeSouboru(),klic.getPublic());
//            System.out.println(zasifrovanyText);
//            System.out.println(RSA.decrypt(zasifrovanyText,klic.getPrivate()));
//
//            System.out.println("AES");
//            String key = "1234567891234566";
//            String a = AES.encrypt(textovySouobor.stringZeSouboru(), key);
//            System.out.println(a);
//            System.out.println(AES.decrypt(a, key));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        launch(args);
    }

}
