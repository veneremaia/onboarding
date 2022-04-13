package onboarding.accion;

import java.util.concurrent.Semaphore;

public class AccionLeer implements Accion {

    private Semaphore puedoLeer;

    private Semaphore lecturaLista;

    public AccionLeer(Semaphore puedoLeer, Semaphore lecturaLista){
        this.puedoLeer = puedoLeer;
        this.lecturaLista = lecturaLista;
    }
    @Override
    public void doIt() {
        try {
            System.out.println(puedoLeer.availablePermits());
            puedoLeer.acquire();
            System.out.println(puedoLeer.availablePermits());
            System.out.println("Thread id: "+ Thread.currentThread().getId() +" Preparando accion leer");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread id: "+ Thread.currentThread().getId() +" Ejecutando accion leer");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread id: "+ Thread.currentThread().getId() +" Finalizando accion leer");
            lecturaLista.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
