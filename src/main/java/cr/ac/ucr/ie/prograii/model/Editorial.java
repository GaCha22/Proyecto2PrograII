package cr.ac.ucr.ie.prograii.model;

public class Editorial {
    private int idEditorial;
    private String nombreEditorial;
    private String ciudad;

    public Editorial() {
        this.ciudad = this.nombreEditorial = "";
    }

    public Editorial(int idEditorial, String nombreEditorial, String ciudad) {
        this.idEditorial = idEditorial;
        this.nombreEditorial = nombreEditorial;
        this.ciudad = ciudad;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
