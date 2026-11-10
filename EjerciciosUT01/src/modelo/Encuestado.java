import java.io.FileWriter;
import java.io.IOException;

// Aplicacion que genera una respuesta a la encuesta. 
// Son lanzados tantos procesos de esta aplicacion desde el servidor como encuestados participan en la muestra
public class Encuestado {
	public static void main (String [] args)
	{
		try {
			Thread.sleep((int) (Math.random() * 500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		// El nombre del encuestado se construye en base al tiempo actual
		// El resultado de la encuesta se almacena en un fichero con el nombre nombre_encuestado.tx
		String sName = Long.toString(System.currentTimeMillis());
		System.out.print(sName + "\n");
		
		// Un tiempo aleatorio para que pueda ser significativamente diferente el tiempo de ejecucion entre encuestados
		try {
			Thread.sleep((int) (Math.random() * 2000) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int iPublicaSol = ((int) (Math.random() * 5));	// Numero entre 0 y 4 para que 20% veces no se publique resultado
		
		if (iPublicaSol != 4 )	// 4 es el unico valor entre los cinco posibles en los que no se publicara resultado
		{
			// El resultado de la encuesta se genera aleatoriamente. Un numero entre 1 y 9
			int iValorEsc = ((int) (Math.random() * 9) + 1);

			// Almacena en fichero el resultado de su participacion en la encuesta
			try (FileWriter writer = new FileWriter(sName + ".txt")) {
				writer.write(Integer.toString(iValorEsc));
			} catch (IOException e) {
			}
		}
        
        
	}
}
