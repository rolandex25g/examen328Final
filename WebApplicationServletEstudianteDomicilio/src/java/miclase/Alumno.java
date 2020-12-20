/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miclase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP 2020
 */
public class Alumno {
    public String idAlumno;
    public String nombre;
    public String paterno;
    public String materno;
    public String ci;
    public String domicilio;
    public Alumno(){
        
    }
    public Alumno(String id,String nom,String pat,String mat, String c,String dom){
        this.idAlumno=id;
        this.nombre=nom;
        this.paterno=pat;
        this.materno=mat;
        this.ci=c;
        this.domicilio=dom;
    }
    public ArrayList obtenerDatos(){
        ArrayList alumnos=null;
        try { 
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        //String res="";
        
            //TODO write your implementation code here:
            Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/academico2", "postgres", "root");
        

            
            Statement stmt = con.createStatement();
            String query = "select *" +
                " from alumno";
                //" where ci='"+ci+"'";
            ResultSet result = stmt.executeQuery(query);
            
            alumnos=new ArrayList();
            while(result.next())
            {
                alumnos.add(new Alumno(
                        result.getString("idAlumno"),
                        result.getString("nombre"),
                        result.getString("paterno"),
                        result.getString("materno"),
                        result.getString("ci"),
                        result.getString("domicilio")
                ));
                
            }
            return alumnos;
        } catch (SQLException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void obtenerFila(String xidAlumno){
        //ArrayList alumnos=null;
        //Alumno a=null;
        try { 
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        //String res="";
        
            //TODO write your implementation code here:
            Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/academico2", "postgres", "root");
            
            Statement stmt = con.createStatement();
            String query = "select *" +
                " from alumno"+
                " where idAlumno='"+xidAlumno+"'";
            ResultSet result = stmt.executeQuery(query);
            
            //alumnos=new ArrayList();
            while(result.next())
            {
                //a=new Alumno(
                        this.idAlumno=result.getString("idAlumno");
                        this.nombre=result.getString("nombre");
                        this.paterno=result.getString("paterno");
                        this.materno=result.getString("materno");
                        this.ci=result.getString("ci");
                        this.domicilio=result.getString("domicilio");
                //);
                
            }
            //return a;
        } catch (SQLException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return null;
    }
    
    public void Modificar(String xidAlumno,String xdomicilio){
        try { 
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }

        //TODO write your implementation code here:
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/academico2", "postgres", "root");
            
            Statement stmt = con.createStatement();
            String query = "update alumno set" +
                    " domicilio='" + xdomicilio + "'" +
                    " where idAlumno='" + xidAlumno + "'";
            ResultSet result = stmt.executeQuery(query);
              
        } catch (SQLException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
