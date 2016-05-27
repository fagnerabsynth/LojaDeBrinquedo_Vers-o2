/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Conn.Database;
import Models.BrinquedoMOD;
import Models.CategoriaMOD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/Categoria"})
public class Categoria extends HttpServlet {

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

        CategoriaMOD ca = new CategoriaMOD();
        try {
            ca.categoria = request.getParameter("categoria");
        } catch (Exception ex) {
            Logger.getLogger(Administracao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {

            out.println("<address>Você esta em: <b><a href='Javascript:Void(0)'  onclick='pagina(\"Catálogo de Brinquedos\")'> Catálogo de Brinquedos</a></b> -> <b><a href='Javascript:Void(0)'  onclick='abreCategoria(\"" + ca.categoria + "\")'> " + ca.categoria + "</a></b>  </address>");

            Database c = new Database();
            ArrayList<BrinquedoMOD> cat = c.select(ca);

            if (cat.size() > 0) {

                for (BrinquedoMOD dados : cat) {
                    out.println("<div class=\"col-sm-4 col-sm-offset-1\" style=\"box-shadow: 2px 2px 2px #d1d1d1;border-radius:5px;padding:10px;cursor:pointer\" onclick=\"abreBrinquedo('" + dados.codigo + "')\">"
                            + "<center>");
                    out.println("<img class='img-reponsive img-circle' width='200' height='200' src=\"" + dados.imagem + "\">");
                    out.println("<h3 style=\"color:red\">" + dados.descricao + "</h3>");
                    out.println("R$ " + dados.preco);
                    out.println("</center>"
                            + "</div>");

                }

            }

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
