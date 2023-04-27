import data.ProveedorConexionSqlite;
import data.RepositorioCasas;
import data.RepositorioEstudiantes;
import modelo.Casa;
import modelo.Estudiante;
import proceso.Hogwarts;
import proceso.ProcesadorArchivoCsv;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import modelo.Estudiante;

public class app {
    public static void main(String[] args){

        // Solo interfaz de usuario
        System.out.println("Cargador de Datos de Hogwards");
        System.out.println("=============================");
        System.out.println();

        // Ingrese del nombre del archivo de datos csv a procesar
        System.out.print("Ingrese el nombre del archivo de datos: ");
        String nombreArchivo = args[0];


        System.out.println("\nProcesando archivo...");

        ProcesadorArchivoCsv procArchivo = new ProcesadorArchivoCsv(nombreArchivo);

        ArrayList<Estudiante> lista = procArchivo.procesarArchivoConValidacion();
        // Mostrar al usuario que el archivo se procesó.
        System.out.println("Proceso finalizado, " + lista.size() + " estudiantes leídos.");

        Hogwarts hogwarts = new Hogwarts();
        for(Estudiante e : lista){
            hogwarts.agregarEstudiante(e);
        }

        System.out.println("Cantidad de Estudiantes por casa: ");
        for (String casa : new String[] {"Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"}){
            System.out.println("Casa: " + casa + " ==> " + hogwarts.getCasa(casa).getCantidadEstudiantes() + " estudiantes");
        }

        ArrayList<Estudiante> noHumano = new ArrayList<>();

        ///
        System.out.printf(hogwarts.getCasa("Gryffindor").toString() + "\n");
        System.out.printf(hogwarts.getCasa("Slytherin").toString() + "\n");
        System.out.printf(hogwarts.getCasa("Hufflepuff").toString() + "\n");
        System.out.printf(hogwarts.getCasa("Ravenclaw").toString() + "\n");
        ////mustra de los estudiantes no humanos
        System.out.println("\nEstudiantes no humanos: ");

        noHumano=hogwarts.todosEstudiantesNoHumanos();
        for(Estudiante e: noHumano){
            System.out.printf(e.getNombre() + "(" + e.getEspecie() + ") " + ", ");
        }


        /// Persistir Casas
        Connection miConexion = ProveedorConexionSqlite.conectar(".\\data\\baseDeDatos.sqlite");
        RepositorioCasas repositorio = new RepositorioCasas(miConexion);

        for (String nombreCasa : new String[] {"Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"}){
            Casa casa = hogwarts.getCasa(nombreCasa);
            Casa casaGuardada = repositorio.getCasa(casa.getIdCasa());
            if (casaGuardada == null) {
                repositorio.agregarCasa(casa);
            }

        }

        /// Persistir Estudiantes Humanos
        Connection miConexion1 = ProveedorConexionSqlite.conectar(".\\data\\baseDeDatos.sqlite");
        RepositorioEstudiantes repoEstud = new RepositorioEstudiantes(miConexion1);

        ArrayList<Estudiante> todosHumanos = hogwarts.todosEstudiantesHumanos();
        for (Estudiante e: todosHumanos){
            Estudiante estGuardado = repoEstud.getEstudiante(e.getNumero());
            if (estGuardado==null){
                repoEstud.agregarEstudiante(e);
            }
        }
    }


}
