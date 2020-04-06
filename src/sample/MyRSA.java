package sample;

import java.math.BigDecimal;
import java.util.Random;

class MyRSA {

    private int P;
    private int Q;
    private int T;

    /**
     * @param p první prvočíslo
     * @param q druhé prvnočíslo
     */
    MyRSA(int p, int q) {
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

    public static boolean isPrime(int num) {
        boolean kontrola = false;

        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                kontrola = true;
                break;
            }
        }

        return !kontrola;
    }

    /**
     * Premeni zadanou zpravu ze stringu na cislo pouzitim poradi cisla v abecede:
     * a = 1
     * b = 2 ...
     *
     * @param text returns String message transformed to int
     * @return vraci integer prevedeneho textu pomoci poradi v abecedě.
     */
    private int transformTextToInt(String text) {
        //Premeni zpravu na lowercase string a pote na char array
        char[] messageArray = text.toLowerCase().toCharArray();

        //Pouzij jsem StringBuilder, protoze pozdeji v metodě používám metodu append namísto +=
        StringBuilder messageToInt = new StringBuilder();
        for (char c : messageArray) {
            //vytvoreni promenné temp a ulozeni ASCII hodnotu daného znaku8
            int temp = (int) c;

            if (temp >= 48 && temp <= 57) {
                //Vyhodit chybu!
                System.out.println("Zprava opsahuje cislo uprostred textu!");
            }

            //96 je posledni hodnota pred zacatkem lowercase abecedy v ASCII tabulce
            int temp_integer = 96;
            if (temp <= 122 & temp >= 97) {
                //Pomoci (temp - temp_integer) ziskam poradi v abecede
                messageToInt.append(temp - temp_integer);

            }
        }
        return Integer.parseInt(messageToInt.toString());
    }

    String transformTextToString(int text) {
        //Integer na char array
        char[] messageArray = String.valueOf(text).toCharArray();

        //Pouzij jsem StringBuilder, protoze pozdeji v metodě používám metodu append namísto +=
        StringBuilder messageToString = new StringBuilder();
        for (char c : messageArray) {
            //Premeni znak (ktery je vzdy cislo ulozene jako ve forme charu) na znak z abecedy adekvatni k hodnote intValOfC
            int intValOfC = Integer.parseInt(String.valueOf(c));
            messageToString.append((intValOfC > 0 && intValOfC < 27) ? String.valueOf((char) (intValOfC + 'a' - 1)) : null);
        }
        return messageToString.toString();
    }

    int[] generatePublicKey() {
        Random generator = new Random();
        //First part of public key
        int n = this.P * this.Q;

        //Hledám druhou část public key -> e se stává druhou částí tohoto klíče
        //Platí podmínka 1 < e < Φ(n)
        //(Φ(n) = T = (p - 1)*(q - 1))

        //Generujeme e
        boolean kontrola = false;
        int e = -1;
        int max = (P + Q) / 2; //Hranici pro maximalni cislo urcim jako prumer prvocisel

        while (kontrola != true) {
            //(max - min + 1) + min
            e = generator.nextInt(max - 2 + 1) + 2;
            if (n % e != 0) {
                kontrola = true;
            }
        }

        return new int[]{n, e};
    }

    int generatePrivateKey(int e) {
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

    int encrypt(String message, int e) {
        for (int i = 2; i < this.T; i++) {
            if (euclidsGCD(i, this.T) == 1) {
                e = i;
                break;
            }
        }

        int n = this.P * this.Q;
        int msg = this.transformTextToInt(message);
        double c = Math.pow(msg, e);
        c = c % n;

        return (int) Math.round(c);
    }

    int decrypt(int decryptedMessage, int privateKey) {
        BigDecimal message = new BigDecimal(decryptedMessage);

        BigDecimal dec = message.pow(privateKey);

        int n = this.P * this.Q;

        return dec.remainder(new BigDecimal(n)).intValue();
    }

}
