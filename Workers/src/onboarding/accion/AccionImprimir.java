package onboarding.accion;


import java.util.concurrent.Semaphore;

public class AccionImprimir implements Accion {

    private Semaphore lecturaLista;

    public AccionImprimir(Semaphore lecturaLista){
        this.lecturaLista = lecturaLista;
    }

    @Override
    public void doIt() {
        try {
            System.out.println(lecturaLista.availablePermits());
            lecturaLista.acquire();
            System.out.println(lecturaLista.availablePermits());
            System.out.println("Thread id: "+ Thread.currentThread().getId() +" Preparando onboarding.workers.accion imprimir");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread id: "+ Thread.currentThread().getId() +"Ejecutando onboarding.workers.accion imprimir");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread id: "+ Thread.currentThread().getId() +"Finalizando onboarding.workers.accion imprimir");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
