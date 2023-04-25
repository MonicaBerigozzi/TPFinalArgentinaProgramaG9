package data;

import modelo.Estudiante;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositorioEstudiantes {
    private Connection conexion;

    public RepositorioEstudiantes(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarEstudiante(Estudiante estud){
        try(Statement sentenciaInsert = conexion.createStatement()){
            String insert = "insert into Estudiantes (numero, blod_status, especie, genero, id_casa, nombre)" +
                    " values(";
            String blodStatus = estud.getBlodStatus().replace("'", "\'");
            String especie = estud.getEspecie().replace("'", "\'");
            insert += estud.getNumero() + ", '"+ blodStatus + "', '" + especie + "', '" + estud.getGenero() + "', " + estud.getCasa()+ ", ";
            String nombreEst = estud.getNombre().replace("'", "\'");
            insert += "'" + nombreEst + "'";
            insert += ")";
            sentenciaInsert.executeUpdate(insert);
        } catch (SQLException ex){
            System.out.println("Error: en sentencia de creación de estudiante: \n" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
