import java.io.*;

public class Profesor extends Persona {

    private static final long serialVersionUID = 3L;

    private String titulo;
    private int experiencia;

    public Profesor(String nombre, String cedula, String titulo, int experiencia) {
        super(nombre, cedula);
        this.titulo = titulo;
        this.experiencia = experiencia;
    }

    // Sobrescribimos cargar() para restaurar atributos propios del profesor
    @Override
    public void cargar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(cedula + ".dat"))) {
            Profesor profesor = (Profesor) in.readObject();
            this.nombre = profesor.nombre;
            this.cedula = profesor.cedula;
            this.titulo = profesor.titulo;
            this.experiencia = profesor.experiencia;
            System.out.println("Información del profesor cargada correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() +
                "Título: " + titulo + "\n" +
                "Experiencia: " + experiencia + " años\n";
    }

}
