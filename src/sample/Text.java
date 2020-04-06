package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Text {
    private File textovySoubor;

    public Text(String cestakSouboru) {
        this.textovySoubor = new File(cestakSouboru);
    }

    public File getTextovySoubor() {
        return textovySoubor;
    }

    public void setTextovySoubor(File textovySoubor) {
        this.textovySoubor = textovySoubor;
    }

    public boolean isFile() {
        return textovySoubor.exists() && !textovySoubor.isDirectory();
    }

    public void vypisSouboru() throws IOException {
        if (textovySoubor.isFile()) {
            BufferedReader br = new BufferedReader(new FileReader(textovySoubor));
            String st;
            while ((st = br.readLine()) != null)
                System.out.println(st);
        }
    }

    public String stringZeSouboru() throws IOException {
        String textZeSouboru = "";
        if (textovySoubor.isFile()) {
            BufferedReader br = new BufferedReader(new FileReader(textovySoubor));
            String radek;

            while ((radek = br.readLine()) != null) {
                textZeSouboru += radek;
            }

        }
        return textZeSouboru;
    }


}
