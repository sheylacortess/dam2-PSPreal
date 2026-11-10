import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BackEnd {
    public static void main(String[] args) {
        File fichero = new File("clientes.txt");

        if (fichero.exists()) {
            try {
                Scanner sc = new Scanner(fichero);
                while (sc.hasNextLine()) {
                    System.out.println(sc.nextLine());
                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PrintWriter pw = new PrintWriter(fichero);
                Scanner sc = new Scanner(System.in);
                while (sc.hasNextLine()) {
                    String linea = sc.nextLine();
                    if (linea.isEmpty()) {
                        break;
                    }
                    pw.println(linea);
                }
                sc.close();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
 
