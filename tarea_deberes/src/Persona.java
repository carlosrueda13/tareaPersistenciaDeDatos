import java.io.*;

public class Persona implements Serializable, Deberes {

    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected String cedula;

    public Persona(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    // ---------------------
    // Métodos de la interfaz Deberes
    // ---------------------

    @Override
    public void guardar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cedula + ".txt"))) {
            out.writeObject(this);
            System.out.println("Información guardada exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(cedula + ".txt"))) {
            Persona persona = (Persona) in.readObject();
            this.nombre = persona.nombre;
            this.cedula = persona.cedula;
            System.out.println("Información cargada exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar() {
        File file = new File(cedula + ".txt");
        if (file.delete()) {
            System.out.println("Archivo eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el archivo.");
        }
    }

    @Override
    public void actualizar() {
        // Para este ejemplo, simplemente llamamos a guardar()
        guardar();
    }

    // ---------------------
    // Métodos adicionales
    // ---------------------
    public String mostrarInfo() {
        return "Nombre: " + nombre + "\n" +
                "Cédula: " + cedula + "\n";
    }

}
