package onboarding.accion;

import onboarding.Libro;


public class AccionLeer implements Accion {

    @Override
    public Libro doIt(Libro libro) {
        System.out.println("Thread id: "+ Thread.currentThread().getId() +" Preparando accion leer libro " + libro.getContenido());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id: "+ Thread.currentThread().getId() +" Ejecutando accion leer libro " + libro.getContenido());
        libro.addAccion("Leer");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id: "+ Thread.currentThread().getId() +" Finalizando accion leer libro " + libro.getContenido());
        return libro;

    }
}
