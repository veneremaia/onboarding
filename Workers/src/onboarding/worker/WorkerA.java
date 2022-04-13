package onboarding.worker;

public class WorkerA extends Thread {

    private String tarea;

    public WorkerA(String tarea) {
        this.tarea = tarea;
    }

    public void run() {
        System.out.println("Preparando tarea del tipo A");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ejecutando tarea del tipo A: " + tarea);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finalizando tarea del tipo A: " + tarea);
    }
}
