import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class FrontEnd {
    public static void main(String[] args) {

        //arrarys para almacenar los datos de los clientes
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Double> saldos = new ArrayList<>();

        String[] sArg = new String[2];
        sArg[0] = "java";
        sArg[1] = "BackEnd";
        ProcessBuilder pb = new ProcessBuilder(sArg);
        pb.directory(new File("./bin"));

        try {
            Process proceso = pb.start();
            InputStream mils = proceso.getInputStream();
            InputStreamReader isr = new InputStreamReader(mils);
            BufferedReader br = new BufferedReader(isr);
            String informacion = "";
            // lee lo que manda el hijo
            while ((informacion = br.readLine()) != null) {
                String[] partes = informacion.split("#");
                if (partes.length == 2) {
                    nombres.add(partes[0]);
                    saldos.add(Double.parseDouble(partes[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("Gestor de bancos. Dispones de las siguientes opciones.");
            System.out.println("1 - Saldo");
            System.out.println("2 - Ingreso");
            System.out.println("3 - Salir");
            System.out.print("Opcion: ");
            
            String lectura = sc.nextLine();

            
            opcion = Integer.parseInt(lectura);

            if (opcion == 1) {
                System.out.print("Los clientes del banco son ");
                for (String n : nombres) {
                    System.out.print(n + " ");
                }
                System.out.println();
                System.out.print("Selecciona el cliente ");
                String clienteSelec = sc.nextLine();

                for (int i = 0; i < nombres.size(); i++) {
                    if (nombres.get(i).equalsIgnoreCase(clienteSelec)) {
                        System.out.println("El saldo de " + nombres.get(i) + " es" + saldos.get(i));
                    }
                }
            } else if (opcion == 2) {
                System.out.print("Los clientes del banco son: ");
                for (String n : nombres) {
                    System.out.print(n + " ");
                }
                System.out.println();
                System.out.print("Selecciona el cliente: ");
                String clienteSelec = sc.nextLine();
                System.out.print("Cuanto quieres ingresar: ");
                double cant = Double.parseDouble(sc.nextLine());

                for (int i = 0; i < nombres.size(); i++) {
                    if (nombres.get(i).equalsIgnoreCase(clienteSelec)) {
                        saldos.set(i, saldos.get(i) + cant);
                        System.out.println("El saldo de " + nombres.get(i) + " es " + saldos.get(i));
                    }
                }
            }
        }
    }
}
