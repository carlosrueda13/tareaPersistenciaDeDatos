import javax.swing.*;
import java.awt.*;

public class VistaGrafica extends JFrame {

    private JTextArea areaTexto;

    public VistaGrafica() {
        setTitle("Administrador de Estudiantes y Profesores");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);

        JButton btnCrearEstudiante = new JButton("Crear Estudiante");
        JButton btnCrearProfesor = new JButton("Crear Profesor");
        JButton btnCargarEstudiante = new JButton("Cargar Estudiante");
        JButton btnCargarProfesor = new JButton("Cargar Profesor");

        btnCrearEstudiante.addActionListener(e -> crearEstudiante());
        btnCrearProfesor.addActionListener(e -> crearProfesor());
        btnCargarEstudiante.addActionListener(e -> cargarEstudiante());
        btnCargarProfesor.addActionListener(e -> cargarProfesor());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 2));
        panelBotones.add(btnCrearEstudiante);
        panelBotones.add(btnCrearProfesor);
        panelBotones.add(btnCargarEstudiante);
        panelBotones.add(btnCargarProfesor);

        getContentPane().add(panelBotones, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void crearEstudiante() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        String cedula = JOptionPane.showInputDialog(this, "Cédula:");
        String programa = JOptionPane.showInputDialog(this, "Programa:");
        int semestre = Integer.parseInt(JOptionPane.showInputDialog(this, "Semestre:"));

        Estudiante estudiante = new Estudiante(nombre, cedula, programa, semestre);
        estudiante.guardar();
        areaTexto.setText("Estudiante creado y guardado:\n\n");
        estudiante.mostrarInfo();
    }

    private void crearProfesor() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        String cedula = JOptionPane.showInputDialog(this, "Cédula:");
        String titulo = JOptionPane.showInputDialog(this, "Título:");
        int experiencia = Integer.parseInt(JOptionPane.showInputDialog(this, "Años de experiencia:"));

        Profesor profesor = new Profesor(nombre, cedula, titulo, experiencia);
        profesor.guardar();
        areaTexto.setText("Profesor creado y guardado:\n\n");
        profesor.mostrarInfo();
    }

    private void cargarEstudiante() {
        String cedula = JOptionPane.showInputDialog(this, "Cédula del estudiante:");
        Estudiante estudiante = new Estudiante("", cedula, "", 0);
        estudiante.cargar();

        areaTexto.setText("Estudiante cargado:\n\n");
        areaTexto.setText("Estudiante creado y guardado:\n\n" + estudiante.mostrarInfo());

    }

    private void cargarProfesor() {
        String cedula = JOptionPane.showInputDialog(this, "Cédula del profesor:");
        Profesor profesor = new Profesor("", cedula, "", 0);
        profesor.cargar();

        areaTexto.setText("Profesor cargado:\n\n");
        areaTexto.setText("Profesor creado y guardado:\n\n" + profesor.mostrarInfo());

    }


}
