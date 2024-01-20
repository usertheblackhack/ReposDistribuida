/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemploagentemovil.soapproject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author USUARIO
 */
@Getter
public class ConexionPostgres {

    static String login = "postgres";
    static String password = "123";
    static String url = "jdbc:postgresql://localhost/RSPractica";

    private Connection conexion;

    public ConexionPostgres() throws Exception {
        conexion = null;
        Class.forName("org.postgresql.Driver");
        conexion = DriverManager.getConnection(url, login, password);
        if (conexion != null) {
            System.out.println("Conexion a la base de datos " + url + " ......OK");
        }
    }
}
