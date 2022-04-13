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
        System.out.println(Thread.currentThread().getId() + " add libro 1");
        synchronized(this.libros){
            libros.add(l);
        }
        System.out.println(Thread.currentThread().getId() + " add libro 2");
        existeLibro.release();
        System.out.println(Thread.currentThread().getId() + " add libro 3");

    }

    public Libro getLibro() throws InterruptedException {
        System.out.println(Thread.currentThread().getId() + " read libro 1");
        existeLibro.acquire();
        System.out.println(Thread.currentThread().getId() + " read libro 2");
        Libro l;
        synchronized(this.libros){
            l =  libros.remove(0);
        }
        System.out.println(Thread.currentThread().getId() + " Libro l" + l);
        return l;
    }

    public static void main(String[] args) {
        Cola c = new Cola();
        Thread t1 = new Thread(){
          public void run ()  {
              try {
                  c.getLibro();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        };
        Thread t2 = new Thread(){
            public void run ()  {
                try {
                    c.addLibro(new Libro("Libro1"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        t2.start();
    }

}
