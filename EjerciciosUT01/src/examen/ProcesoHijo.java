package examen;

public class ProcesoHijo {
    public static void main(String[] args) {
        try {
            if(args.length > 0) {
                String mensajeRecibido = args[0];
                System.out.println("El hijo dice: Mensaje recivido = " + mensajeRecibido);
            }else {
                System.out.println("El hijo no ha recibido nada");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
