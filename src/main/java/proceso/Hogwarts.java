package proceso;

import modelo.Casa;
import modelo.Estudiante;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hogwarts {

    private Map<String, Casa> casas;

    public Hogwarts() {
        casas = new HashMap<>();
        casas.put("Gryffindor", new Casa(1,"Gryffindor"));
        casas.put("Slytherin", new Casa(2, "Slytherin"));
        casas.put("Hufflepuff", new Casa(3, "Hufflepuff"));
        casas.put("Ravenclaw", new Casa(4, "Ravenclaw"));
    }

    public void agregarEstudiante(Estudiante e) {
        if (casas.containsKey(e.getNombreCasa())) {
            casas.get(e.getNombreCasa()).agregarEstudiante(e);
        }
        else
            throw new RuntimeException("Error: El nombre de la casa no existe.");

    }

    public Casa getCasa(String nombre){
        return casas.get(nombre);
    }

    public ArrayList<Estudiante> todosEstudiantesNoHumanos(){
        ArrayList<Estudiante> noHumano = new ArrayList<>(casas.get("Gryffindor").estudiantesNoHumanos());
        noHumano.addAll(casas.get("Slytherin").estudiantesNoHumanos());
        noHumano.addAll(casas.get("Hufflepuff").estudiantesNoHumanos());
        noHumano.addAll(casas.get("Ravenclaw").estudiantesNoHumanos());
        return noHumano;
    }
}
