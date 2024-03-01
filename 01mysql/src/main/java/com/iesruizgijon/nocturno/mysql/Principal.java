/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.iesruizgijon.nocturno.mysql;

import java.util.ArrayList;

/**
 *
 * @author grafeno30
 */
public class Principal {

    public static void main(String[] args) {
        
        ArrayList<String> res ;
        
        BaseDatos  bd = new BaseDatos("northwind", "root", "123qweASD_");
        
                   bd.conecta();
                   
                   bd.consultaPrueba();
                   
                   bd.desconecta();
                    System.out.println("------------------------------------------------");
                  //Devuelve un ArrayList con los nombres de los clientes concatenados con los pedidos realizados
                   
        BaseDatos  bd2= new BaseDatos("classicmodels", "root", "123qweASD_");
        
                   bd2.conecta();
                   
                   res = bd2.consultaPrueba2();
                   for (String re : res){
                       System.out.println(re);
            
        }
                   
                   bd2.desconecta();

                   String[] res2 ;
                   System.out.println("-----------------------------------------");
                   
                   //Devuelve una array de String que es un describe a una tabla

        BaseDatos  bd3 = new BaseDatos("classicmodels", "root", "123qweASD_");

                    bd3.conecta();

                    res2 = bd3.describe("customers");
                    for(String columna: res2){
                        System.out.println(columna);
                    }

                    bd3.desconecta();
                    
     
        
        //bd.consultaPrueba2();
        
       // resultado = bd.describe("customers");
        
       // for (String linea : resultado) {
       //     System.out.println(linea);
            
       // }
       
       bd.conecta();
       
       bd.getDataBaseNames();
        
        bd.desconecta();   
                   
    }
}