package onboarding;

import java.util.ArrayList;
import java.util.List;

public class Libro {

    private List<String> acciones;

    private String contenido;

    public Libro( String contenido) {
        this.acciones = new ArrayList<>();
        this.contenido = contenido;
    }

    public List<String> getAcciones() {
        return acciones;
    }

    public void addAcciones(String accion) {
        this.acciones.add(accion);
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
