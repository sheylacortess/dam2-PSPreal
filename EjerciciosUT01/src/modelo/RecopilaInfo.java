import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Cada hilo de esta clase recupera el resultado de un encuestado
public class RecopilaInfo extends Thread {
		public String sNombre;
		Monitor mon; 
		
		// Recibe como parametro en nombre del encuestado. El resultado de su encuesta esta en el fichero nombre_encuestado.txt
		// Una referencia a la clase Monitor. Seccion critica donde diferentes hilos guardan el resultado de su encuestado simultaneamente
		public RecopilaInfo(String sNombre,Monitor mon)
		{
			this.sNombre = sNombre;
			this.mon = mon;
		}
	
		public void run ()
		{
			int iValorEncuesta;
		    String filePath = sNombre + ".txt"; 		// Ruta del archivo a leer
	        
		    try (FileReader fileReader = new FileReader(filePath);
	             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	            	iValorEncuesta = Integer.parseInt(line);
	            	mon.IncColor(iValorEncuesta);
	            }
	        } catch (IOException e) {
	        	mon.IncNoEnc ();						// No es posible recuperar info de este encuestado
	            // System.out.println("Error al leer el archivo: " + e.getMessage());
	        }
		}
}
