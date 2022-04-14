package onboarding.accion;


import onboarding.Libro;


public class AccionImprimir implements Accion {

    @Override
    public Libro doIt(Libro libro) {
        System.out.println("Thread id: "+ Thread.currentThread().getId() +" Preparando accion imprimir libro " + libro.getContenido());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id: "+ Thread.currentThread().getId() +"Ejecutando onboarding.workers.accion imprimir libro " + libro.getContenido());
        libro.addAccion("Imprimir");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id: "+ Thread.currentThread().getId() +"Finalizando onboarding.workers.accion imprimir libro " + libro.getContenido());
        return libro;
    }
}
