import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AulaVirtual {

    // Método para buscar un alumno por DNI
    public static void buscarAlumnoPorDNI(Scanner scanner, ArrayList<Alumno> alumnos) {
        System.out.println("Ingrese el DNI del alumno a buscar:");
        String dniBuscado = scanner.nextLine();

        for (Alumno alumno : alumnos) {
            if (alumno.getDni().equals(dniBuscado)) {
                System.out.println("Información del alumno encontrado:");
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Carrera: " + alumno.getCarrera());
                System.out.println("Legajo: " + alumno.getLegajo());
                System.out.println("Facultad: " + alumno.getFacultad());
                return;
            }
        }

        System.out.println("No se encontró ningún alumno con el DNI ingresado.");
    }

    // Método para mostrar la lista de alumnos
    private static void mostrarListaAlumnos(ArrayList<Alumno> alumnos) {
        System.out.println();
        System.out.println("Lista de Alumnos:");
        System.out.println();
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : alumnos) {
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("DNI: " + alumno.getDni());
                System.out.println("Legajo: " + alumno.getLegajo());
                System.out.println("Facultad: " + alumno.getFacultad());
                System.out.println("Carrera: " + alumno.getCarrera());
                System.out.println("Materias:");
                for (String materia : alumno.getMaterias()) {
                    System.out.println("- " + materia);
                }
                System.out.println();
            }
        }
    }

    // Método para mostrar la lista de profesores
    private static void mostrarListaProfesores(ArrayList<Profesor> profesores) {
        System.out.println();
        System.out.println("Lista de Profesores:");
        System.out.println();
        if (profesores.isEmpty()) {
            System.out.println("No hay Profesores registrados.");
        } else {
            for (Profesor profesor : profesores) {
                System.out.println("Nombre: " + profesor.getNombre());
                System.out.println("DNI: " + profesor.getDni());
                System.out.println("Facultad: " + profesor.getFacultad());
                System.out.println("Materias:");
                for (String materia : profesor.getMaterias()) {
                    System.out.println("- " + materia);
                }
                System.out.println();
            }
        }
    }

    // Método para volver al menú principal o salir
    private static boolean preguntarVolverMenu(Scanner scanner) {
        System.out.println();
        System.out.println("¿Desea volver al menú principal o salir?");
        System.out.println("1. Volver al menú principal");
        System.out.println("2. Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion == 1;
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = cargarAlumnos();
        ArrayList<Profesor> profesores = cargarProfesores();

        boolean ejecutar = true;
        while (ejecutar) {
            System.out.println("*************************************");
            System.out.println("*   BIENVENIDO AL SISTEMA DE GESTIÓN  *");
            System.out.println("*          DEL AULA VIRTUAL          *");
            System.out.println("*************************************");
            System.out.println();
            System.out.println("Seleccione la opción que desea realizar:");
            System.out.println();
            System.out.println("1. Registrar Nuevo Alumno.");
            System.out.println("2. Registrar Nuevo Profesor.");
            System.out.println("3. Buscar Alumno por DNI.");
            System.out.println("4. Mostrar Lista de Alumnos registrados.");
            System.out.println("5. Mostrar Lista de Profesores registrados.");
            System.out.println("6. Eliminar Alumno.");
            System.out.println("7. Modificar Datos de Alumno.");
            System.out.println("8. Salir.");
            System.out.println();
            System.out.print("Ingrese la opción deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarAlumno(scanner, alumnos);
                    break;
                case 2:
                    registrarProfesor(scanner, profesores);
                    break;
                case 3:
                    buscarAlumnoPorDNI(scanner, alumnos);
                    break;
                case 4:
                    mostrarListaAlumnos(alumnos);
                    break;
                case 5:
                    mostrarListaProfesores(profesores);
                    break;
                case 6:
                    eliminarAlumno(scanner, alumnos);
                    break;
                case 7:
                    modificarAlumno(scanner, alumnos);
                    break;
                case 8:
                    ejecutar = false;
                    System.out.println("Gracias por utilizar el sistema.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }

            if (opcion != 8) {
                ejecutar = preguntarVolverMenu(scanner);
            }
        }
        scanner.close();
    }

    // Método para registrar un alumno
    public static void registrarAlumno(Scanner scanner, ArrayList<Alumno> alumnos) {
        System.out.println();
        System.out.println("Ingrese el nombre del Alumno:");
        String nombre = scanner.nextLine();
        if (!validarCampoNoVacio(scanner, nombre, "nombre")) {
            return;
        }

        System.out.println("Ingrese el DNI del Alumno:");
        String dni = ingresarDato(scanner, "DNI");
        if (dni == null) {
            return;
        }

        System.out.println("Ingrese el legajo del Alumno:");
        String legajo = ingresarDato(scanner, "legajo");
        if (legajo == null) {
            return;
        }

        System.out.println("Ingrese la facultad del Alumno:");
        String facultad = scanner.nextLine();
        if (!validarCampoNoVacio(scanner, facultad, "facultad")) {
            return;
        }

        System.out.println("Ingrese la carrera del Alumno:");
        String carrera = scanner.nextLine();
        if (!validarCampoNoVacio(scanner, carrera, "carrera")) {
            return;
        }

        System.out.println("Ingrese las materias cursadas, separadas por coma:");
        String materias = scanner.nextLine();
        if (!validarCampoNoVacio(scanner, materias, "materias")) {
            return;
        }

        System.out.println();
        Alumno alumno = new Alumno(nombre, dni, carrera, legajo, facultad, materias.split(",\\s+"));
        alumnos.add(alumno);
        guardarAlumnos(alumnos);

        System.out.println("Alumno registrado exitosamente.");
    }

    // Método para registrar un profesor
    public static void registrarProfesor(Scanner scanner, ArrayList<Profesor> profesores) {
        System.out.println();
        System.out.println("Ingrese el nombre del profesor:");
        String nombre = scanner.nextLine();
        if (!validarCampoNoVacio(scanner, nombre, "nombre")) {
            return;
        }

        System.out.println("Ingrese el DNI del profesor:");
        String dni = ingresarDato(scanner, "DNI");
        if (dni == null) {
            return;
        }

        System.out.println("Ingrese la facultad donde dicta clases el profesor:");
        String facultad = scanner.nextLine();
        if (!validarCampoNoVacio(scanner, facultad, "facultad")) {
            return;
        }

        System.out.println("Ingrese las materias que el profesor dicta, separadas por coma:");
        String materias = scanner.nextLine();
        if (!validarCampoNoVacio(scanner, materias, "materias")) {
            return;
        }

        Profesor profesor = new Profesor(nombre, dni, materias.split(",\\s+"), facultad);
        profesores.add(profesor);

        System.out.println();
        System.out.println("Profesor registrado exitosamente.");
    }

    // Método para modificar datos de un alumno
    public static void modificarAlumno(Scanner scanner, ArrayList<Alumno> alumnos) {
        System.out.println("Ingrese el DNI del alumno que desea modificar:");
        String dniModificar = scanner.nextLine();

        for (Alumno alumno : alumnos) {
            if (alumno.getDni().equals(dniModificar)) {
                System.out.println("Alumno encontrado. Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");

                System.out.println("Nombre actual: " + alumno.getNombre());
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                if (!nuevoNombre.isEmpty()) {
                    alumno.setNombre(nuevoNombre);
                }

                System.out.println("Legajo actual: " + alumno.getLegajo());
                System.out.print("Nuevo legajo: ");
                String nuevoLegajo = scanner.nextLine();
                if (!nuevoLegajo.isEmpty() && validarDato(nuevoLegajo)) {
                    alumno.setLegajo(nuevoLegajo);
                }

                System.out.println("Facultad actual: " + alumno.getFacultad());
                System.out.print("Nueva facultad: ");
                String nuevaFacultad = scanner.nextLine();
                if (!nuevaFacultad.isEmpty()) {
                    alumno.setFacultad(nuevaFacultad);
                }

                System.out.println("Carrera actual: " + alumno.getCarrera());
                System.out.print("Nueva carrera: ");
                String nuevaCarrera = scanner.nextLine();
                if (!nuevaCarrera.isEmpty()) {
                    alumno.setCarrera(nuevaCarrera);
                }

                System.out.println("Materias actuales: " + String.join(", ", alumno.getMaterias()));
                System.out.print("Nuevas materias (separadas por coma): ");
                String nuevasMaterias = scanner.nextLine();
                if (!nuevasMaterias.isEmpty()) {
                    alumno.setMaterias(nuevasMaterias.split(",\\s+"));
                }

                guardarAlumnos(alumnos);
                System.out.println("Datos del alumno modificados exitosamente.");
                return;
            }
        }

        System.out.println("No se encontró ningún alumno con el DNI ingresado.");
    }

    // Método para guardar alumnos en archivo TXT
    public static void guardarAlumnos(ArrayList<Alumno> alumnos) {
        try {
            PrintWriter writer = new PrintWriter("alumnos.txt", "UTF-8");

            for (Alumno alumno : alumnos) {
                String materias = String.join(",", alumno.getMaterias());
                writer.println(alumno.getNombre() + "," + alumno.getDni() + "," + alumno.getCarrera() + "," + alumno.getLegajo() + "," + alumno.getFacultad() + "," + materias);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los alumnos.");
            e.printStackTrace();
        }
    }

    // Cargar la lista de alumnos registrados
    public static ArrayList<Alumno> cargarAlumnos() {
        return new ArrayList<Alumno>();
    }

    // Cargar la lista de profesores registrados
    public static ArrayList<Profesor> cargarProfesores() {
        return new ArrayList<Profesor>();
    }

    // Método para validar datos
    public static String ingresarDato(Scanner scanner, String campo) {
        String dato;
        do {
            dato = scanner.nextLine();
            if (!validarDato(dato)) {
                System.out.println("El " + campo + " ingresado no es válido.");
                System.out.println();
                System.out.println("1. Para volver a intentar ingresar el " + campo + ", ingrese el valor '1'.");
                System.out.println("2. Para volver al menú principal, ingrese el valor '2'.");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion == 2) {
                    return null;
                }
            } else {
                return dato;
            }
        } while (true);
    }

    // Método para verificar caracteres del alumno
    private static boolean validarDato(String dato) {
        return dato.matches("\\d{8}");
    }

    // Método para verificar el ingreso de los datos
    public static boolean validarCampoNoVacio(Scanner scanner, String valor, String nombreCampo) {
        if (valor.isEmpty()) {
            System.out.println("El campo " + nombreCampo + " no puede estar vacío.");
        } else if (nombreCampo.equals("materias") && (valor.length() > 100 || !valor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ,\\s]+"))) {
            System.out.println("El campo " + nombreCampo + " solo puede contener letras, espacios y comas, con una longitud máxima de 60 caracteres.");
        } else if (!nombreCampo.equals("materias") && (valor.length() > 30 || !valor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))) {
            System.out.println("El campo " + nombreCampo + " solo puede contener letras y espacios, con una longitud máxima de 30 caracteres.");
        } else {
            return true;
        }

        System.out.println();
        System.out.println("1. Para volver a intentar ingresar el " + nombreCampo + " ingrese el valor '1'.");
        System.out.println("2. Para volver al menú principal ingrese el valor '2'.");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 2) {
            return false;
        } else {
            return validarCampoNoVacio(scanner, scanner.nextLine(), nombreCampo);
        }
    }

    // Método para eliminar un alumno registrado
    public static void eliminarAlumno(Scanner scanner, ArrayList<Alumno> alumnos) {
        System.out.println("Ingrese el DNI del alumno que desea eliminar:");
        String dniEliminar = scanner.nextLine();

        boolean encontrado = false;
        for (Alumno alumno : alumnos) {
            if (alumno.getDni().equals(dniEliminar)) {
                alumnos.remove(alumno);
                encontrado = true;
                System.out.println("Alumno eliminado correctamente.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún alumno con el DNI ingresado.");
        }
    }
}

class Alumno {
    private String nombre;
    private String dni;
    private String carrera;
    private String legajo;
    private String facultad;
    private String[] materias;

    // Constructor de la clase Alumno
    public Alumno(String nombre, String dni, String carrera, String legajo, String facultad, String[] materias) {
        this.nombre = nombre;
        this.dni = dni;
        this.carrera = carrera;
        this.legajo = legajo;
        this.facultad = facultad;
        this.materias = materias;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String[] getMaterias() {
        return materias;
    }

    public void setMaterias(String[] materias) {
        this.materias = materias;
    }
}

class Profesor {
    private String nombre;
    private String dni;
    private String[] materias;
    private String facultad;

    // Constructor de la clase Profesor
    public Profesor(String nombre, String dni, String[] materias, String facultad) {
        this.nombre = nombre;
        this.dni = dni;
        this.materias = materias;
        this.facultad = facultad;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String[] getMaterias() {
        return materias;
    }

    public void setMaterias(String[] materias) {
        this.materias = materias;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
