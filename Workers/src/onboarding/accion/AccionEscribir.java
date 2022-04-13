package onboarding.accion;

import onboarding.Libro;

import java.util.concurrent.Semaphore;

public class AccionEscribir implements Accion {

    private Semaphore escrituraLista;

    private Libro libro;

    public AccionEscribir(Semaphore escrituraLista, Libro libro){
        this.escrituraLista = escrituraLista;
        this.libro = libro;
    }

    @Override
    public void doIt() {
        System.out.println(escrituraLista.availablePermits());
        System.out.println("Thread id: "+ Thread.currentThread().getId() +"Preparando accion escribir");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id: "+ Thread.currentThread().getId() +" Ejecutando accion escribir");
        libro.addAcciones("Escribir");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id: "+ Thread.currentThread().getId() +" Finalizando accion escribir");
        escrituraLista.release();
        System.out.println(escrituraLista.availablePermits());

    }
}
