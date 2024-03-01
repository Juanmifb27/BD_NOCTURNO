/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesruizgijon.nocturno.client_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author grafeno30
 */
public class BaseDatos {
    
    private final String URL="jdbc:mysql://127.0.0.1:3306/";
    private final String nameDB; 
    private final  String user; 
    private final  String pass;

    Connection conexion;
    
    
    public BaseDatos(String nameDB, String user, String pass) {
        this.nameDB = nameDB;
        this.user = user;
        this.pass = pass;
    }
    
    public void conecta(){
        try {
            conexion = DriverManager.getConnection(URL + nameDB , user, pass);
            System.out.println("Conexión establecida con éxito...");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconecta(){

        try {
            if (conexion != null){
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void consultaPrueba(){
        String company;
        String  apellidos;
        String nombre;
        
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select company, last_name, first_name from customers limit 10");
            while(resultado.next()){
                company = resultado.getString("company");
                apellidos = resultado.getString("last_name");
                nombre = resultado.getString("first_name");
                
                System.out.println("Company "+ company + " Apellidos " + apellidos + " Nombre " + nombre);
            }
            resultado.close();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public ArrayList<String> consultaPrueba2(){
       
        ArrayList<String> resultados = new ArrayList<>();
        String pedidos;
        String nombre;
        String consulta = """
                        select concat_ws("-",contactFirstName, contactLastName) 
                        as name, COUNT(OrderNumber) as numOrders 
                        FROM customers inner join orders 
                        on customers.customerNumber = orders.customerNumber
                        group by customers.customerNumber order by name;
                        """;
       
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            while(resultado.next()){
                nombre = resultado.getString("name");
                pedidos = Integer.toString(resultado.getInt("numOrders"));
                resultados.add(nombre + " - "+ pedidos);
              
            }
            resultado.close();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultados;
    }
    
    
    //Sirve para realizar un describe de la tabla que se indique en el metodo.
    
    public String[] describe(String nombre) {

        String[] columnas = null;
        int n_columnas = 0;
        String consulta = "SELECT * FROM "+ nombre;

        try {

            Statement statement = conexion.createStatement();
            //

            ResultSet resultset = statement.executeQuery(consulta);
            //Extrae los metadatos de la tabla que le hemos indicado el nombre
            ResultSetMetaData metadatos = resultset.getMetaData();

            //consigue el numero de columnas que tiene esa tabla con el metodo metadatos.
            n_columnas = metadatos.getColumnCount();
            columnas = new String[n_columnas];

            for (int i = 1; i <= n_columnas; i++) {
                //getColumnName empieza desde el 1
                columnas[i - 1] = metadatos.getColumnName(i);
            }
        } catch (SQLException ex) {

            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnas;
    }
    
    public void getDataBaseNames(){
        
        try {

                Statement stmt = conexion.createStatement();

                //Retrieving the data
                
                
                //Ejecuta la consulta 'Show Databases'
                ResultSet rs = stmt.executeQuery("Show Databases");

                System.out.println("List of databases: ");
                

                while(rs.next()) {
                   //Obtiene la primera cadena
                   System.out.println(rs.getString(1));

                }

        } catch (SQLException ex) {

            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    
    
}
