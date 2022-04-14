package onboarding.worker;

import onboarding.Cola;
import onboarding.Libro;
import onboarding.accion.Accion;

public class WorkerX extends Thread {

    private Cola input;

    private Cola output;

    private Accion accion;

    public WorkerX(Cola input, Cola output, Accion accion){
        this.input = input;
        this.output = output;
        this.accion = accion;
    }

    public void run() {
        System.out.println("Comienza el worker X nro " + Thread.currentThread().getId() + " Accion: " + accion.getClass().getSimpleName());
        Libro libro = null;
        while(true) {
            try {
                libro = input.getLibro();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            libro = accion.doIt(libro);
            try {
                output.addLibro(libro);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
