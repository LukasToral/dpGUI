//C:\Users\Lukas\Desktop\test.txt

package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;


public class Controller {

    public javafx.scene.control.Button closeButton, buttonAES, buttonRSA;
    public Pane paneAES, paneRSA, paneZacatek, paneDalsi, rsaTextInput, rsaStartPane, zasifrovanyTextPaneRSA, rsaTextInputOption, rsaPathInputOption, zasifrovanyTextPane, aesPathInputOption, aesTextInputOption, aesStartPane, aesTextInput, sifraPaneRSA, rsaFileInput, aesFileInputOption;
    public Label nazevProgramu, publicKey, privateKey, KeyAES;
    public TextArea zasifrovanyTextRSA, aesTextArea, AEScestaKsouboru, zasifrovanyTextAES;
    public TextArea rsaTextArea, RSAcestaKsouboru;
    private Stage stage;
    private int RSAkeySize = 0;
    private String RSAtextToEncrypt;
    private String pathToFile;
    private KeyPair RSAkey;
    private SecretKey AESkey;
    private String AEStextToEncrypt;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void closeApp(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        System.out.println("Exiting app...");
        stage.close();

    }

    public void showAES(ActionEvent actionEvent) {
        paneAES.toFront();
        aesStartPane.toFront();
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
                if (letter == '\\' && charArray[pathToFile.indexOf(letter) + 1] != '\\') {
                    pathToFile = pathToFile.replace(String.valueOf(letter), "\\\\");
                    break;
                }
            }

            //Nacte soubor pomoci tridy Text
            Text textFromFile = new Text(pathToFile);
            textFromFile.vypisSouboru();
            //Nastavi ze souboru text, ktery se bude pozdeji sifrovat
            this.RSAtextToEncrypt = textFromFile.stringZeSouboru();


            //Kontrola, zda se jedná o textový soubor
            if (!textFromFile.isFile()) {
                Alert chyba = new Alert(Alert.AlertType.WARNING);
                chyba.setTitle("Chyba v zadání!");
                chyba.setHeaderText("Nebyla zadána správná cesta");
                chyba.setContentText("Na Vámi zadené cestě nebyl nalezen textový soubor.");
                chyba.showAndWait();
            }


            sifraPaneRSA.toFront();
            System.out.println(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()));
            System.out.println(RSA.decrypt(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()), this.RSAkey.getPrivate()));
            zasifrovanyTextRSA.setText(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()));

            //Kontrola, zda byla zadána cesta k souboru
        } else {
            Alert chyba = new Alert(Alert.AlertType.WARNING);
            chyba.setTitle("Chyba v zadání!");
            chyba.setHeaderText("Nebyla nalezena cesta k souboru");
            chyba.setContentText("Nejprve, prosím zadejte cestu k souboru.");
            chyba.showAndWait();
        }
    }

    public void toRSAencryptTextHandle(ActionEvent actionEvent) throws Exception {
        this.RSAtextToEncrypt = rsaTextArea.getText();
        if (!this.RSAtextToEncrypt.isEmpty()) {
            sifraPaneRSA.toFront();
            System.out.println(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()));
            System.out.println(RSA.decrypt(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()), this.RSAkey.getPrivate()));
            zasifrovanyTextRSA.setText(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()));
        } else {
            Alert chyba = new Alert(Alert.AlertType.WARNING);
            chyba.setTitle("Chyba v zadání!");
            chyba.setHeaderText("Nebyl vložen text k zašifrování");
            chyba.setContentText("Nejprve, prosím zadejte text k zašifrování.");
            chyba.showAndWait();
        }
    }

    public void decryptRSA(ActionEvent actionEvent) throws Exception {
        zasifrovanyTextRSA.setText(RSA.decrypt(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()), this.RSAkey.getPrivate()));
    }

    public void encryptRSA(ActionEvent actionEvent) throws Exception {
        zasifrovanyTextRSA.setText(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()));
    }

    public void generateKeyHandle(ActionEvent actionEvent) throws Exception {
        this.AESkey = AES.generateKeyPair();
        KeyAES.setText(String.valueOf(AESkey.getEncoded()));
    }

    public void aesTextInputHandle(ActionEvent actionEvent) {
        aesTextInputOption.toFront();
    }

    public void toAESTextInput(ActionEvent actionEvent) {
        if (this.AESkey != null) {
            aesTextInput.toFront();
            System.out.println(String.valueOf(this.AESkey).isEmpty());
            System.out.println(this.AESkey);
            //Kontrola, zda byla zadána velikost klíče
        } else {
            Alert chyba = new Alert(Alert.AlertType.WARNING);
            chyba.setTitle("Chyba v zadání!");
            chyba.setHeaderText("Nebyl vygenerován klíč");
            chyba.setContentText("Nejprve si, prosím, vygenerujte klíč k zašifrování a dešifrování.");
            chyba.showAndWait();
        }
    }

    public void aesPathHandle(ActionEvent actionEvent) {
        aesPathInputOption.toFront();
    }

    public void toAESencryptPathHandle(ActionEvent actionEvent) throws IOException {
        this.pathToFile = AEScestaKsouboru.getText();
        if (!this.pathToFile.isEmpty() && this.pathToFile.trim().length() > 0) {
            //Upravi vlozenou cestu k souboru tak, aby s ni slo dale pracovat C:\ -> C:\\
            char[] charArray = pathToFile.toCharArray();
            //Projde po pismencich predvytvorene pole znaků a pokud najde znak \, nahradí ho znakem \\
            for (char letter : charArray) {
                if (letter == '\\' && charArray[pathToFile.indexOf(letter) + 1] != '\\') {
                    pathToFile = pathToFile.replace(String.valueOf(letter), "\\\\");
                    break;
                }
            }
            Text textFromFile = new Text(pathToFile);
            textFromFile.vypisSouboru();

            //Kontrola, zda se jedná o textový soubor
            if (textFromFile.isFile()) {
                this.AEStextToEncrypt = textFromFile.stringZeSouboru();
                zasifrovanyTextPane.toFront();
                zasifrovanyTextAES.setText(AES.encrypt(this.AEStextToEncrypt, String.valueOf(this.AESkey)));
                System.out.println(this.pathToFile);
            } else {
                Alert chyba = new Alert(Alert.AlertType.WARNING);
                chyba.setTitle("Chyba v zadání!");
                chyba.setHeaderText("Nebyla zadána správná cesta");
                chyba.setContentText("Na Vámi zadené cestě nebyl nalezen textový soubor.");
                chyba.showAndWait();
            }

            //Kontrola, zda byla zadána cesta k souboru
        } else {
            Alert chyba = new Alert(Alert.AlertType.WARNING);
            chyba.setTitle("Chyba v zadání!");
            chyba.setHeaderText("Nebyla nalezena cesta k souboru");
            chyba.setContentText("Nejprve, prosím zadejte cestu k souboru.");
            chyba.showAndWait();
        }
    }

    public void toAESencryptTextHandle(ActionEvent actionEvent) {
        this.AEStextToEncrypt = aesTextArea.getText();
        if (!this.AEStextToEncrypt.isEmpty() && this.AEStextToEncrypt.trim().length() > 0) {
            zasifrovanyTextPane.toFront();
            zasifrovanyTextAES.setText(AES.encrypt(this.AEStextToEncrypt, String.valueOf(this.AESkey)));
        } else {
            Alert chyba = new Alert(Alert.AlertType.WARNING);
            chyba.setTitle("Chyba v zadání!");
            chyba.setHeaderText("Nebyl vložen text k zašifrování");
            chyba.setContentText("Nejprve, prosím zadejte text k zašifrování.");
            chyba.showAndWait();
        }
    }

    public void decryptAES(ActionEvent actionEvent) {
        zasifrovanyTextAES.setText(AES.decrypt(AES.encrypt(this.AEStextToEncrypt, String.valueOf(this.AESkey)), String.valueOf(this.AESkey)));
    }

    public void encryptAES(ActionEvent actionEvent) {
        zasifrovanyTextAES.setText(AES.encrypt(this.AEStextToEncrypt, String.valueOf(this.AESkey)));
    }

    public void rsaFindFile(ActionEvent actionEvent) throws IOException {

    }

    public void aesFindFile(ActionEvent actionEvent) {
        String caller = actionEvent.getSource().toString().substring(10, 13);
        System.out.println(caller);
    }

    public void findFile(ActionEvent actionEvent) {
        String caller = actionEvent.getSource().toString().substring(10, 13);

        //Vytvoření filechooser pro načtení souboru
        FileChooser fileChooser = new FileChooser();
        //Filtr pro možnost načtení pouze .txt souborů
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        //Vyjimka, pokud by nebyl vybrán žádný soubor, zobrazí chybovou hlášku
        try {

            //Zobrazení dialogu pro načtení souboru
            File selectedFile = fileChooser.showOpenDialog(stage);
            Text txt = new Text(String.valueOf(selectedFile.getAbsoluteFile()));

            if (caller.equalsIgnoreCase("aes")) {
                aesFileInputOption.toFront();
                this.AEStextToEncrypt = txt.stringZeSouboru();
            } else {
                rsaFileInput.toFront();
                this.RSAtextToEncrypt = txt.stringZeSouboru();
            }

        } catch (Exception e) {
            Alert chyba = new Alert(Alert.AlertType.WARNING);
            chyba.setTitle("Nastala chyba");
            chyba.setHeaderText("Nebyl vybrán soubor.");
            chyba.setContentText("Nebyl načten text k zašifrování, protože nebyl vybrán žádný textový soubor");
            chyba.showAndWait();
        }
    }


    public void toEncryptFileHandle(ActionEvent actionEvent) throws Exception {
        String caller = actionEvent.getSource().toString().substring(12, 15).toLowerCase();
        //Pouzita funkce equalsIgnorecase, protoze nefungovalo porovnavani pomoci ==
        if (caller.equalsIgnoreCase("aes")) {
            zasifrovanyTextPane.toFront();
            zasifrovanyTextAES.setText(AES.encrypt(this.AEStextToEncrypt, String.valueOf(this.AESkey)));
        } else {
            sifraPaneRSA.toFront();
            zasifrovanyTextRSA.setText(RSA.encrypt(this.RSAtextToEncrypt, this.RSAkey.getPublic()));
        }
    }
}
