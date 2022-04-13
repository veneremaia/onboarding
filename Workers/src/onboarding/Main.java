package onboarding;

import onboarding.accion.Accion;
import onboarding.accion.AccionEscribir;
import onboarding.accion.AccionImprimir;
import onboarding.accion.AccionLeer;
import onboarding.worker.WorkerX;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        /*for(int i = 0; i<1000; i++) {
            onboarding.workers.worker.WorkerA workerA = new onboarding.workers.worker.WorkerA("Tarea A " + i);
            onboarding.workers.worker.WorkerB workerB = new onboarding.workers.worker.WorkerB("Tarea B " + i);
            workerA.start();
            workerB.start();
        }*/

        Libro libro =  new Libro("Libro 1");
        Semaphore escrituraLista = new Semaphore(0);
        Semaphore lecturaLista = new Semaphore(0);

        System.out.println(escrituraLista.availablePermits());
        Accion leer = new AccionLeer(escrituraLista,lecturaLista);
        Accion escribir = new AccionEscribir(escrituraLista, libro);
        Accion imprimir = new AccionImprimir(lecturaLista);

        WorkerX workerXleer1 = new WorkerX(leer);
        WorkerX workerXescribir = new WorkerX(escribir);
        WorkerX workerXLeer2 = new WorkerX(leer);
        WorkerX workerXImprimir = new WorkerX(imprimir);

        workerXImprimir.start();
        workerXleer1.start();
        workerXescribir.start();
        workerXLeer2.start();

    }
}

