package onboarding.worker;

public class WorkerB extends Thread{

    private String tarea;

    public WorkerB(String tarea){
        this.tarea = tarea;
    }

    public void run(){
        System.out.println("Preparando tarea del tipo B");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ejecutando tarea del tipo B: "+tarea);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finalizando tarea del tipo B: "+tarea );
    }
}
