package guionTeoria;

public class T2RatonesThr extends Thread {
    public static void main(String[] args) {
        System.out.println("Antes de lanzar los hilos");
        T2RatonesThr miTh1 = new T2RatonesThr();
        miTh1.start();
        T2RatonesThr miTh2 = new T2RatonesThr();
        miTh2.start();
        System.out.println("Despues de lanzar los hilos");
    }

    @Override
    public void run() {
        System.out.println("HOLA");
    }
}