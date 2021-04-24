package com.company.controlador;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class gestionBD {

    //Atributos
    private static Connection con;
    private static String url;

    //Constructor
    public gestionBD() {

        String port = "8080";
        String host = "192.168.0.10";
        url = "jdbc:mysql://" + host + ":" + port + "/";
        con = null;

    }

    //Metodo para establecer la conexión a la BD
    public static void estableceConexion() throws ClassNotFoundException, SQLException {

        String user = "android";
        String pass = "12345";
        String dataBase = "consuvino";

        System.out.println("Conectando...");
        boolean todoOK = false;

        if (con == null) {
            // Se mete todo en un try por los posibles errores de MySQL
            try {

                //Cargamos el driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establecemos la conexión con la base de datos.
                con = DriverManager.getConnection(url + dataBase, user, pass);
                todoOK = true;

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Error al abrir base de datos.");
                System.out.println(e.getMessage());
                todoOK = false;

            }
        }

        if (todoOK)
            JOptionPane.showMessageDialog(null, "Conexion establecida");
    }

    public static void cerrarConexion() throws SQLException {
        if (con != null) {
            // Cerramos la conexion a la base de datos.
            con.close();
            JOptionPane.showMessageDialog(null, "Conexion cerrada.");
        }
    }

    public static void consulta() throws SQLException {
        // Preparamos la consulta
        Statement s = con.createStatement();
        // Se realiza la consulta. Los resultados se guardan en el
        // ResultSet rs
        ResultSet rs = s.executeQuery("select * from editorial");
        // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
        }
    }

    public static void insertar() throws SQLException {
        Scanner leer = new Scanner(System.in);
        System.out.println("Inserte un Nombre editorial");
        String nombreEditorial = leer.nextLine();
        System.out.println("Inserte una direccion");
        String direccion = leer.nextLine();
        System.out.println("Inserte un telefono");
        String telefono = leer.nextLine();
        System.out.println("Inserte rfc");
        String rfc = leer.nextLine();

        String comandoSQL = ("INSERT INTO editorial (nombreEditorial,direccion,telefono,rfc) " +
                "VALUES('" + nombreEditorial + "','" + direccion + "','" + telefono + "','" + rfc + "')");
        Statement st = con.createStatement();
        st.executeUpdate(comandoSQL);
        System.out.println("Agregado correctamente");
    }

    public static void borrar() throws SQLException {
        Scanner leer = new Scanner(System.in);
        System.out.println("Inserte el usuario a borrar");
        String usuario = leer.nextLine();

        String comandoSQL = ("DELETE FROM usuarios WHERE usuario = ('" + usuario + "')");
        Statement st = con.createStatement();
        st.executeUpdate(comandoSQL);
        System.out.println("Usuario eliminado correctamente");
    }

    public static void buscar() throws SQLException {
        Scanner leer = new Scanner(System.in);
        System.out.println("Inserte el nombre a buscar");
        String nombre = leer.nextLine();

        Statement s = con.createStatement();//en caso necesario buscar por la ID (cuando exista una ID)
        String comandoSQL = ("SELECT * FROM usuarios WHERE nombre like ('" + nombre + "')");
        ResultSet rs = s.executeQuery(comandoSQL);
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }

    public static void actualizar() throws SQLException {
        Scanner leer = new Scanner(System.in);
        //Actualizar nombre
        System.out.println("Actualizando nombre del usuario...");
        System.out.println("Inserte el usuario a actualizar");
        String usuario = leer.nextLine();
        System.out.println("Inserte nuevo nombre del usuario");
        String nombre = leer.nextLine();

        String comandoSQL = ("UPDATE usuarios SET nombre = ('" + nombre + "') WHERE usuario = ('" + usuario + "') ");
        Statement st = con.createStatement();
        st.executeUpdate(comandoSQL);
        System.out.println("Nombre actualizado correctamente");

        //Actualizar password
        System.out.println("Actualizando password...");
        System.out.println("Inserte el usuario a actualizar");
        usuario = leer.nextLine();
        System.out.println("Inserte un nuevo password");
        String password = leer.nextLine();

        String comandoSQLac = ("UPDATE usuarios SET password = ('" + password + "') WHERE usuario = ('" + usuario + "') ");
        Statement sta = con.createStatement();
        sta.executeUpdate(comandoSQLac);
        System.out.println("Password actualizado correctamente");

        //Actualizar usuario
        System.out.println("Actualizando usuario...");
        System.out.println("Inserte el usuario a actualizar");
        usuario = leer.nextLine();
        System.out.println("Inserte un nuevo usuario");
        String usuarioac = leer.nextLine();

        String comandoSQLact = ("UPDATE usuarios SET usuario = ('" + usuarioac + "') WHERE usuario = ('" + usuario + "') ");
        Statement stat = con.createStatement();
        stat.executeUpdate(comandoSQLact);
        System.out.println("Password actualizado correctamente");
    }
}
