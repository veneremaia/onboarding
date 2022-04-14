package onboarding.accion;

import onboarding.Libro;

public class AccionEscribir implements Accion {

    @Override
    public Libro doIt(Libro libro) {
        System.out.println("Thread id: "+ Thread.currentThread().getId() +"Preparando accion escribir libro " + libro.getContenido());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id: "+ Thread.currentThread().getId() +" Ejecutando accion escribir libro " + libro.getContenido());
        libro.addAccion("Escribir");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id: "+ Thread.currentThread().getId() +" Finalizando accion escribir libro " + libro.getContenido());
        return libro;

    }
}
