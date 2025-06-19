import java.io.*;

public class Estudiante extends Persona {

    private static final long serialVersionUID = 2L;

    private String programa;
    private int semestre;

    public Estudiante(String nombre, String cedula, String programa, int semestre) {
        super(nombre, cedula);
        this.programa = programa;
        this.semestre = semestre;
    }

    // Sobrescribimos cargar() para incluir los nuevos atributos
    @Override
    public void cargar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(cedula + ".txt"))) {
            Estudiante estudiante = (Estudiante) in.readObject();
            this.nombre = estudiante.nombre;
            this.cedula = estudiante.cedula;
            this.programa = estudiante.programa;
            this.semestre = estudiante.semestre;
            System.out.println("Información del estudiante cargada correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() +
                "Programa: " + programa + "\n" +
                "Semestre: " + semestre + "\n";
    }

}
