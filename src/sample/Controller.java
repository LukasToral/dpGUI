package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.security.KeyPair;


public class Controller{

    private int RSAkeySize = 0;
    private String RSAtextToEncrypt;
    private String pathToFile;
    private KeyPair RSAkey;

    public javafx.scene.control.Button closeButton, buttonAES, buttonRSA;
    public Pane paneAES, paneRSA, paneZacatek, paneDalsi, rsaTextInput, rsaStartPane, rsaTextInputOption, rsaPathInputOption, zasifrovanyTextPane;
    public Label nazevProgramu, publicKey, privateKey;
    public TextArea zasifrovanyText;
    public TextArea rsaTextArea,RSAcestaKsouboru;


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
        rsaStartPane.toFront();
    }

    public void showZacatek(ActionEvent actionEvent) {
        paneZacatek.toFront();
    }

    public void showDalsi(ActionEvent actionEvent) {
        paneDalsi.toFront();
    }

    public void setFirst(ActionEvent actionEvent) throws Exception {
        this.RSAkeySize = 512;
        RSA rsaCipher = new RSA(this.RSAkeySize);
        this.RSAkey = rsaCipher.generateKeyPair();
        publicKey.setText(this.RSAkey.getPublic().toString());
        privateKey.setText(this.RSAkey.getPrivate().toString());
        System.out.println(this.RSAkeySize);
    }

    public void setSecond(ActionEvent actionEvent) throws Exception {
        this.RSAkeySize = 1024;
        RSA rsaCipher = new RSA(this.RSAkeySize);
        this.RSAkey = rsaCipher.generateKeyPair();
        publicKey.setText(this.RSAkey.getPublic().toString());
        privateKey.setText(this.RSAkey.getPrivate().toString());
    }

    public void setThird(ActionEvent actionEvent) throws Exception {
        this.RSAkeySize = 2048;
        RSA rsaCipher = new RSA(this.RSAkeySize);
        this.RSAkey = rsaCipher.generateKeyPair();
        publicKey.setText(this.RSAkey.getPublic().toString());
        privateKey.setText(this.RSAkey.getPrivate().toString());
    }

    public void setFourth(ActionEvent actionEvent) throws Exception {
        this.RSAkeySize = 3072;
        RSA rsaCipher = new RSA(this.RSAkeySize);
        this.RSAkey = rsaCipher.generateKeyPair();
        publicKey.setText(this.RSAkey.getPublic().toString());
        privateKey.setText(this.RSAkey.getPrivate().toString());
    }

    public void setFifth(ActionEvent actionEvent) throws Exception {
        this.RSAkeySize = 4096;
        RSA rsaCipher = new RSA(this.RSAkeySize);
        this.RSAkey = rsaCipher.generateKeyPair();
        publicKey.setText(this.RSAkey.getPublic().toString());
        privateKey.setText(this.RSAkey.getPrivate().toString());
    }

    public void toRSATextInputHandle(ActionEvent actionEvent) {
        if (this.RSAkeySize != 0) {
            rsaTextInput.toFront();
        //Kontrola, zda byla zadána velikost klíče
        } else {
            Alert chyba = new Alert(Alert.AlertType.WARNING);
            chyba.setTitle("Chyba v zadání!");
            chyba.setHeaderText("Nebyla zadána velikost klíče");
            chyba.setContentText("Nejprve, prosím zadejte velikost klíče.");
            chyba.showAndWait();
        }
    }

    public void rsaTextInputHandle(ActionEvent actionEvent) {
        rsaTextInputOption.toFront();
    }

    public void rsaPathHandle(ActionEvent actionEvent) {
        rsaPathInputOption.toFront();
    }

    public void toRSAencryptPathHandle(ActionEvent actionEvent) throws Exception {
        this.pathToFile = RSAcestaKsouboru.getText();
        if (!this.pathToFile.isEmpty()) {
            //Upravi vlozenou cestu k souboru tak, aby s ni slo dale pracovat C:\ -> C:\\
            char[] charArray = pathToFile.toCharArray();
            //Projde po pismencich predvytvorene pole znaků a pokud najde znak \, nahradí ho znakem \\
            for (char letter : charArray) {
                if (letter == '\\' && charArray[pathToFile.indexOf(letter)+1] != '\\') {
                    pathToFile = pathToFile.replace(String.valueOf(letter), "\\\\");
                    break;
                }
            }
            Text textFromFile = new Text(pathToFile);
            textFromFile.vypisSouboru();

            //Kontrola, zda se jedná o textový soubor
            if (!textFromFile.jeSoubor()) {
                Alert chyba = new Alert(Alert.AlertType.WARNING);
                chyba.setTitle("Chyba v zadání!");
                chyba.setHeaderText("Nebyla zadána správná cesta");
                chyba.setContentText("Na Vámi zadené cestě nebyl nalezen textový soubor.");
                chyba.showAndWait();
            }
            this.RSAtextToEncrypt = textFromFile.stringZeSouboru();
            zasifrovanyTextPane.toFront();
            System.out.println(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()));
            System.out.println(RSA.decrypt(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()), this.RSAkey.getPrivate()));
            zasifrovanyText.setText(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()));
        //Kontrola, zda byla zadána cesta k souboru
        } else {
            Alert chyba = new Alert(Alert.AlertType.WARNING);
            chyba.setTitle("Chyba v zadání!");
            chyba.setHeaderText("Nebyla nalezena cesta k souboru");
            chyba.setContentText("Nejprve, prosím zadejte cestu k souboru.");
            chyba.showAndWait();
        }
    }

    public void toRSAencryptTextHandle(ActionEvent actionEvent) {
        this.RSAtextToEncrypt = rsaTextArea.getText();
        if (!this.RSAtextToEncrypt.isEmpty()) {
            zasifrovanyTextPane.toFront();
        } else {
            Alert chyba = new Alert(Alert.AlertType.WARNING);
            chyba.setTitle("Chyba v zadání!");
            chyba.setHeaderText("Nebyl vložen text k zašifrování");
            chyba.setContentText("Nejprve, prosím zadejte text k zašifrování.");
            chyba.showAndWait();
        }
    }

    public void decryptRSA(ActionEvent actionEvent) throws Exception {
        zasifrovanyText.setText(RSA.decrypt(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()), this.RSAkey.getPrivate()));
    }

    public void encryptRSA(ActionEvent actionEvent) throws Exception {
        zasifrovanyText.setText(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()));
    }
}
