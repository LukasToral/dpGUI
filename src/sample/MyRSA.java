package sample;

import java.math.BigDecimal;

public class MyRSA {

    private int P;
    private int Q;
    private int T;

    public MyRSA(int p, int q) {
        this.P = p;
        this.Q = q;
        this.T = (p - 1) * (q - 1);
    }

    /**
     * Function to find GCD of two integers
     *
     * @param u fist prime
     * @param w second prime
     * @return gcd of a & b
     */
    private int euclidsGCD(int u, int w) {
        int temp;
        while (w != 0) {
            temp = u % w;
            u = w;
            w = temp;
        }
        return u;
    }

    /**
     * Premeni zadanou zpravu ze stringu na cislo pouzitim poradi cisla v abecede:
     * a = 1
     * b = 2 ...
     *
     * @param message returns String message transformed to int
     * @return
     */
    private int transformMessageToInt(String message) {
        //Premeni zpravu na lowercase string a pote na char array
        char[] messageArray = message.toLowerCase().toCharArray();

        String messageToInt = "";
        for (char c : messageArray) {
            //vytvoreni promenné temp a ulozeni ASCII hodnotu daného znaku
            int temp = (int) c;

            if (temp >= 48 && temp <= 57) {
                //Vyhodit chybu!
                System.out.println("Zprava opsahuje cislo uprostred textu!");
            }

            //96 je posledni hodnota pred zacatkem lowercase abecedy v ASCII tabulce
            int temp_integer = 96;
            if (temp <= 122 & temp >= 97) {
                //Pomoci (temp - temp_integer) ziskam poradi v abecede
                messageToInt += (temp - temp_integer);
            }
        }
        return Integer.parseInt(messageToInt);
    }

    private String transformMessageToString(int message) {
        //Integer na char array
        char[] messageArray = String.valueOf(message).toCharArray();

        String messageToString = "";
        for (char c : messageArray) {
            //Premeni znak (ktery je vzdy cislo ulozene jako ve forme charu) na znak z abecedy adekvatni k hodnote intValOfC
            int intValOfC = Integer.parseInt(String.valueOf(c));
            messageToString += (intValOfC > 0 && intValOfC < 27) ? String.valueOf((char) (intValOfC + 'a' - 1)) : null;
        }
        return messageToString;
    }

    public int[] generatePublicKey() {

        //First part of public key
        int n = this.P * this.Q;

        //Hledám druhou část public key -> e se stává druhou částí tohoto klíče
        //Platí podmínka 1 < e < Φ(n)
        //(Φ(n) = T = (p - 1)*(q - 1))
        int e = 461;
        for (int i = 2; i < this.T; i++) {
            if (euclidsGCD(i, this.T) == 1) {
                e = i;
                break;
            }
        }

        return new int[]{n, e};
    }

    public int generatePrivateKey(int e) {
        for (int i = 2; i < this.T; i++) {
            if (euclidsGCD(i, this.T) == 1) {
                e = i;
                break;
            }
        }

        int d = 0;
        for (int i = 1; i < 10; i++) {
            int x = 1 + i * this.T;
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }

        return d;
    }

    public int encrypt(String message, int e) {
        for (int i = 2; i < this.T; i++) {
            if (euclidsGCD(i, this.T) == 1) {
                e = i;
                break;
            }
        }

        int n = this.P * this.Q;
        int msg = this.transformMessageToInt(message);

        double c = Math.pow(msg, e);
        c = c % n;

        return (int) Math.round(c);
    }

    public int decrypt(double decryptedMessage, int privateKey) {
        System.out.println("dec = " + decryptedMessage);
        System.out.println("PK = " + privateKey);

        BigDecimal message = new BigDecimal(decryptedMessage);

        //pow(message, key)
        BigDecimal dec = message.pow(privateKey);

        System.out.println("M = " + dec);
        int n = this.P * this.Q;


        return dec.remainder(new BigDecimal(n)).intValue();
    }

}
