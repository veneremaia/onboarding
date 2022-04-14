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
        }

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

         */

        Cola libros = new Cola();
        Cola librosEscritos = new Cola();
        Cola librosLeidos = new Cola();
        Cola librosListos = new Cola();

        // Agregar libros a la cola inicial
        Thread agregarLibrosThread = new Thread(){
            public void run ()  {
                // Creacion de libros sin acciones
                Libro libro1 = new Libro("Libro1");
                Libro libro2 = new Libro("Libro2");
                Libro libro3 = new Libro("Libro3");
                try {
                    libros.addLibro(libro1);
                    libros.addLibro(libro2);
                    libros.addLibro(libro3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Accion leer = new AccionLeer();
        Accion escribir = new AccionEscribir();
        Accion imprimir = new AccionImprimir();
        Accion leer2 = new AccionLeer();
        Accion escribir2 = new AccionEscribir();

        WorkerX workerXleer1 = new WorkerX(librosEscritos,librosLeidos,leer);
        WorkerX workerXescribir = new WorkerX(libros, librosEscritos, escribir);
        WorkerX workerXescribir2 = new WorkerX(libros, librosEscritos, escribir2);
        WorkerX workerXLeer2 = new WorkerX(librosEscritos, librosLeidos, leer2);
        WorkerX workerXImprimir = new WorkerX(librosLeidos, librosListos, imprimir);

        workerXImprimir.start();
        workerXescribir.start();
        workerXescribir2.start();
        workerXleer1.start();
        workerXLeer2.start();
        agregarLibrosThread.start();
    }
}

