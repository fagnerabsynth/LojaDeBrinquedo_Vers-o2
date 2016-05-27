/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Conn.Database;
import Models.BrinquedoMOD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fagner
 */
@WebServlet(urlPatterns = {"/Brinquedo"})
public class Brinquedo extends HttpServlet {

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
        BrinquedoMOD b = new BrinquedoMOD();

        int codigo = 0;
        try {

            codigo = Integer.parseInt(request.getParameter("brinquedo"));
            b.codigo = codigo;
            Database c = new Database();
            b = c.select(b);
            codigo = 0;
        } catch (Exception ex) {
            Logger.getLogger(Administracao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            codigo = (request.getParameter("local").equals("home")) ? 1 : 0;
        } catch (Exception ex) {
            Logger.getLogger(Administracao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {
            if (codigo == 0) {
                out.println("<address>Você esta em: <b><a href='Javascript:Void(0)'  onclick='pagina(\"Catálogo de Brinquedos\")'> Catálogo de Brinquedos</a></b> -> <b><a href='Javascript:Void(0)'  onclick='abreCategoria(\"" + b.categoria + "\")'> " + b.categoria + "</a></b> -> <b><a href='Javascript:Void(0)'  onclick='abreBrinquedo(\"" + b.codigo + "\")'> " + b.descricao + "</a></b>  </address>");
            } else {
                out.println("<address>Você esta em: <b><a href='Javascript:Void(0)'  onclick='pagina(\"Home\")'> Home</a></b> -> <b><a href='Javascript:Void(0)'  onclick='abreBrinquedoHome(\"" + b.codigo + "\")'> " + b.descricao + "</a></b>  </address>");
            }

            out.println("<div class=\"col-sm-4\"><img src=\"" + b.imagem + "\"  width='250' height='300' class=\"img-rounded\"></div>");
            out.println("<div class=\"col-sm-8\"><h4>Código: " + b.codigo + "</h4></div>");

            out.println("<div class=\"col-sm-8\"><h3>" + b.descricao + "</h3></div>");
            out.println("<div class=\"col-sm-8\"><h2>R$ " + b.preco + "</h2></div>");

            out.println("<div class=\"col-sm-12\"><br /></div>");
            out.println(""
                    + "<div class=\"col-sm-12\">"
                    + "<fieldset><legend>Detalhe</legend><div> " + b.detalhe + "<div></fieldset></div>");

            out.close();
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
