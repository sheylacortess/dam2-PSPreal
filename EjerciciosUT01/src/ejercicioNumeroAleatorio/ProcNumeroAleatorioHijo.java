package ejercicioNumeroAleatorio;

import java.util.Random;
import java.util.Scanner;

public class ProcNumeroAleatorioHijo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine() ) {
            Random rand = new Random();
            int numeroAleatorio = rand.nextInt(10) + 1;
            System.out.println(numeroAleatorio); // esto es lo que va a devolver
            System.out.flush(); //importante limpiar el buffer
        }
    }
}
