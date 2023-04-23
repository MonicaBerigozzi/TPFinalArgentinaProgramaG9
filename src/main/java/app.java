import modelo.Casa;
import modelo.Estudiante;
import proceso.Hogwarts;
import proceso.ProcesadorArchivoCsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import modelo.Estudiante;

public class app {
    public static void main(String[] args){
        Scanner miEscanner = new Scanner(System.in);

        // Solo interfaz de usuario
        System.out.println("Cargador de Datos de Hogwards");
        System.out.println("=============================");
        System.out.println();

        // Ingrese del nombre del archivo de datos csv a procesar
        System.out.print("Ingrese el nombre del archivo de datos: ");
        //String nombreArchivo = miEscanner.nextLine();
        String nombreArchivo = args[0];


        System.out.println("\nProcesando archivo...");
        // Procesar el archivo de texto
        // 1 Abrir el archivo
        // 2 Leer el archivo línea por línea
        //      Para cada línea
        //      2.1 Separar porciones separadas por coma
        //      2.2 Validamos que la línea corresponda a un objeto a crear
        //      2.3 Creamos el objeto transformando las cadenas de acuerdo con los atributos
        //      2.4 Agregamos el objeto a la lista resultante.
        ProcesadorArchivoCsv procArchivo = new ProcesadorArchivoCsv(nombreArchivo);
//        ArrayList<Estudiante> lista = procArchivo.procesarArchivo();
        ArrayList<Estudiante> lista = procArchivo.procesarArchivoConValidacion();
        // Mostrar al usuario que el archivo se procesó.
        System.out.println("Proceso finalizado, " + lista.size() + " estudiantes leídos.");

//        System.out.println("\nLista de Estudiantes:");
//        for(Estudiante e : lista)
//            System.out.println(e);

        Hogwarts hogwarts = new Hogwarts();
        for(Estudiante e : lista){
            hogwarts.agregarEstudiante(e);
        }

        System.out.println("Cantidad de Estudiantes por casa: ");
        for (String casa : new String[] {"Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"}){
            System.out.println("Casa: " + casa + " ==> " + hogwarts.getCasa(casa).getCantidadEstudiantes() + " estudiantes");
        }

        ////creacion de las casas
        Casa casaGryffindor = new Casa(1, "Gryffindor");
        Casa casaSlytherin = new Casa(2, "Slytherin");
        Casa casaHufflepuff = new Casa(3, "Hufflepuff");
        Casa casaRavenclaw = new Casa(4, "Ravenclaw");
        ArrayList<Estudiante> noHumano = new ArrayList<>();


        ////agregarmos los estudiantes a partir de la lista de estudiantes
        agregarEstudianteAcasa(lista, casaGryffindor, casaSlytherin, casaHufflepuff, casaRavenclaw, noHumano);
        System.out.printf(casaGryffindor.toString() + "\n");
        System.out.printf(casaSlytherin.toString() + "\n");
        System.out.printf(casaHufflepuff.toString() + "\n");
        System.out.printf(casaRavenclaw.toString() + "\n");
        ////mustra de los estudiantes no humanos
        System.out.println("\nEstudiantes no humanos: ");
        estudiantesNoHumanos(lista, noHumano);
        for(Estudiante e: noHumano){
            System.out.printf(e.getNombre() + "(" + e.getEspecie() + ") " + ", ");
        }




    }

    ////Método para agregar estudiantes a la casa
    public static void agregarEstudianteAcasa(ArrayList<Estudiante> lista, Casa casaGryffindor,Casa casaSlytherin, Casa casaHufflepuff,Casa casaRavenclaw, ArrayList<Estudiante> noHumano){
        for(Estudiante e : lista){
            if(e.getNombreCasa().equals("Gryffindor")){
                casaGryffindor.agregarEstudiante(e);
            }else if(e.getNombreCasa().equals("Slytherin")){
                casaSlytherin.agregarEstudiante(e);
            }if(e.getNombreCasa().equals("Hufflepuff")){
                casaHufflepuff.agregarEstudiante(e);
            }else if(e.getNombreCasa().equals("Ravenclaw")){
                casaRavenclaw.agregarEstudiante(e);
            }


        }

    }
    ////Método para identidicar los estudiantes no humanos(Se consideró que si la especia tiene la palabra Humano,
    ////entonces es humano, por má que tenga otra palabra más)
    public static void estudiantesNoHumanos(ArrayList<Estudiante> lista, ArrayList<Estudiante> noHumano){

        for(Estudiante e: lista){
            if(!e.getEspecie().contains("Human")){
                noHumano.add(e);
            }
        }

    }
}
