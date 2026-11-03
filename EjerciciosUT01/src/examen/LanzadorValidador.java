package examen;


public class LanzadorValidador {
    public static void main(String[] args) {
        try {
        String[] sArg = new String[2];
        sArg[0] = "Lucía"; // Consultar localizacion con which java
        sArg[1] = "17";
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "examen.ProcesoValidador", sArg);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
