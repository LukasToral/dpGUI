package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Text {
    private File textovySoubor;
    private String cestakSouboru;

    public Text(String cestakSouboru) {
        this.textovySoubor = new File(cestakSouboru);
    }

    public boolean jeSoubor() {
        if (textovySoubor.exists() && !textovySoubor.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    public void vypisSouboru() throws IOException {
        if (textovySoubor.exists() && !textovySoubor.isDirectory()) {
            BufferedReader br = new BufferedReader(new FileReader(textovySoubor));
            String st;
            while ((st = br.readLine()) != null)
                System.out.println(st);
        }
    }

    public List<Character> vytvoreniPoleZnaku() throws IOException {
        List<Character> poleZnaku = new ArrayList<Character>();
        if (textovySoubor.exists() && !textovySoubor.isDirectory()) {
            BufferedReader br = new BufferedReader(new FileReader(textovySoubor));
            int character;

            while ((character = br.read()) != -1) {
                poleZnaku.add((char) character);
            }

        }

        return poleZnaku;
    }

    public String stringZeSouboru() throws IOException {
        String textZeSouboru="";
        if (textovySoubor.exists() && !textovySoubor.isDirectory()) {
            BufferedReader br = new BufferedReader(new FileReader(textovySoubor));
            String radek;

            while ((radek = br.readLine()) != null) {
                textZeSouboru += radek;
            }

        }
        return textZeSouboru;
    }


}
