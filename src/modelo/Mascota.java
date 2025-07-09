package modelo;

public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private String documentoDueno;

    public Mascota(int id, String nombre, String especie, String raza, String documentoDueno) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.documentoDueno = documentoDueno;
    }

    public Mascota(String nombre, String especie, String raza, String documentoDueno) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.documentoDueno = documentoDueno;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public String getRaza() { return raza; }
    public String getDocumentoDueno() { return documentoDueno; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecie(String especie) { this.especie = especie; }
    public void setRaza(String raza) { this.raza = raza; }
    public void setDocumentoDueno(String documentoDueno) { this.documentoDueno = documentoDueno; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Especie: " + especie +
                ", Raza: " + raza + ", Documento Dueño: " + documentoDueno;
    }
}