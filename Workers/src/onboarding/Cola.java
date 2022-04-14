package onboarding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Cola {

    private List<Libro> libros = new ArrayList<>();

    private Semaphore existeLibro = new Semaphore(0);

    private Semaphore muxLibro = new Semaphore(1);

    /*
    *   public void addLibro(Libro l) throws InterruptedException {
        System.out.println(Thread.currentThread().getId() + " add libro 1");
        muxLibro.acquire();
        libros.add(l);
        muxLibro.release();
        System.out.println(Thread.currentThread().getId() + " add libro 2");
        existeLibro.release();
        System.out.println(Thread.currentThread().getId() + " add libro 3");

    }

    public Libro getLibro() throws InterruptedException {
        System.out.println(Thread.currentThread().getId() + " read libro 1");
        existeLibro.acquire();
        System.out.println(Thread.currentThread().getId() + " read libro 2");
        muxLibro.acquire();
        Libro l =  libros.remove(0);
        muxLibro.release();
        return l;
    }*/

    public void addLibro(Libro l) throws InterruptedException {
        System.out.println(Thread.currentThread().getId() + " Try to add libro "+ l.getContenido());
        synchronized(this.libros){
            libros.add(l);
        }
        System.out.println(Thread.currentThread().getId() + " Adding libro " + l.getContenido());
        existeLibro.release();
        System.out.println(Thread.currentThread().getId() + "  Libro was added " + l.getContenido());
        System.out.println("Cantidad disponibles para obtener libros : "+ existeLibro.availablePermits());
    }

    public Libro getLibro() throws InterruptedException {
        System.out.println(Thread.currentThread().getId() + " Try to get libro step 1");
        existeLibro.acquire();
        System.out.println(Thread.currentThread().getId() + " Getting libro step 2");
        Libro l;
        synchronized(this.libros){
            l =  libros.remove(0);
        }
        System.out.println(Thread.currentThread().getId() + " Return Libro " + l.getContenido());
        return l;
    }

}
