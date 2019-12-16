package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Controller{

    public javafx.scene.control.Button closeButton, buttonAES, buttonRSA;
    public Pane paneAES, paneRSA, paneZacatek, paneDalsi;
    public Label nazevProgramu;


    public void closeApp(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        System.out.println("Exiting app...");
        stage.close();
    }


    public void showAES(ActionEvent actionEvent) {
        paneAES.toFront();
    }

    public void showRSA(ActionEvent actionEvent) {
        paneRSA.toFront();
    }

    public void showZacatek(ActionEvent actionEvent) {
        paneZacatek.toFront();
    }

    public void showDalsi(ActionEvent actionEvent) {
        paneDalsi.toFront();
    }
}
