package ejercicioMenuProceso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class ProcMenuProcesPadre {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        String devuelto = "";

        ProcessBuilder pb = new ProcessBuilder("java", "ejercicioMenuProceso.ProcMenuProcesoHija");
        
        pb.directory(new File("./bin"));
        Process hijo = pb.start();

        BufferedWriter alHijo = new BufferedWriter(new OutputStreamWriter(hijo.getOutputStream()));
        BufferedReader delHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));

        do {
            System.out.println("\nMenu de opciones. Elija por favor: ");
            System.out.println("* Eco: para recibir un eco del otro proceso");
            System.out.println("* Saludo: para recibir Hola del otro proceso");
            System.out.println("* Vivo: para comprobar si el otro proceso esta vivo");
            System.out.println("* Resucitar: para activar otro proceso hijo");
            System.out.println("* Salir: para salir del programa");
            System.out.println("Indica tu opcion: ");
            opcion = sc.nextLine();
            opcion = opcion.toLowerCase();

            switch (opcion) {
                case "eco": 
                    System.out.println("Escribe algo: ");
                    String textoEco = sc.nextLine();
                    alHijo.write(textoEco);
                    alHijo.newLine(); // esto es obligatorio, sino se queda tostao, como el boton de enviar de whatsapp 
                    alHijo.flush(); // investigar que es esto, para empujar lo escrito a la clase hijo, si no le doy al flush se queda en el buffer

                    devuelto = delHijo.readLine();
                    System.out.println(devuelto);
                    break;

                case "saludo":
                    alHijo.write("SALUDO");
                    alHijo.newLine(); 
                    alHijo.flush(); // si no lo pones se queda en la "tubería"

                    devuelto = delHijo.readLine();
                    System.out.println(devuelto);
                    break;

                case "vivo":
                    if (hijo.isAlive()) {
                        System.out.println("El hijo está vivo. Su pid es: " + hijo.pid());
                    }else {
                        System.out.println("El hijo esta muerto.");
                    }

                    break;
                case "matar" :
                    alHijo.close();
                    System.out.println("Hijo muerto.");
                    break;

                case "resucitar" :
                    alHijo = new BufferedWriter(new OutputStreamWriter(hijo.getOutputStream()));
                    hijo = pb.start();
                    System.out.println("Hijo revivido. ");
                    break;

                case "salir":
                    System.out.println("Saliendo.");
                    break;            
                default:
                    System.out.println("Elija una opcion válida.");
                    break;
            }
        } while (!opcion.equalsIgnoreCase("salir"));

    }
}
