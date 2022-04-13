package onboarding.worker;

import onboarding.accion.Accion;

public class WorkerX extends Thread {

    private Accion accion;

    public WorkerX(Accion accion) {
        this.accion = accion;
    }

    public void run() {
       accion.doIt();
    }
}
