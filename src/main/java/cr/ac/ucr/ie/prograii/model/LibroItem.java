package cr.ac.ucr.ie.prograii.model;

public class LibroItem {
    private int numInscripcion;
    private boolean estado;

    public LibroItem() {
    }

    public LibroItem(int numInscripcion, boolean estado) {
        this.numInscripcion = numInscripcion;
        this.estado = estado;
    }

    public int getNumInscripcion() {
        return numInscripcion;
    }

    public void setNumInscripcion(int numInscripcion) {
        this.numInscripcion = numInscripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
