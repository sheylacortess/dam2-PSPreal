package practica1;

import java.util.Scanner;

public class Tema1PruebaH {
    public static void main(String[] args) {
        int iValorUno, iValorDos, iResul;
        Scanner miScan = new Scanner(System.in);
        String sInfo = miScan.nextLine();
        iValorUno = Integer.parseInt(sInfo);
        sInfo = miScan.nextLine();
        iValorDos = Integer.parseInt(sInfo);

        iResul = iValorUno + iValorDos;
        System.out.println(iValorUno + " + " + iValorDos + " es igual a " + iResul);
        miScan.close();
    }
}