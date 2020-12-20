/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mipaquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import miclase.Alumno;

/**
 *
 * @author HP 2020
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            
            String idAlumno;             
            idAlumno = request.getParameter("idAlumno");
            if(idAlumno==null){
                
                Alumno alumno=new Alumno();
                ArrayList lista=new ArrayList();
                lista=alumno.obtenerDatos();
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<td>Nombre</td>");
                out.println("<td>Paterno</td>");
                out.println("<td>Materno</td>");
                out.println("<td>CI</td>");
                out.println("<td>Domicilio</td>");
                out.println("<td></td>");
                out.println("</tr>");
                for(int i=0;i<lista.size();i++){
                    alumno=(Alumno)lista.get(i);
                    out.println("<tr>");
                    out.println("<td>"+alumno.nombre+"</td>");
                    out.println("<td>"+alumno.paterno+"</td>");
                    out.println("<td>"+alumno.materno+"</td>");
                    out.println("<td>"+alumno.ci+"</td>");
                    out.println("<td>"+alumno.domicilio+"</td>");
                    out.println("<td><a href='?idAlumno="+alumno.idAlumno+"'>Modificar</a></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } else {
                String domicilio = request.getParameter("domicilio");
                
                if(domicilio==null){
                    Alumno alumno=new Alumno();
                    alumno.obtenerFila(idAlumno);

                    out.println("<form method='POST' action='?guardar=1'>");
                    out.println("<input type='text' name='idAlumno' value='"+alumno.idAlumno+"'/>");
                    out.println("<input type='text' name='domicilio' value='"+alumno.domicilio+"'/>");
                    out.println("<input type='submit' name='Guardar' value='Guardar'/>");
                    out.println("</form>");
                } else {
                    Alumno alumno=new Alumno();
                    
                    //out.println(idAlumno);
                    //out.println(domicilio);
                    
                    alumno.Modificar(idAlumno,domicilio);
                    
                    out.println("Registro guardado.");
                    out.println("<a href='?voler=1'>Volver</a>");
                   
                }
            }

            
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
