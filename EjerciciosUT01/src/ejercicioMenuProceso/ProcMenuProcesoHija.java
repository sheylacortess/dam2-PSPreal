package ejercicioMenuProceso;

import java.util.Scanner;

public class ProcMenuProcesoHija {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            if (linea.equalsIgnoreCase("SALUDO")) {
                System.out.println("Hola soy tu hijo.");
            } else {
                System.out.println("El eco es: " + linea);
            }
        }
    }
}
