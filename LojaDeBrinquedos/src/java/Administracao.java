/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Conn.Database;
import Models.BrinquedoMOD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
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
@WebServlet(urlPatterns = {"/Administração"})
public class Administracao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String pesquisa = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            pesquisa = request.getParameter("pesquisa").toString();
        } catch (Exception ex) {
            Logger.getLogger(Administracao.class.getName()).log(Level.SEVERE, null, ex);
        }



        try (PrintWriter out = response.getWriter()) {

            Database c = new Database();
            ArrayList<BrinquedoMOD> retorno;

            if (pesquisa.equals("")) {
                retorno = c.select();
            } else {
                retorno = c.select(pesquisa);
            }

            out.println("<address>Você esta em: <b><a href='Javascript:Void(0)'  onclick='pagina(\"" + request.getServletPath().replace('/', ' ').trim() + "\")'>" + request.getServletPath().replace('/', ' ') + "</a></b> </address>");
            out.println("<div class='col-sm-12'>");
            out.println("<div class=\"input-group\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"pesq\" placeholder=\"Pesquisar...\" value='"+ pesquisa +"'>");
            out.println("<span class=\"input-group-btn\">");
            out.println("<button class='form-control btn btn-" + request.getParameter("cor") + "' type=\"button\" onclick=\"paginaComParametros('Administração',$('#pesq').val())\"><span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span> Pesquisar</button>");
            out.println("</span>");
            out.println("</div>");
            out.println("<table class=\"table table-stripe table-hover\">");

            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Descrição</th>");
            out.println("<th>Categoria</th>");
            out.println("<th>Preço</th>");
            out.println("<th>Controle</th>");
            out.println("</tr>");
            out.println("</thead>");

            out.println("<tbody>");
            for (BrinquedoMOD dados : retorno) {
                out.println("<tr>");
                out.println("<td>" + dados.descricao + "</td>");
                out.println("<td>" + dados.categoria + "</td>");
                out.println("<td>" + dados.preco + "</td>");
                out.println("<td><a href=\"javascript:Void(0);\" onclick=\"brinquedoEditar('Manipula Brinquedo','" + dados.codigo + "')\">Editar</a> | <a href=\"javascript:Void(0);\"    onclick=\"brinquedoExcluir('Manipula Brinquedo','" + dados.codigo + "','" + dados.descricao + "');\">Excluir</a></td>");
                out.println("</tr>");
            }

            out.println("</tbody>");

            String total = (retorno.size() == 0) ? "Nenhum brinquedo em nossa base dados!" : (retorno.size() == 1) ? "Somente um brinquedo encontrado!" : "" + retorno.size() + " brinquedos encontrados!";
            out.println("<tfoot>");
            out.println("<tr>");
            out.println("<td colspan=\"4\"><a href=\"javascript:alteraPagina('Manipula Brinquedo')\" class=\"btn btn-default btn-sm\">Adicionar Brinquedo</a><div style=\"float:right;\">" + total + "</div></td>");
            out.println("</tr>");
            out.println("</tfoot>");
            out.println("</table>");
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
