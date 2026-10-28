package practica1;

public class InspectorDeProcesos {
    public static void main(String[] args) {
        try {
            //1 configuro el proceso 
            ProcessBuilder pb = new ProcessBuilder("ls", "/Escritorio/PSP/dam2-PSPreal");
            //2 empiezo el proceso 
            Process proceso = pb.start();
            //voy a hacer que el proceso padre esper a que el hijo termine con WaitFor 
            // el metodo wait for devuielve el numero del codigo de salida 
            int codigoSalida = proceso.waitFor(); //si wait for devuleve un numero distinto de 0, significa que la ejecucion ha fallado
            if (codigoSalida == 0) {
                System.out.println("El proceso ha finalizado correctamente!");
            }else {
                System.out.println("El proceso ha fallado con el codigo de error: " + codigoSalida);
            }

        } catch (Exception e) {
            System.err.println("Error inesperado : " + e.getMessage());
        }
    }
}
