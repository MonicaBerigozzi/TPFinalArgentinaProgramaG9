package modelo;

import java.util.ArrayList;

public class Casa {

    private int idCasa;
    private String nombre;
    private ArrayList<Estudiante> estudiantes;

    public Casa(int idCasa, String nombre) {
        this.idCasa = idCasa;
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCasa() {
        return idCasa;
    }

    public int getCantidadEstudiantes(){
        return estudiantes.size();
    }

    public void agregarEstudiante(Estudiante e){
        if (e != null && e.getNombreCasa().equals(nombre)){
            e.setCasa(this);
            estudiantes.add(e);
        }
        else
            throw new RuntimeException("Nombre de casa inválido. [" + e.getNombreCasa() + "]");

    }

    @Override
    public String toString() {
        String devolver = "Casa =>" +
                " idCasa= " + idCasa +
                " - nombre= '" + nombre + '\'' +
                " - estudiantes=\n";
        for (Estudiante e : estudiantes){
            devolver += e.toString() + "\n";
        }
        return devolver;
    }

    public  ArrayList<Estudiante> estudiantesNoHumanos(){
        ArrayList<Estudiante> noHumano = new ArrayList<>();
        for(Estudiante e: estudiantes){
            if(!e.getEspecie().contains("Human")){
                noHumano.add(e);
            }
        }
        return noHumano;
    }

    public  ArrayList<Estudiante> estudiantesHumanos(){
        ArrayList<Estudiante> Humano = new ArrayList<>();
        for(Estudiante e: estudiantes){
            if(e.getEspecie().contains("Human")){
                Humano.add(e);
            }
        }
        return Humano;
    }
}
