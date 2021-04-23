package com.company;

import com.company.controlador.gestionBD;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static gestionBD bd;

    public static void main(String[] args) {
	// write your code here
        try {
            bd= new gestionBD();
            bd.estableceConexion();
            bd.consulta();
            bd.insertar();
            //bd.borrar();
            bd.consulta();
            //bd.actualizar();
            //bd.buscar();
            bd.cerrarConexion();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
