package ejercicioNumeroAleatorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class ProcNumeroAleatorioPadre {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String textoIntroducido = "";
        int devuelto;
        ProcessBuilder pb = new ProcessBuilder("java", "ejercicioNumeroAleatorio.ProcNumeroAleatorioHijo");

        pb.directory(new File("./bin"));
        Process hijo = pb.start();

        BufferedWriter alHijo = new BufferedWriter(new OutputStreamWriter(hijo.getOutputStream()));
        BufferedReader delHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));

        try {
            do {
                System.out.println("¿Quiere obtener un número aleatorio? Si no es el caso, introduzca FIN.");
                textoIntroducido = sc.nextLine();
                if (textoIntroducido.equalsIgnoreCase("FIN")) {
                    break; // O sal del bucle directamente
                }
                alHijo.write(textoIntroducido);
                alHijo.newLine(); // esto es obligatorio, sino se queda tostao, como el boton de enviar de
                                  // whatsapp
                alHijo.flush(); // investigar que es esto, para empujar lo escrito a la clase hijo, si no le doy
                                // al flush se queda en el buffer

                devuelto = Integer.parseInt(delHijo.readLine());
                System.out.println("El numero aleatorio es: ");
                System.out.println(devuelto);

            } while (!textoIntroducido.equalsIgnoreCase("FIN"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
