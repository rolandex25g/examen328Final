/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mipaquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.postgresql.Driver;
/**
 *
 * @author HP 2020
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "obtenernota")
    public String obtenernota(@WebParam(name = "ci") String ci, @WebParam(name = "idMateria") String idMateria) {
        try { 
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }
        String res="";
        try {
            //TODO write your implementation code here:
            Connection con = null;
            try {
                con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/academico2", "postgres", "root");
            } catch (SQLException ex) {
                Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Statement stmt = con.createStatement();
            String query = "select I.nota" +
                " from inscripcion as I, alumno as A" +
                " where I.idAlumno=A.idAlumno" +
                "  and A.ci='"+ci+"'"+
                "  and I.idMateria='"+idMateria+"'";
            ResultSet result = stmt.executeQuery(query);
            
            
            while(result.next())
            {
                res=res+ result.getString("nota")+",";
            }
            //return null;
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
        //return null;
    }
}
