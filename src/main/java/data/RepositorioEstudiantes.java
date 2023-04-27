package data;

import modelo.Estudiante;

import java.sql.Connection;
import java.sql.ResultSet;
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
            insert += estud.getNumero() + ", '"+ blodStatus + "', '" + especie + "', '" + estud.getGenero() + "', " + estud.getCasa().getIdCasa()+ ", ";
            String nombreEst = estud.getNombre().replace("'", "\'");
            insert += "'" + nombreEst + "'";
            insert += ")";
            //System.out.println(insert);
            sentenciaInsert.executeUpdate(insert);

        } catch (SQLException ex){
            System.out.println("Error: en sentencia de creación de estudiante: \n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Estudiante getEstudiante(int nroEstudiante){
        Estudiante res = null;
        try (Statement sentenciaConsulta = conexion.createStatement()){
            String query = "select nombre, genero, especie, blod_status from Estudiantes where numero = ";
            query += nroEstudiante;
            ResultSet rs = sentenciaConsulta.executeQuery(query);

            while (rs.next())
            {
                String nombre = rs.getString(1);
                char genero = rs.getString(2).charAt(0);
                String especie = rs.getString(3);
                String blodStatus = rs.getString(4);
                res = new Estudiante(nroEstudiante, nombre,genero,especie,blodStatus);

            }
        } catch (SQLException ex){
            System.out.println("Error: en sentencia de selección de estudiante: \n" + ex.getMessage());
            ex.printStackTrace();
        }
        return res;
    }
}
