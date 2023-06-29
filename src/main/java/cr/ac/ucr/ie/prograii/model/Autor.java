package cr.ac.ucr.ie.prograii.model;

public class Autor {
    private int idAutor;
    private String nombre;
    private String apellidosAutor;

    public Autor() {
        this.nombre = this.apellidosAutor = "";
    }

    public Autor(int idAutor, String nombre, String apellidosAutor) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellidosAutor = apellidosAutor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidosAutor() {
        return apellidosAutor;
    }

    public void setApellidosAutor(String apellidosAutor) {
        this.apellidosAutor = apellidosAutor;
    }
}
