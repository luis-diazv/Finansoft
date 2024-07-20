import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final boolean modoPruebas = true;

    private static String URL;
    private static Connection conexion;
    private static String consulta;

    public static void conectar() {
        System.out.println("\nINFO: Intentando establecer conexion con la base de datos");
        setRutaDatabase();
        try {
            URL = null;
            conexion = DriverManager.getConnection(URL);
            if (conexion != null) {
                System.out.println("INFO: Conexión a base de datos de "+ (modoPruebas? "pruebas" : "producción") +" establecida");
                //createDatabases();
            }
        } catch (SQLException e) {
            System.out.print("ERROR: Conexión a base de datos de "+ (modoPruebas? "pruebas" : "producción") +" sin éxito\n\tErrorInfo: "+e.getMessage());
        }
    }

    public static void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setRutaDatabase(){
        File archivo = new File ("src/data/database.txt");
        List<String> rutas = new ArrayList<>();

        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));

            String linea = "";

            while(true){
                linea = lector.readLine();
                 if(linea != null){
                     rutas.add(linea);
                 }else {
                     break;
                 }
            }

            lector.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (URL == null){
            if(modoPruebas){
                URL = "jdbc:sqlite:"+rutas.get(0);
            }else{
                URL = "jdbc:sqlite:"+rutas.get(1);
            }
        }
    }

    //TODO: Verificar si existe la base de datos, sino que las cree mediante las consultas
    private static void createDatabases(){

    }

    //TODO: Recuperar de la base de datos las consultas mediante su ID para mayor seguridad
    public static String getConsulta(Integer id){
        String query = "";

        try {
            Connection conexion = DriverManager.getConnection(URL);
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    return rs.getString(i);
                }
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static void main(String[] args) {
        conectar();
    }
}
