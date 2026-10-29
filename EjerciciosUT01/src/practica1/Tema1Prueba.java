package practica1;

import java.io.File;
import java.io.IOException;

public class Tema1Prueba {
    public static void main(String[] args) {
        String[] sArg = new String[2];
        sArg[0] = "/usr/bin/java"; // Consultar localizacion con which java
        sArg[1] = "practica1.Tema1PruebaH";
        // si quiero probar lo errroes y que se rellene el error.txt --> sArg[1] = "ls -laeevefef";
        // .class, sin indicar
        ProcessBuilder mipb = null;
        Process miProc = null;

        mipb = new ProcessBuilder(sArg);
        mipb.directory(new File("./bin"));
        File fInp = new File("numero.txt");
        File fOut = new File("salida.txt");
        File fErr = new File("error.txt");
        mipb.redirectInput(fInp);
        mipb.redirectOutput(fOut);
        mipb.redirectError(fErr);
        try {
            miProc = mipb.start();
            // Inicia el proceso
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(miProc.pid());
        // Pid del proceso iniciado

    }
}